package test.gui;

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

import test.agent.Agent;
import test.model.environment.EntityType;
import test.util.typedef.Matrix;

/**
 *  Panel que representa el entorno (cuadricula).
 */
public class EnvironmentPanel2 extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int x, y;
    private Matrix<EnvironmentLabel> elements;
    private ArrayList<ArrayList<JLabel>> labels;
    
    private ArrayList<Agent> allAgents;
    private Agent agent;
    private ImageIcon agentIcon; // Icon representing the agent.
    private ArrayList<Point> foodPositions;
    private ImageIcon foodIcon; // Icon representing food.
    private EntityType clickEffect;
    
    EnvironmentPanel2(int width, int height){
        initialize(width, height);
        setLayout(new GridLayout(width,height));
        for (int j = 0; j < elements.getHeight(); j++){
            for (int i = 0; i < elements.getWidth(); i++){
                Color color = (i+j)%2 == 0 ? new Color(150,150,150) : new Color(200,200,200);
                EnvironmentLabel label = new EnvironmentLabel("",i,j);
                label.setOpaque(true);
                label.setBackground(color);
                label.addMouseListener(clickEffectListener);
                getElements().set(i, j, label);
                add(elements.get(i, j));
            }
        }
        Random rand = new Random();
        agent.pos.setLocation(rand.nextInt(x), rand.nextInt(y));
        generateFoodPortion();
        generateFoodPortion();
    }
    
    public void initialize(int width, int height){
        setAgent(new Agent()); 
        setAgentIcon(new ImageIcon("media/image/bee.png")); // Icon representing the agent.
        setFoodPositions(new ArrayList<Point>());
        setFoodIcon(new ImageIcon("media/image/daisy.png")); // Icon representing food.
        setClickEffect(EntityType.FOOD);
        setX(width);
        setY(height);
        setElements(new Matrix<EnvironmentLabel>(new EnvironmentLabel[x][y]));
        setLabels(new ArrayList<ArrayList<JLabel>>());
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
            case AGENT: agent.pos.setLocation(el.x, el.y); break;
            case FOOD: foodPositions.add(new Point(el.x, el.y)); break;
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
        for (int j = 0; j < elements.getHeight(); j++){
            for (int i = 0; i < elements.getWidth(); i++){
                // Erase previous frame.
                elements.get(i, j).setIcon(null);
                
                // Draw food.
                for (Point food : foodPositions)
                    if (i == food.x && j == food.y) elements.get(i, j).setIcon(foodIcon);
                
                // Draw agent.
                if (i == agent.pos.getX() && j == agent.pos.getY()) elements.get(i, j).setIcon(agentIcon);
            }
        }
        super.paint(g);
    }
}