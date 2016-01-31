package model.entity.agent;

import model.entity.Entity;
import model.environment.EnvironmentModel;
import model.movement.MovementType;
import model.movement.RandomMove;
import util.typedef.Position;

public class AgentBee extends Agent {
    
    private static BehaviourType defaultInitialBehaviour = BehaviourType.SCOUT;
    int pollen; // Amount of pollen the bee has.
    
    /*
     * Constructors
     */
    
    public AgentBee(Position pos, BehaviourType behaviour) {
        super(Entity.type.AGENT_BEE, pos);
        this.behaviour = behaviour;
    }
    
    public AgentBee(Position pos) {
        this(pos, defaultInitialBehaviour);
    }
    
    public static enum BehaviourType{
        SCOUT,
        RETURN,
        INFORM,
        IDLE
    }
    
    private BehaviourType behaviour;    // Current behavior of the AgentBee.
    public MovementType pathFinding;    // Current pathfinding algorithm the AgentBee is using.
    public Position hivePos;    // Position of the bee's hive.
    
    public void moveAgent(EnvironmentModel env){
        
        if (getBehaviour() == BehaviourType.SCOUT) {
            pathFinding = new RandomMove();
        }
        
        // Skip moving (do nothing) if we're already on food.
        // TODO: find the food item and remove from list: an 'eat' turn.
        
        /*
        if (panel.getFoodPositions().contains(pos)){
            setBehaviour(BehaviourType.RETURN);
            returnToHive();
        }
        */
        
        // The pathfinding module decides the next step and executes it.
        pathFinding.nextMove(this, env);
    }
    
    
    public void returnToHive(){
        
    }

    /**
     * @return the behaviour
     */
    public BehaviourType getBehaviour() {
        return behaviour;
    }

    /**
     * @param behaviour the behaviour to set
     */
    public void setBehaviour(BehaviourType behaviour) {
        this.behaviour = behaviour;
    }
    
    public void setLocation(Integer x, Integer y){
        setPos(new Position(x,y));
    }

    @Override
    public void simulationStep(EnvironmentModel environment) {
        moveAgent(environment);
    }
}