package test.model.agent;

import test.gui.environment.EnvironmentPanel;
import test.model.environment.Entity;
import test.util.typedef.Position;

public abstract class Agent extends Entity{
    
    /**
     * Constructor.
     */
    protected Agent(Position pos, Entity.type entityType){
        super(pos, entityType);
    }
    
    /**
     * Simulate a step of the simulation, as an Agent.
     * Act out a step of the simulation (move, eat, etc.).
     * The current state of the Environment is needed, so we pass the Environment.
     */
    abstract public void simulationStep(EnvironmentPanel environment);

    /*
     * Handmade getters and setters.
     */
    
    public Integer getPosX() {
        return getPos().getX();
    }
    public Integer getPosY() {
        return getPos().getY();
    }
}
