package test.gui.util;

import java.awt.Color;

/**
 * Collection of functions that help treat instances of java.awt.Color.
 * 
 * @author jose
 */
public abstract class ColorHelper {
    
    /**
     * @param colorChannelValue Color channel value that might be out of bounds.
     * @return Color channel value between 0 and 255.
     */
    static private int normalize(int colorChannelValue){
        if (colorChannelValue > 255) return 255;    // Value above upper bound; truncate to 255.
        if (colorChannelValue < 0) return 0;    // Value below lower bound; set to zero.
        return colorChannelValue;   // No problems.
    }
    
    /**
     * @param color Input color to be offset.
     * @param r Offset for red channel.
     * @param g Offset for green channel.
     * @param b Offset for blue channel.
     * @return Resulting color of offsetting 'color' with 'r', 'g' and 'b'.
     */
    static public Color offsetColor(Color color, int r, int g, int b){
        int r2 = normalize(color.getRed() + r);
        int g2 = normalize(color.getGreen() + g);
        int b2 = normalize(color.getBlue() + b);
        return new Color(r2, g2, b2);
    }
    
    /**
     * @param color Input color to be lightened or darkened.
     * @param value Number by which we offset the input color's channel values.
     * @return Resulting color of offsetting 'color' with 'value.
     */
    static public Color offsetColor(Color color, int value){
        int r = normalize(color.getRed() + value);
        int g = normalize(color.getGreen() + value);
        int b = normalize(color.getBlue() + value);
        return new Color(r, g, b);
    }
    
    /**
     * @param c1 Color 1.
     * @param c2 Color 2.
     * @return Mean color resulting of the mean of c1 and c2.
     */
    static public Color mean(Color c1, Color c2){
        int r = (c1.getRed() + c2.getRed()) / 2;
        int g = (c1.getGreen() + c2.getGreen()) / 2;
        int b = (c1.getBlue() + c2.getBlue()) / 2;
        return new Color(r, g, b);
    }
}
