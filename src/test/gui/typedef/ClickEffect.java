package test.gui.typedef;

import app.util.Sys;
import test.model.agent.Agent;
import test.model.agent.AgentBee;
import test.model.agent.AgentWasp;
import test.model.environment.Entity;
import test.model.environment.EnvObject;
import test.model.environment.ObjectBeehive;
import test.model.environment.ObjectFlower;
import test.util.typedef.Position;


/**
 * @author jose
 *
 *  Class that receives an entity type and returns its corresponding Entity.
 */
public class ClickEffect {
    
    // TODO: Opcion 2: tener todos los tipos en un solo enum EntityType.
    
    // Default initial entity type.
    static private Entity.type defaultEntityType;
    static private Agent.type defaultAgentType;
    static private EnvObject.type defaultObjectType;
    
    // Current entity type.
    private Entity.type entityType; 
    private Agent.type agentType;
    private EnvObject.type objectType;
    
    /**
     * Constructor.
     */
    public void ClickEffect(){
        this.entityType = Entity.type.AGENT;
        this.agentType = Agent.type.BEE;
        this.objectType = EnvObject.type.FLOWER;
    }
    
    public void setEffect(Agent.type agentType){
        this.entityType = Entity.type.AGENT;
        this.agentType = agentType;
    }
    
    public void setEffect(EnvObject.type objectType){
        this.entityType = Entity.type.OBJECT;
        this.objectType = objectType;
    }
    
    // la chicha de la clase
    /**
     * Creates an Entity in the specified position.
     * @param pos Position where the Entity will be created.
     * @return Entity in the specified position.
     */
    public Entity createEntity(Position pos){
        /*
         * entityType decides whether we look at agentType or objectType
         * for the type of the Entity that we will create.
         */
        switch(this.entityType){
        case AGENT:
            switch(this.agentType){
            case BEE: return new AgentBee(pos);
            case WASP: return new AgentWasp(pos);
            }
        case OBJECT:
            switch(this.objectType){
            case FLOWER: return new ObjectFlower(pos);
            case BEEHIVE: return new ObjectBeehive(pos);
            }
        }
        
        // Si la cosa sale mal
        try {
            Sys.exception("ClickEffect: Unknown entity type provided.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AgentBee(pos);
    }
}
