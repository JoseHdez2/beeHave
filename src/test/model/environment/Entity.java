package test.model.environment;

import javax.swing.ImageIcon;

import test.util.typedef.Position;

/**
 * @author jose
 * 
 *  Abstract entity, that can be either an Agent (can act) or an Object (doesn't act).
 *  Has a position in an environment.
 */
public abstract class Entity {
    
    public enum type {
        AGENT_BEE,
        AGENT_WASP,
        OBJECT_BEEHIVE,
        OBJECT_FLOWER,
    }
    
    protected Entity.type entityType;   // Type of entity.
    protected Position pos;       // Position in the environment.
    
    // TODO: 'cleaner' but less optimal (we load the same icon multiple times). 
    ImageIcon icon; // Image that represents this entity.
    
    /**
     * Constructor.
     */
    protected Entity(Position pos, Entity.type entityType){
        this.pos = pos;
        this.entityType = entityType;
        this.icon = EntityTypeMapper.getIcon(entityType);
    }
    
    /*
     * Getters and setters.
     */

    public Entity.type getEntityType() {
        return entityType;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
