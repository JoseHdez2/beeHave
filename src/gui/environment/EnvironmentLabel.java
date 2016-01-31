package gui.environment;

import java.awt.Color;

import javax.swing.JLabel;

import gui.util.ColorHelper;

/**
 *  Trivial class:
 *  Just a JLabel that knows the tile of the grid that it's representing.
 *  
 *  This allows us to not run through the entire grid whenever a JLabel is clicked to find out its x and y values.
 */
public class EnvironmentLabel extends JLabel{

    // TODO: default
    private static final long serialVersionUID = 1L;
    
    private static int highlightOffset = 30;
    
    public int x = 0, y = 0;    // Tile of environment this JLabel represents.
    private boolean highlighted; // Whether or not this JLabel is highlighted (cursor).
    
    private Color trueColor; // True color regardless of highlighted state.
    
    public EnvironmentLabel(){
        this.setOpaque(true);
        highlighted = false;
    }
    
    public EnvironmentLabel(int i, int j){
       this();
       this.x = i;
       this.y = j;
    }
    
    @Override
    public void setBackground(Color bg){
        super.setBackground(bg);
        trueColor = bg;
    }
    
    /*
     * Getters and setters.
     */
    
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
        Color displayColor = highlighted ? ColorHelper.offsetColor(trueColor, highlightOffset) : trueColor;
        super.setBackground(displayColor);
    }

}
