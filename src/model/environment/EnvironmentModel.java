package model.environment;

import java.util.ArrayList;

import model.entity.Entity;
import model.entity.EntityTypeMapper;
import model.entity.agent.Agent;
import model.entity.agent.AgentBee;
import model.entity.object.EnvObject;
import model.entity.object.ObjectBeehive;
import model.entity.object.ObjectFlower;
import util.math.RandomNum;
import util.movement.AStar;
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
    
    private AStar searchAlgorithm; // TODO
    
    private EnvironmentModel(){
        agents = new ArrayList<Agent>();
        objects = new ArrayList<EnvObject>();
    }
    
    public EnvironmentModel(int width, int height) {
        this();
        this.width = width;
        this.height = height;
        this.terrain = TerrainGenerator.plain(width, height, EnvironmentModel.Terrain.GRASS);
//        this.terrain = TerrainGenerator.random(width, height);
        
        EntityTypeMapper.createEntityInto(this, Entity.type.OBJECT_BEEHIVE, randomPosition());
        
        for (int i = 0; i < 25; i++)
            EntityTypeMapper.createEntityInto(this, Entity.type.AGENT_BEE, randomPosition());
        
        for(int i = 0; i < 50; i++)
            EntityTypeMapper.createEntityInto(this, Entity.type.OBJECT_FLOWER, randomPosition());
    }
    
    public void simulationStep(){
        for(Agent a : agents)
            a.simulationStep(this);
    }
    
    /**
     * @return Random position inside the bounds of this environment.
     */
    public Position randomPosition(){
        return new Position(RandomNum.randInt(0, width-1), RandomNum.randInt(0, height-1));
    }
    /*
     * Special handmade getters
     */
    
    /**
     * @param entityName Name of the entity that we want.
     * @return Entity or null
     */
    public Entity getEntity(String entityName){
        for (Entity e : objects)
            if(entityName.equals(e.getName())) return e;
        for (Entity e : agents)
            if(entityName.equals(e.getName())) return e;
        return null;
    }
    
    /**
     * @param pos Position of which we want all entities.
     * @return All entities that are in that position.
     */
    public ArrayList<Entity> getEntities(Position pos){
        ArrayList<Entity> entitiesInPos = new ArrayList<Entity>();
        for (Entity e : objects)
            if(pos.equals(e.getPos())) entitiesInPos.add(e);
        for (Entity e : agents)
            if(pos.equals(e.getPos())) entitiesInPos.add(e);
        return entitiesInPos;
    }
    
    /**
     * @param entityType
     * @return All entities in the environment of type entityType. 
     */
    public ArrayList<Entity> getEntities(Entity.type entityType){
        ArrayList<Entity> entitiesOfType = new ArrayList<Entity>();
        for (Entity e : objects)
            if(e.getEntityType() == entityType) entitiesOfType.add(e);
        for (Entity e : agents)
            if(e.getEntityType() == entityType) entitiesOfType.add(e);
        return entitiesOfType;
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
