package srpg.base;

/**
 *  Character with the ability to move inside a Map.
 */
public abstract class Unit {
    
    /**
     * @return  Whether this unit can traverse the given tile during a turn.
     */
    public abstract boolean canPass();
    
    /**
     * @return  Whether this unit can stay in the given tile at the end of a turn.
     */
    public abstract boolean canStay();
}
