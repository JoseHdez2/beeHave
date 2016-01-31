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
	
    /*
     * Equals and hashCode.
     */
    
    public boolean equals(Object ob){
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        Position other = (Position)ob;
        if (p != other.p) return false;
        return true;
    }
    
    public int hashCode() {
        return p.hashCode();
    }
}
