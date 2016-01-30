package test.model.environment;

import test.util.typedef.Position;

/**
 * @author jose
 * 
 *  Abstract entity, that can be either an Agent (can act) or an Object (doesn't act).
 *  Has a position in an environment.
 */
public abstract class Entity {
    
    public enum type {
        AGENT,
        OBJECT
    }
    
    protected Entity.type entityType;   // Type of entity.
    protected Position pos;       // Position in the environment.
    
    /**
     * Constructor.
     */
    protected Entity(Position pos, Entity.type entityType){
        this.pos = pos;
        this.entityType = entityType;
    }
    
    /*
     * Getters and setters.
     */

    public Entity.type getEntityType() {
        return entityType;
    }

    public void setEntityType(Entity.type entityType) {
        this.entityType = entityType;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
