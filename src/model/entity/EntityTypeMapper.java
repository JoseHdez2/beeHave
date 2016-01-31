package model.entity;

import javax.swing.ImageIcon;

import model.entity.agent.AgentBee;
import model.entity.agent.AgentWasp;
import model.entity.object.ObjectBeehive;
import model.entity.object.ObjectFlower;
import model.environment.EnvironmentModel;
import util.typedef.Position;


/**
 * @author jose
 *
 *  Collection of static functions that receive an Entity.type and return a corresponding object.
 */
public abstract class EntityTypeMapper {
    
    /**
     * @param entityType    Type of Entity.
     * @param pos   Position of Entity.
     * @return  Entity of specified type and position.
     */
    public static Entity createEntity(Entity.type entityType, Position pos){
        switch(entityType){
        case AGENT_BEE: return new AgentBee(pos);
        case AGENT_WASP: return new AgentWasp(pos);
        case OBJECT_BEEHIVE: return new ObjectBeehive(pos);
        case OBJECT_FLOWER: return new ObjectFlower(pos);
        }
        return null;
    }
    
    /**
     * Given an EnvironmentModel, create a new Entity and add it to the model.
     * Wraps around {@link EntityTypeMapper#createEntity(test.model.entity.Entity.type, Position)}.
     * @param entityType    Type of Entity.
     * @param pos   Position of Entity.
     * @return  Entity of specified type and position.
     */
    public static void createEntityInto(EnvironmentModel env, Entity.type entityType, Position pos){
        switch(entityType){
        case AGENT_BEE: env.getAgents().add((AgentBee)createEntity(entityType, pos)); return;
        case AGENT_WASP: env.getAgents().add((AgentWasp)createEntity(entityType, pos)); return;
        case OBJECT_BEEHIVE: env.getObjects().add((ObjectBeehive)createEntity(entityType, pos)); return;
        case OBJECT_FLOWER: env.getObjects().add((ObjectFlower)createEntity(entityType, pos)); return;
        }
    }
    
    /**
     * @param entityType    Type of Entity.
     * @return  ImageIcon that represents the specified type of Entity.
     */
    public static ImageIcon getIcon(Entity.type entityType){
        switch(entityType){
        case AGENT_BEE: return new ImageIcon("res/image/bee.png");
        case AGENT_WASP: return new ImageIcon("res/image/meat.png");
        case OBJECT_BEEHIVE: return new ImageIcon("res/image/agent.png");
        case OBJECT_FLOWER: return new ImageIcon("res/image/daisy.png");
        }
        return null;
    }
}
