package main;

public class Main {
    public static void main(String[] args) {

        int turnMax = 100;
        
        boolean simulate = true;
        
        while(simulate){
            int turn = 0;
            
            // Cycle can be stopped by reaching a maximum number of turns
            // as well as other interruptor events.
            if (turn >= turnMax){
                simulate = false;
            }
            
            turn++;
        }
    }
}
