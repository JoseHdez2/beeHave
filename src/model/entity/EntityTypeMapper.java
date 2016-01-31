package model.entity;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.entity.agent.Agent;
import model.entity.agent.AgentBee;
import model.entity.agent.AgentWasp;
import model.entity.object.EnvObject;
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
        case AGENT_BEE: env.getAgents().add((AgentBee)createEntity(entityType, pos)); break;
        case AGENT_WASP: env.getAgents().add((AgentWasp)createEntity(entityType, pos)); break;
        case OBJECT_BEEHIVE: env.getObjects().add((ObjectBeehive)createEntity(entityType, pos)); break;
        case OBJECT_FLOWER: env.getObjects().add((ObjectFlower)createEntity(entityType, pos)); break;
        }
        // Set newly created entity's name.  TODO: laame code, redo.
        int num = 0;
        boolean isAgent = (entityType == Entity.type.AGENT_BEE || entityType == Entity.type.AGENT_WASP) ?
                true : false;
        int last = isAgent ? env.getAgents().size()-1 : env.getObjects().size()-1;
        
        if (isAgent){
            for(Agent a : env.getAgents())
                if(a.entityName().equals(env.getAgents().get(last).entityName()))
                    num++;  // Increment for each agent of same class.
            env.getAgents().get(last).setName(env.getAgents().get(last).entityName() + num);
        } 
        else {
            for(EnvObject o : env.getObjects())
                if(o.entityName().equals(env.getObjects().get(last).entityName()))
                    num++;  // Increment for each agent of same class.
            env.getObjects().get(last).setName(env.getObjects().get(last).entityName() + num);
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
