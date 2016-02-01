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
import test.agent.Flower;
import test.agent.Hive;

/**
 *  Panel que representa el entorno (cuadricula).
 */
public class EnvironmentPanel extends JPanel {
    
    /**
	 * 
	 */
	private static Integer DEFAULT_BEES = 25;
	private static final long serialVersionUID = 1L;
	private int x, y;
    private Matrix<EnvironmentLabel> elements;
    private ArrayList<ArrayList<JLabel>> labels;
    private ArrayList<Agent> allAgents;
    private Hive hive;
    private ImageIcon agentIcon; // Icon representing the agent.
    private ArrayList<Flower> foodPositions;
    private ImageIcon foodIcon; // Icon representing food.
    private EnvironmentEntity clickEffect;
    private AStar searchAlgorithm;
    private ImageIcon hiveIcon;
    
    EnvironmentPanel(int width, int height){
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
//        agent.pos.setLocation(rand.nextInt(x), rand.nextInt(y));
        getHive().setPos(rand.nextInt(x), rand.nextInt(y));
		for (Agent agent : allAgents) {
			agent.getPos().setLocation(rand.nextInt(x), rand.nextInt(y));
			agent.setHivePos(getHive().getPos());
		}
		
        for (int i = 0; i < 50; i++) {
    		generateFoodPortion();
		}

    }
    
    public void initialize(int width, int height){
    	setSearchAlgorithm(new AStar(width, height));
    	setAllAgents(new ArrayList<Agent>(), DEFAULT_BEES);
    	setHive(new Hive());
	    setAgentIcon(new ImageIcon("media/image/bee.png")); // Icon representing the agent.
	    setFoodPositions(new ArrayList<Flower>());
	    setFoodIcon(new ImageIcon("media/image/daisy.png")); // Icon representing food.
	    setHiveIcon(new ImageIcon("media/image/beehive.png"));
	    setClickEffect(EnvironmentEntity.FOOD);
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
        Flower auxFlower = new Flower();
        auxFlower.setFlowerPosition(new Point(rand.nextInt(x), rand.nextInt(y)));
        foodPositions.add(auxFlower);
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
            case AGENT: 
            	Agent aux = new Agent(el.x, el.y, getHive().getPos().x, getHive().getPos().y);
            	getAllAgents().add(aux); 
            	break;
            case FOOD: 
            	Flower auxFlower = new Flower();
                auxFlower.setFlowerPosition(new Point(el.x, el.y));
                foodPositions.add(auxFlower);
                break;
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
    	for (Agent agent : getAllAgents()) {
    	   	agent.moveAgent(this);
    	   	checkBeeFlower(); 	
		}
    	
        repaint();  // Repaint to show changes.
    }
    
    @Override
    public void paint(Graphics g) {
        for (int j = 0; j < elements.getHeight(); j++){
            for (int i = 0; i < elements.getWidth(); i++){
                // Erase previous frame.
                elements.get(i, j).setIcon(null);
                
                // Draw food.
                for (Flower food : getFoodPositions())
                    if (i == food.getFlowerPosition().getX() && j == food.getFlowerPosition().getY()) elements.get(i, j).setIcon(foodIcon);
                
                // Draw agent.
                for (Agent agent : getAllAgents()) {
                    if (i == agent.getPos().getX() && j == agent.getPos().getY()) elements.get(i, j).setIcon(agentIcon);
				}
                
                //Draw hive
                if (getHive().getPos().getX() == i && getHive().getPos().getY() == j) {
					getElements().get(i, j).setIcon(hiveIcon);
				}
                
                
                //if (i == agent.pos.getX() && j == agent.pos.getY()) elements.get(i, j).setIcon(agentIcon);
            }
        }
        super.paint(g);
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
	public ArrayList<Flower> getFoodPositions() {
		return foodPositions;
	}

	/**
	 * @param foodPositions the foodPositions to set
	 */
	public void setFoodPositions(ArrayList<Flower> foodPositions) {
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
	public EnvironmentEntity getClickEffect() {
		return clickEffect;
	}

	/**
	 * @param clickEffect the clickEffect to set
	 */
	public void setClickEffect(EnvironmentEntity clickEffect) {
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

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the allAgents
	 */
	public ArrayList<Agent> getAllAgents() {
		return allAgents;
	}

	/**
	 * @param allAgents the allAgents to set
	 */
	public void setAllAgents(ArrayList<Agent> allAgents, Integer numberOfBees) {
		this.allAgents = allAgents;
		for (int i = 0; i < numberOfBees; i++) {
			this.allAgents.add(new Agent());
		}
		
	}

	/**
	 * @return the hive
	 */
	public Hive getHive() {
		return hive;
	}

	/**
	 * @param hive the hive to set
	 */
	public void setHive(Hive hive) {
		this.hive = hive;
	}

	/**
	 * @param allAgents the allAgents to set
	 */
	public void setAllAgents(ArrayList<Agent> allAgents) {
		this.allAgents = allAgents;
	}
	
	public void checkBeeFlower(){
		for (Agent agent : getAllAgents()) {
			for (Flower flower : getFoodPositions()) {
				if (flower.getFlowerPosition().equals(agent.getPos()) && agent.getBehaviour() != Agent.behaviourType.RETURN) {
					if (flower.getPollen() != 0) {
						agent.getPollen(flower);
						agent.setBehaviour(Agent.behaviourType.RETURN);
						getSearchAlgorithm().knowledgeInit(agent.getPos(), getHive().getPos());
						agent.setPathToHive(getSearchAlgorithm().run(agent.getPos(), getHive().getPos()));
						break;
					}
					else {
						agent.setBehaviour(Agent.behaviourType.SCOUT);
						break;
					}	
				}
			}
			if (agent.getBehaviour().equals(Agent.behaviourType.IDLE)) {
				agent.communicate(getHive());
			}
			if (agent.getBestFlower().getPollen() == 0 && (agent.getBehaviour().equals(Agent.behaviourType.IDLE) || agent.getBehaviour().equals(Agent.behaviourType.GO_TO_POINT) &&
					getHive().getPos().equals(agent.getPos()))) {
				agent.setBehaviour(Agent.behaviourType.SCOUT);
			}
			if (agent.getBehaviour() == Agent.behaviourType.IDLE && getHive().getBeesInside().contains(agent) && getHive().getBeesInside().size() >= 2) {
				getSearchAlgorithm().knowledgeInit(agent.getPos(), agent.getBestFlower().getFlowerPosition());
				agent.setPathToFlower(getSearchAlgorithm().run(agent.getPos(), agent.getBestFlower().getFlowerPosition()));	
				agent.setBehaviour(Agent.behaviourType.GO_TO_POINT);
				getHive().getBeesInside().remove(agent);
				break;
			}
			
			if (getHive().getPos().equals(agent.getPos()) && (agent.getBehaviour().equals(Agent.behaviourType.RETURN))) {
				agent.setBehaviour(Agent.behaviourType.IDLE);
				if (!getHive().getBeesInside().contains(agent)) {
					getHive().getBeesInside().add(agent);
				}
				agent.unloadPollen(getHive());
			}
			if (agent.getBehaviour() == Agent.behaviourType.GO_TO_POINT && agent.getPathToFlower().isEmpty()) {
				getSearchAlgorithm().knowledgeInit(agent.getPos(), agent.getBestFlower().getFlowerPosition());
				agent.setPathToFlower(getSearchAlgorithm().run(agent.getPos(), agent.getBestFlower().getFlowerPosition()));	
			}
		}
	}

	/**
	 * @return the searchAlgorithm
	 */
	public AStar getSearchAlgorithm() {
		return searchAlgorithm;
	}

	/**
	 * @param searchAlgorithm the searchAlgorithm to set
	 */
	public void setSearchAlgorithm(AStar searchAlgorithm) {
		this.searchAlgorithm = searchAlgorithm;
	}

	/**
	 * @return the hiveIcon
	 */
	public ImageIcon getHiveIcon() {
		return hiveIcon;
	}

	/**
	 * @param hiveIcon the hiveIcon to set
	 */
	public void setHiveIcon(ImageIcon hiveIcon) {
		this.hiveIcon = hiveIcon;
	}
}
