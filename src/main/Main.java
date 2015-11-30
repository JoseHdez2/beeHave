package main;

import main.model.sim.Simulation;
import util.Sys;

public class Main {
    public static void main(String[] args) {
        String sep = "--------";
        Sys.outLn(sep, "beeHive", sep);

        Simulation sim = new Simulation();
        sim.runSimulation();
        
        Sys.outLn("Goodbye, beeHive.");
    }
}
