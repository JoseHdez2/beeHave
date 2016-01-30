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
import test.model.entity.agent.AgentBee;
import test.util.typedef.Matrix;
import test.util.typedef.Position;


/**
 *  Panel que representa el entorno (cuadricula).
 */
public class EnvironmentPanel extends JLayeredPane {
    
    // TODO: default serial id
    private static final long serialVersionUID = 1L;
    
    private int x, y;
    public Matrix<JPanel> tiles;
    public Matrix<EnvironmentLabel> elements;
    ArrayList<ArrayList<JLabel>> labels = new ArrayList<ArrayList<JLabel>>();
    
    // If false, show background image. Else show checkerboard pattern.
    boolean showGrid = true;
    
    // public Point agentPos = new Point(); // Agent's position in x and y.
    AgentBee agent = new AgentBee(new Position(0,0));
    ImageIcon agentIcon = new ImageIcon("res/image/bee.png"); // Icon representing the agent.
    
    public ArrayList<Point> foodPositions = new ArrayList<Point>();
    ImageIcon foodIcon = new ImageIcon("res/image/daisy.png"); // Icon representing food.
    
    public Entity.type clickEffect = Entity.type.OBJECT_FLOWER;
    
    int gridTileSize = 50; // Tile width and height, in pixels.
    
    public EnvironmentPanel(int width, int height){
        setLayout(new GridLayout(width,height));
        this.x = width;
        this.y = height;
        this.elements = new Matrix<EnvironmentLabel>(new EnvironmentLabel[x][y]);
        
        this.setSize(gridTileSize * x, gridTileSize * y);
        
        // Add background tiles
        for (int j = 0; j < elements.height(); j++){
            for (int i = 0; i < elements.width(); i++){
                Color color = (i+j)%2 == 0 ? new Color(150,150,150) : new Color(200,200,200);
                EnvironmentLabel label = new EnvironmentLabel("b",i,j);
                if (showGrid) label.setOpaque(true);
                label.setBackground(color);
                
                label.addMouseListener(clickEffectListener);
                
                elements.set(i, j, label);
                add(elements.get(i, j));  // Add into background layer
            }
        }
        
        Random rand = new Random();
        agent.setPos(new Position(rand.nextInt(x), rand.nextInt(y)));
        generateFoodPortion();
        generateFoodPortion();
        this.repaint();
    }
    
    /**
     * Create a single food item randomly in the environment.
     */
    public void generateFoodPortion(){
        Random rand = new Random();
        foodPositions.add(new Point(rand.nextInt(x), rand.nextInt(y)));
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
        for (int j = 0; j < elements.height(); j++){
            for (int i = 0; i < elements.width(); i++){
                // Erase previous frame.
                elements.get(i, j).setIcon(null);
                
                // Draw food.
                for (Point food : foodPositions)
                    if (i == food.x && j == food.y) elements.get(i, j).setIcon(foodIcon);
                
                // Draw agent.
                if (i == agent.getPosX() && j == agent.getPosY()) elements.get(i, j).setIcon(agentIcon);
            }
        }
        super.paint(g);
    }

    public AgentBee getAgent() {
        return agent;
    }

    public Matrix<EnvironmentLabel> getElements() {
        return elements;
    }

    public ArrayList<Point> getFoodPositions() {
        return foodPositions;
    }
}
