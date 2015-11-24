package srpg;

import java.util.ArrayList;

import util.Matrix;

public class Map extends Matrix<AbstractTile>{

    ArrayList<Unit> unitList;  // Units in map.
    
    public Map(AbstractTile[][] data) {
        super(data);
    }

    /*
     * Getters and setters.
     */
    
    public ArrayList<Unit> getUnitList() {
        return unitList;
    }

};
