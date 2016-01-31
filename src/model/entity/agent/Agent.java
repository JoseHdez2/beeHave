package model.entity.agent;

import model.entity.Entity;
import model.environment.EnvironmentModel;
import model.movement.MovementType;
import util.typedef.Position;

public abstract class Agent extends Entity{
    
    /**
     * Constructor.
     */
    protected Agent(Entity.type entityType, Position pos){
        super(entityType, pos);
    }
    
    /**
     * Simulate a step of the simulation, as an Agent.
     * Act out a step of the simulation (move, eat, etc.).
     * The current state of the Environment is needed, so we pass the Environment.
     */
    abstract public void simulationStep(EnvironmentModel environment);
    
    protected MovementType pathFinding;    // Current pathfinding algorithm the AgentBee is using.

    /*
     * Getters and setters
     */
    
    public MovementType getPathFinding() {
        return pathFinding;
    }

    public void setPathFinding(MovementType pathFinding) {
        this.pathFinding = pathFinding;
    }
}
