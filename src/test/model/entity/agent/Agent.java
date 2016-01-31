package test.model.entity.agent;

import test.model.entity.Entity;
import test.model.environment.EnvironmentModel;
import test.util.typedef.Position;

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
}
