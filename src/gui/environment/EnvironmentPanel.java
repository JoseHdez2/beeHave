package gui.environment;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;

import gui.util.ColorHelper;
import model.entity.Entity;
import model.entity.EntityTypeMapper;
import model.entity.agent.Agent;
import model.entity.object.EnvObject;
import model.environment.EnvironmentModel;
import util.typedef.Matrix;
import util.typedef.Position;

/**
 *  JPanel acting as GUI for the simulation environment.
 *  Visually represents an EnvironmentModel.
 *  
 *  @author jose
 */
public class EnvironmentPanel extends JLayeredPane {
    
    // TODO: default serial id
    private static final long serialVersionUID = 1L;
    
    private static int GRID_TILE_SIZE = 50; // Tile width and height, in pixels.
    
    public enum ClickEffect {
        CREATE,
        SELECT,
        GRABBING_ENTITY  // Currently grabbing an entity
    }
    
    private ClickEffect clickEffect; // Effect that clicking on a tile will have.
    private Entity.type selectedEntityType; // For when creating an entity.
    
    private String selectedEntityName; // Currently selected entity, to inspect or modify.
    
    private EnvironmentModel env;   // Environment model this interface will represent. 
    
    public Matrix<EnvironmentLabel> envLabels;  // JLabels that will have entities as icons. 
    
    private EnvironmentLabel highlightedTileCursor; // TODO: uninitialized; it initializes soon enough. but be careful...
    private EnvironmentLabel highlightedTileInspector;
    
    // Cursor stuff. Thanks to http://stackoverflow.com/a/4274653/3399416.
    
    
    private EnvironmentPanel(){
        selectedEntityType = Entity.type.OBJECT_FLOWER;
        selectedEntityName = null;
        setClickEffect(ClickEffect.CREATE);
    }
    
    public EnvironmentPanel(int width, int height){
        this();
        env = new EnvironmentModel(width, height);
        // Temp variables
        int x = env.getWidth();
        int y = env.getHeight();
        
        this.setSize(GRID_TILE_SIZE * x, GRID_TILE_SIZE * y);
        setLayout(new GridLayout(width,height));
        
        this.envLabels = new Matrix<EnvironmentLabel>(new EnvironmentLabel[x][y]);
        
//        setCursorImage("res/image/meat.png");
        
        // Add tiles
        for (int j = 0; j < envLabels.height(); j++){
            for (int i = 0; i < envLabels.width(); i++){
                EnvironmentLabel label = new EnvironmentLabel(i,j);
                label.addMouseListener(clickEffectListener);    // Label can listen clicks.
                
                envLabels.set(i, j, label); // Store in EnvironmentPanel's internal memory.
                add(envLabels.get(i, j));  // Add into EnvironmentPanel's JPanel hierarchy (GUI).
            }
        }
        recolorTiles();
        this.repaint();
    }
    
    /**
     * Re-set background color of environment tiles.
     */
    private void recolorTiles(){
        
        tileColorDarkOffset = -20;
        tileColorDark = ColorHelper.offsetColor(DEFAULT_TILE_COLOR, tileColorDarkOffset);
        
        for (int j = 0; j < envLabels.height(); j++){
            for (int i = 0; i < envLabels.width(); i++){
                envLabels.get(i, j).setBackground(tileColor(i,j));
            }
        }
    }
    
    private static Color DEFAULT_TILE_COLOR = new Color(200,200,200);
    private Color tileColorDark; // Color of darker tiles in checkerboard mode.
    
    private int tileColorDarkOffset; // Difference between light and dark tiles in checkerboard mode.
    private boolean tileColorChecker = true; // (De)activate checkerboard mode.
    private boolean tileColorTerrain = true; // (De)activate terrain coloring.
    
    /**
     * Take tile position and EnvironmentModel to determine 
     * @param i Column (tile position)
     * @param j Row (tile position)
     * @return  Corresponding.
     */
    private Color tileColor(int i, int j){
        Color color = DEFAULT_TILE_COLOR;
        if (tileColorChecker) color = (i+j)%2 == 0 ? tileColorDark : DEFAULT_TILE_COLOR;
        
        if (!tileColorTerrain) return color;
        switch(env.getTerrain().get(i, j)){
        case GRASS: color = ColorHelper.mean(color, Color.GREEN); break;
        case SOIL: color = ColorHelper.mean(color, Color.YELLOW); break;
        default: break; // TODO: Shouldn't happen. Throw exception? 
        }
        return color;
    }
    
    /**
     * Create N entities in random positions.
     */
    public void generateEntities(int n, Entity.type entityType){
        for (int i = 0; i < n; i++)
            EntityTypeMapper.createEntityInto(env, entityType, env.randomPosition());
    }
    
    /**
     * Given a tile position (for example, from {@link EnvironmentPanel#clickEffectListener})
     * execute an effect, usually relating to said tile.
     * @param x Column (tile position)
     * @param y Row (tile position)
     */
    private void clickEffect(int x, int y){
        switch(clickEffect){
        case CREATE:
            EntityTypeMapper.createEntityInto(env, selectedEntityType, new Position(x,y)); break;
        case SELECT:
            // If there's an entity in (x,y), select it.
            // Agents take precedence over objects, newly created take precedence over older.
            // TODO: select(highlight) object in list when selected in the environment.
            for (Entity e : env.getObjects())
                if(new Position(x,y).equals(e.getPos())) selectedEntityName = e.getName();
            for (Entity e : env.getAgents())
                if(new Position(x,y).equals(e.getPos())) selectedEntityName = e.getName();
            break;
        default:
             System.out.println("probably dragging"); break;
        }
    }
    
    /**
     * Mouse listener for when a tile of the grid is clicked.
     * Determines which effect is produced by looking at the clickEffect attribute of this class.
     */
    MouseListener clickEffectListener = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            EnvironmentLabel el = ((EnvironmentLabel)e.getSource());
            clickEffect(el.x,el.y);
            el.getParent().repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseClicked(e);
            EnvironmentLabel el = ((EnvironmentLabel)e.getSource());
            System.out.println(String.format("old pos: %s", new Position(el.x,el.y)));
            if (clickEffect == ClickEffect.SELECT)
                setClickEffect(ClickEffect.GRABBING_ENTITY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
            if (clickEffect == ClickEffect.GRABBING_ENTITY){
                EnvironmentLabel ht = highlightedTileCursor;
                if (getSelectedEntity() != null) getSelectedEntity().setPos(new Position(ht.x,ht.y));
                System.out.println(String.format("new pos: %s", new Position(ht.x,ht.y)));
                setClickEffect(ClickEffect.SELECT);
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ((EnvironmentLabel)e.getSource()).setHighlightedCursor(true);
            highlightedTileCursor = ((EnvironmentLabel)e.getSource());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((EnvironmentLabel)e.getSource()).setHighlightedCursor(false);
        }
        
    };
    
    /**
     * Perform a step of the simulation.
     * Allow each of the agents in the environment to act out a single turn.
     * Delegates/cascades into each of the agents in the environment.
     */
    public void simulationStep(){
        env.simulationStep();
        repaint();  // Repaint to show changes.
    }
    
    @Override
    public void paint(Graphics g) {
        for (int j = 0; j < envLabels.height(); j++){
            for (int i = 0; i < envLabels.width(); i++){
                // Erase previous frame.
                envLabels.get(i, j).setIcon(null);
                
                // TODO: this way of painting works but is not optimal.
                
                for (EnvObject o : env.getObjects())
                    if (new Position(i,j).equals(o.getPos())){
                        envLabels.get(i, j).setIcon(o.getIcon());
                        // TODO messy override
                        /*
                        if (o.getEntityType() == Entity.type.OBJECT_FLOWER)
                            if (((ObjectFlower)o).getPollen() <= 0)
                                envLabels.get(i, j).setIcon(new ImageIcon("res/image/daisyDead.png"));
                                */
                    }
                
                for (Agent a : env.getAgents())
                    if (new Position(i,j).equals(a.getPos())) envLabels.get(i, j).setIcon(a.getIcon());
                    
                
                // TODO show hive up front
                EnvObject hive = env.getObjects().get(0);
                if (new Position(i,j).equals(hive.getPos()))  envLabels.get(i, j).setIcon(hive.getIcon());
            }
        }
        super.paint(g);
    }
    
    private void setCursorImage(Image image){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor c = toolkit.createCustomCursor(image, new Point(this.getX(), 
                   this.getY()), "img");
        this.setCursor (c);
    }
    
    private void setCursorImage(String imagePath){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(imagePath);
        setCursorImage(image);
    }
    
    private void setCursorImage(){
        setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Using the internal selectedEntityName, find and return the currently selected entity.
     * @return Currently selected entity.
     */
    public Entity getSelectedEntity(){
        return env.getEntity(selectedEntityName);
    }
    

    /**
     * Set both the click effect and the cursor to display over the environment.
     * @param clickEffect
     */
    public void setClickEffect(ClickEffect clickEffect) {
        this.clickEffect = clickEffect;
        switch(clickEffect){
        case CREATE:
            setCursorImage("res/image/star.png"); break;
        case GRABBING_ENTITY:
            setCursorImage(getSelectedEntity().getIcon().getImage()); break;
        case SELECT:
            setCursorImage(); break;
        default:
            break;
        
        }
    }
    
    /*
     * Special getters and setters.
     */

    public void setSelectedEntityName(String selectedEntityName) {
        this.selectedEntityName = selectedEntityName;
        int i = getSelectedEntity().getPosX();
        int j = getSelectedEntity().getPosY();
        envLabels.get(i, j).setHighlightedInspector(true);
    }
    
    /*
     * Getters and setters.
     */
    
    public Matrix<EnvironmentLabel> getEnvLabels() {
        return envLabels;
    }

    public EnvironmentModel getEnv() {
        return env;
    }

    public int getTileColorDarkOffset() {
        return tileColorDarkOffset;
    }

    public void setTileColorDarkOffset(int tileColorDarkOffset) {
        this.tileColorDarkOffset = tileColorDarkOffset;
    }

    public boolean isTileColorChecker() {
        return tileColorChecker;
    }

    public void setTileColorChecker(boolean tileColorChecker) {
        this.tileColorChecker = tileColorChecker;
        recolorTiles();
    }

    public boolean isTileColorTerrain() {
        return tileColorTerrain;
    }

    public void setTileColorTerrain(boolean tileColorTerrain) {
        this.tileColorTerrain = tileColorTerrain;
        recolorTiles();
    }

    public void setSelectedEntityType(Entity.type selectedEntityType) {
        this.selectedEntityType = selectedEntityType;
    }
}
