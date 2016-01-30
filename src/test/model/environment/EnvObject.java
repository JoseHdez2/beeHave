package test.model.environment;

import test.util.typedef.Position;

/**
 * @author jose
 *
 *  Environment object.
 *  Entity that doesn't act (unlike Agents, which do act).
 */
public class EnvObject extends Entity {

    protected EnvObject(Position pos) {
        super(pos, Entity.type.OBJECT);
    }

}
