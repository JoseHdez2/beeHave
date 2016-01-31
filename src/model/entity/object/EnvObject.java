package model.entity.object;

import model.entity.Entity;
import util.typedef.Position;

/**
 * @author jose
 *
 *  Environment object (EnvObject, to differentiate from java.lang.Object).
 *  Entity that doesn't act (unlike Agents, which do act).
 */
public abstract class EnvObject extends Entity {
    
    protected EnvObject(Entity.type entityType, Position pos) {
        super(entityType, pos);
    }

}
