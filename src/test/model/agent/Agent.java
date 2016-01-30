package test.model.agent;

import test.util.typedef.Position;

public abstract class Agent {
    
    protected Position pos; // Current position of the Agent in the Environment.
    
    /**
     * Simulate a step of the simulation, as an Agent.
     * Act out a step of the simulation (move, eat, etc.).
     */
    abstract public void simulationStep();
}
