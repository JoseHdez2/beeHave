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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((p == null) ? 0 : p.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (p == null) {
            if (other.p != null)
                return false;
        } else if (!p.equals(other.p))
            return false;
        return true;
    }
}
