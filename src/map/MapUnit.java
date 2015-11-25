package map;

import util.Pos;

/**
 *  Character with the ability to move inside a Map.
 */
public interface MapUnit {
    
    /**
     * @return  Whether this unit can traverse the given tile during the current turn.
     */
    //boolean canPass();
    
    /**
     * @return  Whether this unit can stay in the given tile at the end of the current turn.
     */
    //boolean canStay();
    
    /**
     * @return  Position of itself in the map.
     */
    Pos posInMap(); 
}
