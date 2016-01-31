package test.gui.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

import test.model.entity.Entity;
import test.model.entity.EntityType;
import test.model.entity.EntityTypeMapper;
import test.model.entity.agent.Agent;
import test.model.entity.object.EnvObject;
import test.model.environment.EnvironmentModel;
import test.util.typedef.Matrix;
import test.util.typedef.Position;

/**
 *  JPanel acting as GUI for the simulation environment.
 *  Visually represents an EnvironmentModel.
 *  
 *  @author jose
 */
public class EnvironmentPanel extends JLayeredPane {
    
    // TODO: default serial id
    private static final long serialVersionUID = 1L;
    
    private static Color GRAY_LITE = new Color(200,200,200);
    private static Color GRAY_DARK = new Color(150,150,150);
    
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
                label.setBackground(tileColor(i,j));
                
                label.addMouseListener(clickEffectListener);
                
                envLabels.set(i, j, label);
                add(envLabels.get(i, j));  // Add into background layer
            }
        }
        this.repaint();
    }
    
    /**
     * Take tile position and EnvironmentModel to determine 
     * @param i Column (tile position)
     * @param j Row (tile position)
     * @return  Corresponding.
     */
    private Color tileColor(int i, int j){
        Color color = (i+j)%2 == 0 ? GRAY_DARK : GRAY_LITE;
        // TODO: Take terrain into account?
        return color;
    }
    
    /**
     * Create a single food item randomly in the environment.
     */
    public void generateFoodPortion(){
        EntityTypeMapper.createEntityInto(env, Entity.type.OBJECT_FLOWER, env.randomPosition());
    }
    
    // TODO: put into EnvModel? probably not since it uses clickEffect variables.
    
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
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
        
    };
    
    /**
     * Perform a step of the simulation.
     * Allow each of the agents in the environment to act out a single turn.
     * Delegates/cascades into each of the agents in the environment.
     */
    public void simulationStep(){
        for (Agent a : env.getAgents())
            // a.simulationStep(env);
        repaint();  // Repaint to show changes.
    }
    
    @Override
    public void paint(Graphics g) {
        for (int j = 0; j < envLabels.height(); j++){
            for (int i = 0; i < envLabels.width(); i++){
                // Erase previous frame.
                envLabels.get(i, j).setIcon(null);
                
                for (Agent a : env.getAgents())
                    if (new Position(i,j).equals(a.getPos())) envLabels.get(i, j).setIcon(a.getIcon());
                
                for (EnvObject o : env.getObjects())
                    if (new Position(i,j).equals(o.getPos())) envLabels.get(i, j).setIcon(o.getIcon());
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
}
