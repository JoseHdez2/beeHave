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
        checkBeeFlower();
    }
    
    /**
     * @return Random position inside the bounds of this environment.
     */
    public Position randomPosition(){
        return new Position(RandomNum.randInt(0, width-1), RandomNum.randInt(0, height-1));
    }
    
    // TODO
    private void checkBeeFlower(){
        for (Agent a : agents) {
            if (a.getEntityType() != Entity.type.AGENT_BEE) return;
            AgentBee bee = (AgentBee)a;
            for (EnvObject f : objects){
                if (f.getEntityType() != Entity.type.OBJECT_FLOWER) return;
                ObjectFlower flower = (ObjectFlower)f;
                if (flower.getPos().equals(bee.getPos()) && bee.getBehaviour() != AgentBee.behaviourType.RETURN) {
                    if (flower.getPollen() != 0) {
                        bee.getPollen(flower);
                        bee.setBehaviour(AgentBee.behaviourType.RETURN);
                        searchAlgorithm.knowledgeInit(bee.getPos(), ((ObjectBeehive)objects.get(0)).getPos());
                        bee.setPathToHive(searchAlgorithm.run(bee.getPos(), ((ObjectBeehive)objects.get(0)).getPos()));
                        break;
                    }
                    else {
                        bee.setBehaviour(AgentBee.behaviourType.SCOUT);
                        break;
                    }   
                }
            }
            if (bee.getBehaviour().equals(AgentBee.behaviourType.IDLE)) {
                bee.communicate((ObjectBeehive)objects.get(0));
            }
            if (bee.getBestFlower().getPollen() == 0 && (bee.getBehaviour().equals(AgentBee.behaviourType.IDLE) || bee.getBehaviour().equals(AgentBee.behaviourType.GO_TO_POINT) &&
                    objects.get(0).getPos().equals(bee.getPos()))) {
                bee.setBehaviour(AgentBee.behaviourType.SCOUT);
            }
            if (bee.getBehaviour() == AgentBee.behaviourType.IDLE && ((ObjectBeehive)objects.get(0)).getBeesInside().contains(bee) && ((ObjectBeehive)objects.get(0)).getBeesInside().size() >= 2) {
                searchAlgorithm.knowledgeInit(bee.getPos(), bee.getBestFlower().getPos());
                bee.setPathToFlower(searchAlgorithm.run(bee.getPos(), bee.getBestFlower().getPos())); 
                bee.setBehaviour(AgentBee.behaviourType.GO_TO_POINT);
                ((ObjectBeehive)objects.get(0)).getBeesInside().remove(bee);
                break;
            }
            
            if (((ObjectBeehive)objects.get(0)).getPos().equals(bee.getPos()) && (bee.getBehaviour().equals(AgentBee.behaviourType.RETURN))) {
                bee.setBehaviour(AgentBee.behaviourType.IDLE);
                if (!((ObjectBeehive)objects.get(0)).getBeesInside().contains(bee)) {
                    ((ObjectBeehive)objects.get(0)).getBeesInside().add(bee);
                }
                bee.unloadPollen(((ObjectBeehive)objects.get(0)));
            }
            if (bee.getBehaviour() == AgentBee.behaviourType.GO_TO_POINT && bee.getPathToFlower().isEmpty()) {
                searchAlgorithm.knowledgeInit(bee.getPos(), bee.getBestFlower().getPos());
                bee.setPathToFlower(searchAlgorithm.run(bee.getPos(), bee.getBestFlower().getPos())); 
            }
        }
    }
    
    /*
     * Special handmade getters
     */
    
    public Entity getEntity(String entityName){
        for (Entity e : objects)
            if(entityName.equals(e.getName())) return e;
        for (Entity e : agents)
            if(entityName.equals(e.getName())) return e;
        return null;
    }
    
    /**
     * @param pos
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
