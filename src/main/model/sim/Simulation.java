package main.model.sim;

public class Simulation {
    
    int turnMax = 100;
    
    public void runSimulation(){
        boolean simulate = true;
        
        while(simulate){
            int turn = 0;
            
            // Simulate one turn in the world.
            simulateTurn();
            
            
            // Cycle can be stopped by reaching a maximum number of turns
            // as well as other interruptor events.
            if (turn >= turnMax){
                simulate = false;
            }
            
            turn++;
        }
    }
    
    public void simulateTurn(){
        
    }
    
}
