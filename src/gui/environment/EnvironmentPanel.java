package gui.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
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
    
    enum ClickEffect {
        CREATE,
        MOVE_AGENT,
        MOVE_OBJECT
    }
    
    private ClickEffect clickEffect; // Effect that clicking on a tile will have.
    private Entity.type clickEffectEntity; // For when creating an entity.
    private int clickEffectPointer; // For when moving an entity. Currently selected entity.
    
    private EnvironmentModel env;   // Environment model this interface will represent. 
    
    public Matrix<EnvironmentLabel> envLabels;  // JLabels that will have entities as icons. 
    
    private EnvironmentPanel(){
        clickEffect = ClickEffect.CREATE;
        clickEffectEntity = Entity.type.OBJECT_FLOWER;
        clickEffectPointer = 0;
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
     * Create a single food item randomly in the environment.
     */
    public void generateFoodPortion(){
        EntityTypeMapper.createEntityInto(env, Entity.type.OBJECT_FLOWER, env.randomPosition());
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
            EntityTypeMapper.createEntityInto(env, clickEffectEntity, new Position(x,y)); break;
        case MOVE_AGENT:
            env.getAgents().get(clickEffectPointer).setPos(new Position(x,y)); break;
        case MOVE_OBJECT:
            env.getObjects().get(clickEffectPointer).setPos(new Position(x,y)); break;
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
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            ((EnvironmentLabel)e.getSource()).setHighlighted(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((EnvironmentLabel)e.getSource()).setHighlighted(false);
        }
        
    };
    
    /**
     * Perform a step of the simulation.
     * Allow each of the agents in the environment to act out a single turn.
     * Delegates/cascades into each of the agents in the environment.
     */
    public void simulationStep(){
        for (Agent a : env.getAgents())
             a.simulationStep(env);
        repaint();  // Repaint to show changes.
    }
    
    @Override
    public void paint(Graphics g) {
        for (int j = 0; j < envLabels.height(); j++){
            for (int i = 0; i < envLabels.width(); i++){
                // Erase previous frame.
                envLabels.get(i, j).setIcon(null);
                
                for (EnvObject o : env.getObjects())
                    if (new Position(i,j).equals(o.getPos())) envLabels.get(i, j).setIcon(o.getIcon());
                
                for (Agent a : env.getAgents())
                    if (new Position(i,j).equals(a.getPos())) envLabels.get(i, j).setIcon(a.getIcon());
            }
        }
        super.paint(g);
    }

    /*
     * Getters and setters.
     */
    
    public Matrix<EnvironmentLabel> getEnvLabels() {
        return envLabels;
    }

    public ClickEffect getClickEffect() {
        return clickEffect;
    }

    public void setClickEffect(ClickEffect clickEffect) {
        this.clickEffect = clickEffect;
    }

    public Entity.type getClickEffectEntity() {
        return clickEffectEntity;
    }

    public void setClickEffectEntity(Entity.type clickEffectEntity) {
        this.clickEffectEntity = clickEffectEntity;
    }

    public int getClickEffectPointer() {
        return clickEffectPointer;
    }

    public void setClickEffectPointer(int clickEffectPointer) {
        this.clickEffectPointer = clickEffectPointer;
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
}
