package test.gui.environment;

import java.util.ArrayList;

import test.model.entity.agent.Agent;
import test.model.entity.object.EnvObject;
import test.util.typedef.Matrix;
import test.util.typedef.Position;

/**
 * @author jose
 *
 *  The model of the environment.
 *  Data and methods that manage the environment are here,
 *  while EnvironmentPanel manages the graphical user interface.
 */
public class EnvironmentModel {

    enum terrainType {
      GRASS,  
    };
    
    int width, height;  // Width and height of the environment.
    Matrix<EnvironmentModel.terrainType> terrain;
    
    ArrayList<Agent> agents; // All agents in the environment.
    ArrayList<EnvObject> objects; // All objects in the environment.
    
    private EnvironmentModel(){
        agents = new ArrayList<Agent>();
        objects = new ArrayList<EnvObject>();
    }
    
    public EnvironmentModel(int width, int height) {
        this();
        this.width = width;
        this.height = height;
    }
    
    public void simulationStep(){
        for(Agent a : agents){
//            a.simulationStep(this);
        }
    }
    
    /**
     * @return Random position inside environment.
     */
    public Position randomPosition(){
        return new Position();
    }
    
    
    
    /*
     * Getters and setters
     */
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Matrix<EnvironmentModel.terrainType> getTerrain() {
        return terrain;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public ArrayList<EnvObject> getObjects() {
        return objects;
    }
}
