package test.model.environment;

import test.util.typedef.Position;

/**
 * @author jose
 * 
 *  Abstract entity, that can be either an Agent (can act) or an Object (doesn't act).
 *  Has a position in an environment.
 */
public class Entity {
    EntityType type;    // Type of entity.
    Position pos;       // Position in the environment.
}
