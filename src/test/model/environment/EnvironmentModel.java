package test.model.environment;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import test.gui.typedef.EnvironmentLabel;
import test.model.agent.Agent;
import test.model.agent.AgentBee;
import test.util.typedef.Matrix;
import test.util.typedef.Position;


/**
 * @author jose
 *
 *  Matrix representing the environment where the simulation is run.
 *  
 *  Includes all of the Entities (both Agents and Objects) and manages each step of the simulation,
 *  letting each of the agents act a step for each turn of the simulation. 
 */
@SuppressWarnings("serial")
public class EnvironmentModel extends JPanel {
    
    /**
	 * 
	 */
	private int x, y;
    private Matrix<EnvironmentLabel> elements;
    private ArrayList<ArrayList<JLabel>> labels;
    
    // public Point agentPos = new Point(); // Agent's position in x and y.
    
    // New stuff
    private ArrayList<Agent> agents;    // All agents that exist in the environment.
    private ArrayList<EnvObject> objects;   // All objects that exist in the environment.
    
    private ImageIcon agentIcon = new ImageIcon("media/image/bee.png"); // Icon representing the agent.
    private ImageIcon foodIcon = new ImageIcon("media/image/daisy.png"); // Icon representing food.
    private Entity.type clickEffect = Entity.type.AGENT;
    
    EnvironmentModel(){
//        agent = new AgentBee(new Position(1,1));
        
        // New stuff
        agents = new ArrayList<Agent>();
        objects = new ArrayList<EnvObject>();
    }
    
    EnvironmentModel(int width, int height){
        this();
    	setLayout(new GridLayout(width,height));
        this.x = width;
        this.y = height;
        this.elements = new Matrix<EnvironmentLabel>(new EnvironmentLabel[x][y]);
        setLabels(new ArrayList<ArrayList<JLabel>>());
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
        /*
        Random rand = new Random();
        agent.pos.setLocation(rand.nextInt(x), rand.nextInt(y));
        generateFoodPortion();
        generateFoodPortion();
        */
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

	/**
	 * @return the elements
	 */
	public Matrix<EnvironmentLabel> getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(Matrix<EnvironmentLabel> elements) {
		this.elements = elements;
	}

	/**
	 * @return the labels
	 */
	public ArrayList<ArrayList<JLabel>> getLabels() {
		return labels;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(ArrayList<ArrayList<JLabel>> labels) {
		this.labels = labels;
	}

	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * @return the agentIcon
	 */
	public ImageIcon getAgentIcon() {
		return agentIcon;
	}

	/**
	 * @param agentIcon the agentIcon to set
	 */
	public void setAgentIcon(ImageIcon agentIcon) {
		this.agentIcon = agentIcon;
	}

	/**
	 * @return the foodPositions
	 */
	public ArrayList<Point> getFoodPositions() {
		return foodPositions;
	}

	/**
	 * @param foodPositions the foodPositions to set
	 */
	public void setFoodPositions(ArrayList<Point> foodPositions) {
		this.foodPositions = foodPositions;
	}

	/**
	 * @return the foodIcon
	 */
	public ImageIcon getFoodIcon() {
		return foodIcon;
	}

	/**
	 * @param foodIcon the foodIcon to set
	 */
	public void setFoodIcon(ImageIcon foodIcon) {
		this.foodIcon = foodIcon;
	}

	/**
	 * @return the clickEffect
	 */
	public EntityType getClickEffect() {
		return clickEffect;
	}

	/**
	 * @param clickEffect the clickEffect to set
	 */
	public void setClickEffect(EntityType clickEffect) {
		this.clickEffect = clickEffect;
	}

	/**
	 * @return the clickEffectListener
	 */
	public MouseListener getClickEffectListener() {
		return clickEffectListener;
	}

	/**
	 * @param clickEffectListener the clickEffectListener to set
	 */
	public void setClickEffectListener(MouseListener clickEffectListener) {
		this.clickEffectListener = clickEffectListener;
	}
}
