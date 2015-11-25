package map;

import java.util.ArrayList;

import util.Matrix;

public class Map extends Matrix<MapTile>{

    ArrayList<MapUnit> unitList;  // Units in map.
    
    public Map(MapTile[][] data) {
        super(data);
    }

    /*
     * Getters and setters.
     */
    
    public ArrayList<MapUnit> getUnitList() {
        return unitList;
    }

};
