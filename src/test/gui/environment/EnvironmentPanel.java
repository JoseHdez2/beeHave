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
import javax.swing.JPanel;

import test.model.agent.Agent;
import test.model.agent.AgentBee;
import test.model.environment.Entity;
import test.model.environment.Entity.type;
import test.util.math.RandomNum;
import test.util.typedef.Matrix;
import test.util.typedef.Position;

/**
 *  Panel que representa el entorno (cuadricula).
 */
public class EnvironmentPanel extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int x, y;
    private Matrix<EnvironmentLabel> elements;
    private ArrayList<ArrayList<JLabel>> labels;
    
    private ArrayList<Agent> allAgents;
    private AgentBee agent;
    private ImageIcon agentIcon; // Icon representing the agent.
    private ArrayList<Point> foodPositions;
    private ImageIcon foodIcon; // Icon representing food.
    
    // Default initial value for clickEffect.
    static private Entity.type defaultEntityType = type.AGENT_BEE;
    // Effect when mouse is clicked.
    private Entity.type clickEffect;
    
    private EnvironmentPanel(){
        foodPositions = new ArrayList<Point>();
        setClickEffect(defaultEntityType);
        labels = new ArrayList<ArrayList<JLabel>>();
    }
    
    public EnvironmentPanel(int width, int height){
        this();
        this.x = width;
        this.y = height;
        agent = new AgentBee(new Position(RandomNum.randInt(0, x), RandomNum.randInt(0, y)));
        elements = new Matrix<EnvironmentLabel>(new EnvironmentLabel[x][y]);
        
        setLayout(new GridLayout(width,height));
        for (int j = 0; j < elements.getHeight(); j++){
            for (int i = 0; i < elements.getWidth(); i++){
                Color color = (i+j)%2 == 0 ? new Color(150,150,150) : new Color(200,200,200);
                EnvironmentLabel label = new EnvironmentLabel("",i,j);
                label.setOpaque(true);
                label.setBackground(color);
                label.addMouseListener(clickEffectListener);
                elements.set(i, j, label);
                add(elements.get(i, j));
            }
        }
        Random rand = new Random();
        agent.setPos(new Position(rand.nextInt(x), rand.nextInt(y)));
        generateFoodPortion();
        generateFoodPortion();
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
            case AGENT_BEE: agent.setPos((new Position(el.x, el.y))); break;
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
        agent.simulationStep(this);
        repaint();  // Repaint to show changes.
    }
    
    
    public void setClickEffect(Entity.type clickEffect) {
        this.clickEffect = clickEffect;
    }
    
    @Override
    public void paint(Graphics g) {
        for (int j = 0; j < elements.getHeight(); j++){
            for (int i = 0; i < elements.getWidth(); i++){
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
    
    // TODO: quitar
    /**
     * @return the foodPositions
     */
    public ArrayList<Point> getFoodPositions() {
        return foodPositions;
    }

    public AgentBee getAgent() {
        return agent;
    }
}
