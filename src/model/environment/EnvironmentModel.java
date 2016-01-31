package model.environment;

import java.util.ArrayList;

import model.entity.Entity;
import model.entity.EntityTypeMapper;
import model.entity.agent.Agent;
import model.entity.agent.AgentBee;
import model.entity.object.EnvObject;
import util.math.RandomNum;
import util.typedef.Matrix;
import util.typedef.Position;

/**
 *  The model of the environment.
 *  Data and methods that manage the environment are here,
 *  while EnvironmentPanel manages the graphical user interface.
 *  
 * @author jose
 */
public class EnvironmentModel {

    public enum Terrain {
      GRASS,
      SOIL
    };
    
    int width, height;  // Width and height of the environment.
    Matrix<EnvironmentModel.Terrain> terrain;
    
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
        this.terrain = new Matrix<EnvironmentModel.Terrain>(new EnvironmentModel.Terrain[width][height]);
        
        for (int j = 0; j < terrain.height(); j++){
            for (int i = 0; i < terrain.width(); i++){
                switch(RandomNum.randInt(0, 1)){
                case 0: terrain.set(i, j, Terrain.GRASS); break;
                case 1: terrain.set(i, j, Terrain.SOIL); break;
                }
            }
        }
        
        // Two ways of doing the same: creating an AgentBee into the environment.
        agents.add((AgentBee)EntityTypeMapper.createEntity(Entity.type.AGENT_BEE, randomPosition()));
        EntityTypeMapper.createEntityInto(this, Entity.type.AGENT_BEE, randomPosition());
        
        for(int i = 0; i < 5; i++)
            EntityTypeMapper.createEntityInto(this, Entity.type.OBJECT_FLOWER, randomPosition());
    }
    
    public void simulationStep(){
        for(Agent a : agents){
            a.simulationStep(this);
        }
    }
    
    /**
     * @return Random position inside the bounds of this environment.
     */
    public Position randomPosition(){
        return new Position(RandomNum.randInt(0, width-1), RandomNum.randInt(0, height-1));
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

    public Matrix<EnvironmentModel.Terrain> getTerrain() {
        return terrain;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public ArrayList<EnvObject> getObjects() {
        return objects;
    }
}
