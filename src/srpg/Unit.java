package srpg;

/**
 *  Character with the ability to move inside a Map.
 */
public interface Unit {
    
    /**
     * @return  Whether this unit can traverse the given tile during the current turn.
     */
    public abstract boolean canPass();
    
    /**
     * @return  Whether this unit can stay in the given tile at the end of the current turn.
     */
    public abstract boolean canStay();
}
