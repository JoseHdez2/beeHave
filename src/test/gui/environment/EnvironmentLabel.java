package test.gui.environment;

import javax.swing.JLabel;

/**
 *  Trivial class:
 *  Just a JLabel that knows the tile of the grid that it's representing.
 *  
 *  This allows us to not run through the entire grid whenever a JLabel is clicked to find out its x and y values.
 */
public class EnvironmentLabel extends JLabel{

    // TODO: default
    private static final long serialVersionUID = 1L;
    
    public int x = 0, y = 0;
    
    public EnvironmentLabel(){
        this.setOpaque(true);
    }
    
    public EnvironmentLabel(int i, int j){
       this.x = i;
       this.y = j;
    }

}
