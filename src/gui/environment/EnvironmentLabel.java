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
    
    // TODO: Use colors instead of offsets/grays. More flexible.
    
    private int highlightOffsetCursor; // How much lighter/darker will this label become when highlighted. 
    private boolean highlightedCursor; // Whether or not this JLabel is highlighted (cursor).
    
    private int highlightOffsetInspector; // How much lighter/darker will this label become when highlighted. 
    private boolean highlightedInspector; // Whether or not this JLabel is highlighted (inspector).
    
    private Color trueColor; // True color regardless of highlighted state.
    
    public EnvironmentLabel(){
        super("", SwingConstants.CENTER);
        this.setOpaque(true);
        highlightedCursor = false;
        highlightOffsetCursor = 20;
        highlightOffsetInspector = -40;
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
    
    
    public void updateDisplayColor(){
        Color displayColor = trueColor;
        if (highlightedCursor) displayColor = ColorHelper.offsetColor(displayColor, highlightOffsetCursor);
        if (highlightedInspector) displayColor = ColorHelper.offsetColor(displayColor, highlightOffsetInspector);
        super.setBackground(displayColor);
    }
    
    /*
     * Handmade, special getters and setters.
     */
    
    public void setHighlightedCursor(boolean highlightedCursor) {
        this.highlightedCursor = highlightedCursor;
        updateDisplayColor();
    }
    
    public void setHighlightedInspector(boolean highlightedInspector) {
        this.highlightedInspector = highlightedInspector;
        updateDisplayColor();
    }

}
