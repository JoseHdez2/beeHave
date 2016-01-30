package test.model.environment;

import test.util.typedef.Position;

/**
 * @author jose
 *
 *  Environment object (EnvObject, to differentiate from java.lang.Object).
 *  Entity that doesn't act (unlike Agents, which do act).
 */
public abstract class EnvObject extends Entity {
    
    protected EnvObject(Position pos, Entity.type entityType) {
        super(pos, entityType);
    }

}
