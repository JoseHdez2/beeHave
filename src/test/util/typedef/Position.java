package test.util.typedef;

import java.awt.Point;

/**
 * Renaming of java.awt.Point for easier semantic understanding.
 */
public class Position {
	
    Point p;
    
    // Default constructor: Initializes x and y to zero.
	public Position() {
		p = new Point();
	}
	
	// Constructor.
	public Position(int x, int y){
		p = new Point(x,y);
	}
	
	public void setLocation(Integer x, Integer y){
		p.setLocation(x, y);
	}
	
	public Integer getX(){
	    return (int)p.getX();
	}
	
	public Integer getY(){
        return (int)p.getY();
    }

}
