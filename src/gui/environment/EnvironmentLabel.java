package gui.environment;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.util.ColorHelper;

/**
 *  JLabel that knows the tile of the grid that it's representing.
 *  
 *  This allows us to not run through the entire grid whenever a JLabel is clicked to find out its x and y values.
 *  Can also be set to hightlighted, which makes it display a lighter color 
 *  
 *  @author jose
 */
public class EnvironmentLabel extends JLabel{

    // TODO: default
    private static final long serialVersionUID = 1L;
    
    public int x = 0, y = 0;    // Tile of environment this JLabel represents.
    
    private int highlightOffset; // How much lighter/darker will this label become when highlighted. 
    private boolean highlighted; // Whether or not this JLabel is highlighted (cursor).
    
    private Color trueColor; // True color regardless of highlighted state.
    
    public EnvironmentLabel(){
        super("", SwingConstants.CENTER);
        this.setOpaque(true);
        highlighted = false;
        highlightOffset = 20;
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
