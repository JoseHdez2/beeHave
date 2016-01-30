package test.model.environment;

import javax.swing.ImageIcon;

import app.util.Sys;
import test.model.agent.Agent;
import test.model.agent.AgentBee;
import test.model.agent.AgentWasp;
import test.util.typedef.Position;


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
     * @param entityType    Type of Entity.
     * @return  ImageIcon that represents the specified type of Entity.
     */
    public static ImageIcon getIcon(Entity.type entityType){
        switch(entityType){
        case AGENT_BEE: return new ImageIcon("media/image/bee.png");
        case AGENT_WASP: new ImageIcon("media/image/meat.png");
        case OBJECT_BEEHIVE: new ImageIcon("media/image/agent.png");
        case OBJECT_FLOWER: new ImageIcon("media/image/daisy.png");
        }
        return null;
    }
}
