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
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import test.model.entity.Entity;
import test.model.entity.agent.Agent;
import test.model.entity.agent.AgentBee;
import test.model.entity.object.EnvObject;
import test.util.typedef.Matrix;
import test.util.typedef.Position;

/**
 *  JPanel acting as GUI for the simulation environment.
 *  Visually represents an EnvironmentModel.
 */
public class EnvironmentPanel extends JLayeredPane {
    
    // TODO: default serial id
    private static final long serialVersionUID = 1L;
    
    private EnvironmentModel env;   // Environment. 
    
//    private int x, y;
    public Matrix<EnvironmentLabel> envLabels;
    
    // If false, show background image. Else show checkerboard pattern.
    boolean showGrid = true;
    
    // TODO: delete this old hat
    AgentBee agent; // Single agent
    ImageIcon agentIcon; // Icon representing the agent.
    
    public ArrayList<Point> foodPositions; // Positions for food
    ImageIcon foodIcon; // Icon representing food.
    
    public Entity.type clickEffect; // Effect that clicking on a tile will have.
    
    private static int gridTileSize = 50; // Tile width and height, in pixels.
    
    private EnvironmentPanel(){
        clickEffect = Entity.type.OBJECT_FLOWER;
        
        agent = new AgentBee(new Position(0,0));
        agentIcon = new ImageIcon("res/image/bee.png");
        foodPositions = new ArrayList<Point>();
        foodIcon = new ImageIcon("res/image/daisy.png");
    }
    
    public EnvironmentPanel(int width, int height){
        this();
        env = new EnvironmentModel(width, height);
        // Temp variables
        int x = env.getWidth();
        int y = env.getHeight();
        
        this.setSize(gridTileSize * x, gridTileSize * y);
        setLayout(new GridLayout(width,height));
        
        this.envLabels = new Matrix<EnvironmentLabel>(new EnvironmentLabel[x][y]);
        
        // Add tiles
        for (int j = 0; j < envLabels.height(); j++){
            for (int i = 0; i < envLabels.width(); i++){
                Color color = (i+j)%2 == 0 ? new Color(150,150,150) : new Color(200,200,200);
                EnvironmentLabel label = new EnvironmentLabel("b",i,j);
                if (showGrid) label.setOpaque(true);
                label.setBackground(color);
                
                label.addMouseListener(clickEffectListener);
                
                envLabels.set(i, j, label);
                add(envLabels.get(i, j));  // Add into background layer
            }
        }
        /*
        Random rand = new Random();
        agent.setPos(new Position(rand.nextInt(x), rand.nextInt(y)));
        generateFoodPortion();
        generateFoodPortion();
        */
        this.repaint();
    }
    
    /**
     * Create a single food item randomly in the environment.
     */
    public void generateFoodPortion(){
        Random rand = new Random();
//        foodPositions.add(new Point(rand.nextInt(x), rand.nextInt(y)));
    }
    
    /**
     * Mouse listener for when a tile of the grid is clicked.
     * Determines which effect is produced by looking at the clickEffect attribute of this class.
     */
    MouseListener clickEffectListener = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            EnvironmentLabel el = ((EnvironmentLabel)e.getSource());
            switch(clickEffect){
            case AGENT_BEE: agent.setPos(new Position(el.x, el.y)); break;
            case OBJECT_FLOWER: foodPositions.add(new Point(el.x, el.y)); break;
            case AGENT_WASP: break;
            case OBJECT_BEEHIVE: break;
            default: break;
            }
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
     */
    public void simulationStep(){
        agent.moveAgent(this);
        repaint();  // Repaint to show changes.
    }
    
    @Override
    public void paint(Graphics g) {
        for (int j = 0; j < envLabels.height(); j++){
            for (int i = 0; i < envLabels.width(); i++){
                // Erase previous frame.
                envLabels.get(i, j).setIcon(null);
                
                for (Agent a : env.getAgents())
                    if (new Position(i,j) == a.getPos()) envLabels.get(i, j).setIcon(a.getIcon());
//                    if (i == a.getPosX() && j == agent.getPosY()) envLabels.get(i, j).setIcon(a.getIcon());
                
                for (EnvObject o : env.getObjects())
                    if (i == o.getPos().getX() && j == agent.getPos().getY()) envLabels.get(i, j).setIcon(o.getIcon());
                
                /*
                // Draw food.
                for (Point food : foodPositions)
                    if (i == food.x && j == food.y) envLabels.get(i, j).setIcon(foodIcon);
                
                // Draw agent.
                if (i == agent.getPosX() && j == agent.getPosY()) envLabels.get(i, j).setIcon(agentIcon);
                */
            }
        }
        super.paint(g);
    }

    public AgentBee getAgent() {
        return agent;
    }

    public Matrix<EnvironmentLabel> getElements() {
        return envLabels;
    }

    public ArrayList<Point> getFoodPositions() {
        return foodPositions;
    }
}
