package test.model.environment;

import test.util.typedef.Position;

/**
 * @author jose
 *
 *  Environment object (EnvObject, to differentiate from java.lang.Object).
 *  Entity that doesn't act (unlike Agents, which do act).
 */
public abstract class EnvObject extends Entity {

    public enum type {
        FLOWER,
        BEEHIVE
    }
    
    EnvObject.type objectType;  // Type of EnvObject.
    
    protected EnvObject(Position pos, EnvObject.type objectType) {
        super(pos, Entity.type.OBJECT);
        this.objectType = objectType;
    }
    
    /*
     * Getters and setters.
     */

    public EnvObject.type getObjectType() {
        return objectType;
    }

    public void setObjectType(EnvObject.type objectType) {
        this.objectType = objectType;
    }

}
