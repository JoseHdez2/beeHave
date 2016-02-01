package util.movement;

import java.util.ArrayList;
import java.util.HashMap;

import util.typedef.DefaultHashMap;
import util.typedef.Position;

public class AStar {
    
    private static int ZERO = 0;
    private static int INFINITY = Integer.MAX_VALUE;
    private ArrayList<Position> closedSet;
    private ArrayList<Position> openSet;
    private HashMap<Position, Position> cameFrom;
    private DefaultHashMap<Position, Integer> gScore;
    private DefaultHashMap<Position, Integer> fScore;
    private Position current;
    private int matrixWidth;
    private int matrixHeight;

    public AStar(int width, int height, Position start, Position goal) {  
        clear(width, height, start, goal);
    }
    
    public AStar(int width, int height){
        matrixInit(width, height);
    }
    
    private void clear(Integer width, Integer height, Position start, Position goal){
        matrixInit(width, height);
        knowledgeInit(start, goal);
    }
    
    private void matrixInit(int width, int height){
        setMatrixHeight(height);
        setMatrixWidth(width);
        setClosedSet(new ArrayList<Position>());
        setOpenSet(new ArrayList<Position>());
        setCameFrom(new HashMap<Position, Position>());
        setCurrent(new Position());
    }
    public void knowledgeInit(Position start, Position goal){
        getOpenSet().add(start);
        setgScore(new DefaultHashMap<Position, Integer>(INFINITY));
        setfScore(new DefaultHashMap<Position, Integer>(INFINITY));
        getgScore().put(start, ZERO);
        getfScore().put(start, Manhattan.getDistance((int) start.getX(),(int) start.getY(),(int) goal.getX(),(int) goal.getY()));
    }
    
    public ArrayList<Position> run(Position start, Position goal){
        clear(getMatrixWidth(), getMatrixHeight(), start, goal);
        while (!getOpenSet().isEmpty()) {
            int min = INFINITY;
            for (Position Position : openSet) {
                
                if (getfScore().get(Position) <= min) {
                    min = (int) getfScore().get(Position);
                    setCurrent(Position);
                }
            }
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, goal);
                
            }
            getOpenSet().remove(getCurrent());
            getClosedSet().add(getCurrent());
            ArrayList<Position> neighborsCurrent = getNeighbors(current);
            

            for (Position neighbor : neighborsCurrent) {
                if (getClosedSet().contains(neighbor)) {
                    continue;
                }
                int tentativegScore = (int) getgScore().get(getCurrent()) + pythagoreanDistance(start, goal);
                if (!getOpenSet().contains(neighbor)) {
                    getOpenSet().add(neighbor);
                }
                else if (tentativegScore >= getgScore().get(neighbor)) {
                    continue;
                }
                
                cameFrom.put(neighbor, getCurrent());
                getgScore().put(neighbor, tentativegScore);
                getfScore().put(neighbor, getgScore().get(neighbor) + Manhattan.getDistance((int) neighbor.getX(),(int) neighbor.getY(),(int) goal.getX(),(int) goal.getY()));
            }
        }
        return null;
        
    }
    
    private ArrayList<Position> reconstructPath(HashMap<Position, Position> cameFrom, Position current){
        ArrayList<Position> totalPath = new ArrayList<Position>();
        totalPath.add(current);
        while (cameFrom.keySet().contains(current)){
            current = cameFrom.get(current);
            totalPath.add(current);
        }
        
        return totalPath;
    }
    
    private ArrayList<Position> getNeighbors(Position current2) {
        ArrayList<Position> aux = new ArrayList<Position>();
        if ((int)current2.getY() == 0 && (int)current2.getX() == 0) {
            aux.add(new Position((int) current2.getX() + 1,(int)current2.getY()));
            aux.add(new Position((int) current2.getX(),(int)current2.getY() + 1));
            return aux;
        }
        
        else if ((int)current2.getY() == 0) {
            aux.add(new Position((int) current2.getX() + 1,(int)current2.getY()));
            aux.add(new Position((int) current2.getX(),(int)current2.getY() + 1));
            aux.add(new Position((int) current2.getX() - 1,(int)current2.getY()));
            return aux;
        }
        else if ((int)current2.getX() == 0) {
            aux.add(new Position((int) current2.getX() + 1,(int)current2.getY()));
            aux.add(new Position((int) current2.getX(),(int)current2.getY() + 1));
            aux.add(new Position((int) current2.getX(),(int)current2.getY() - 1));
            return aux;
        }
        aux.add(new Position((int) current2.getX() + 1,(int)current2.getY()));
        aux.add(new Position((int) current2.getX(),(int)current2.getY() + 1));
        aux.add(new Position((int) current2.getX() - 1,(int)current2.getY()));
        aux.add(new Position((int) current2.getX(),(int)current2.getY() - 1));
        return aux;
    }

    /**
     * @return the closedSet
     */
    public ArrayList<Position> getClosedSet() {
        return closedSet;
    }


    /**
     * @return the openSet
     */
    public ArrayList<Position> getOpenSet() {
        return openSet;
    }

    /**
     * @return the gScore
     */
    public DefaultHashMap<Position, Integer> getgScore() {
        return gScore;
    }

    /**
     * @param gScore the gScore to set
     */
    public void setgScore(DefaultHashMap<Position, Integer> gScore) {
        this.gScore = gScore;
    }

    /**
     * @return the fScore
     */
    public DefaultHashMap<Position, Integer> getfScore() {
        return fScore;
    }

    /**
     * @param fScore the fScore to set
     */
    public void setfScore(DefaultHashMap<Position, Integer> fScore) {
        this.fScore = fScore;
    }

    /**
     * @param closedSet the closedSet to set
     */
    public void setClosedSet(ArrayList<Position> closedSet) {
        this.closedSet = closedSet;
    }

    /**
     * @param openSet the openSet to set
     */
    public void setOpenSet(ArrayList<Position> openSet) {
        this.openSet = openSet;
    }

    /**
     * @param cameFrom the cameFrom to set
     */
    public void setCameFrom(HashMap<Position, Position> cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * @return the current
     */
    public Position getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Position current) {
        this.current = current;
    }

    /**
     * @return the matrixWidth
     */
    public int getMatrixWidth() {
        return matrixWidth;
    }

    /**
     * @param matrixWidth the matrixWidth to set
     */
    public void setMatrixWidth(int matrixWidth) {
        this.matrixWidth = matrixWidth;
    }

    /**
     * @return the matrixHeight
     */
    public int getMatrixHeight() {
        return matrixHeight;
    }

    /**
     * @param matrixHeight the matrixHeight to set
     */
    public void setMatrixHeight(int matrixHeight) {
        this.matrixHeight = matrixHeight;
    }
    
    public int pythagoreanDistance(Position start, Position goal ){
        return (int) Math.sqrt((Math.pow(   (goal.getX() - start.getX()), 2) + Math.pow((goal.getY() - start.getY()), 2)));
    } 
}
