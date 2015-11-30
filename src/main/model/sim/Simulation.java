package main.model.sim;

import util.Sys;

public class Simulation {
    
    int turn = 0;   // Current turn, in case the simulation is running.
    int turnMax = 100;  // Maximum turns the simulation will run for.
    
    boolean SHOW_CONSOLE = true;
    
    public void beginSimulation(){
        turn = 0;
        runSimulation();
    }
    
    public void runSimulation(){
        boolean simulate = true;
        
        while(simulate){
            
            // Simulate one turn in the world.
            simulateTurn();
            
            // Cycle can be stopped by reaching a maximum number of turns
            // as well as other interruptor events.
            if (checkSimulationStop()){
                simulate = false;
            } else {
                turn++;
            }
        }
    }
    
    /**
     * @return  Whether a simulation stop event has been triggered in the current turn.
     */
    protected boolean checkSimulationStop(){
        if (turn >= turnMax) return true;
        // if numero de abejas vivas == 0, etc.
        return false;
    }
    
    /**
     * Simulate a turn (time unit) in our simulated environment.
     */
    protected void simulateTurn(){
        Sys.out("turn: ",turn);
    }
    
    // TODO: protected void showMyself()
    
    public String 
}
