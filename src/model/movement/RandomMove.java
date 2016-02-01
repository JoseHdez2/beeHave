package model.movement;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import model.entity.agent.Agent;
import model.environment.EnvironmentModel;

/**
 * @author eebritos
 *
 */
public class RandomMove implements MovementType {
    private ArrayList<Point> visited;
    private Random randomGenerator;

    public RandomMove(){
        visited = new ArrayList<Point>();
        randomGenerator = new Random(); 
    }
    
    public boolean isIn(ArrayList<Point> list,double d,double e) {
       for (Point element : list) {
        if (element.getX() == d && element.getY() == e) {
            return true;
        }
       }
       return false;
    }
    
    public ArrayList<moveDirection> legalMovements( int height, int width, Agent agent){
        ArrayList<moveDirection> result = new ArrayList<moveDirection>();
        if(agent.getPosY() == height-1 && agent.getPosX() == width -1){
            if (isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
                result.add(moveDirection.UP);
                return result;
            }
            else if (isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
                result.add(moveDirection.LEFT);
                return result;
            }
            result.add(moveDirection.LEFT);
            result.add(moveDirection.UP);
            
            return result;
        }
        else if(agent.getPosY() == 0 && agent.getPosX() == 0){
            if (isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
                result.add(moveDirection.DOWN);
                return result;
            }
            else if (isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
                result.add(moveDirection.RIGHT);
                return result;
            }
            result.add(moveDirection.DOWN);
            result.add(moveDirection.RIGHT);
            return result;
        }
        else if(agent.getPosY() == 0 && agent.getPosX() == width - 1){
            if (isIn(getVisited(), agent.getPosX() , agent.getPosY() + 1 )) {
                result.add(moveDirection.DOWN);
                return result;
            }
            else if (isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
                result.add(moveDirection.LEFT);
                return result;
            }
            result.add(moveDirection.DOWN);
            result.add(moveDirection.LEFT);
            return result;      
        }
        else if(agent.getPosY() == height - 1 && agent.getPosX() == 0){
            if (isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
                result.add(moveDirection.UP);
                return result;
            }
            else if (isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
                result.add(moveDirection.RIGHT);
                return result;
            }
            result.add(moveDirection.UP);
            result.add(moveDirection.RIGHT);
            return result;
        }
        else if (agent.getPosY() == height - 1) {
            if (!isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
                result.add(moveDirection.RIGHT);
                
            }
            if (!isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
                result.add(moveDirection.LEFT);
                
            }
            if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
                result.add(moveDirection.UP);
                
            }
            if (!result.isEmpty()) {
                return result;
            }
            
        }
        else if (agent.getPosX() == width - 1) {
            if (!isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
                result.add(moveDirection.LEFT);
                
            }
            if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
                result.add(moveDirection.DOWN);
                
            }
            if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
                result.add(moveDirection.UP);
            }
            if (!result.isEmpty()) {
                return result;
            }
        }
        else if (agent.getPosX() == 0) {
            if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
                result.add(moveDirection.UP);
                
            }
            if (!isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
                result.add(moveDirection.RIGHT);
                
            }
            if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
                result.add(moveDirection.DOWN);
                
            }
            if (!result.isEmpty()) {
                return result;
            }
            
        }
        else if (agent.getPosY() == 0) {
            if (!isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
                result.add(moveDirection.RIGHT);
            }
            if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() + 1)) {
                result.add(moveDirection.DOWN);
            }
            if (!isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
                result.add(moveDirection.LEFT);
            }
            if (!result.isEmpty()) {
                return result;
            }
        }

        if (result.isEmpty()) {
            if (agent.getPosY() == 0 && agent.getPosX() == 0) {
                result.add(moveDirection.DOWN);
                result.add(moveDirection.RIGHT);
                return result;
            }
            else if (agent.getPosY() == height - 1 && agent.getPosX() == width - 1) {
                result.add(moveDirection.UP);
                result.add(moveDirection.LEFT);
                return result;
            }
            else if(agent.getPosY() == 0 && agent.getPosX() == width-1){
                result.add(moveDirection.DOWN);
                result.add(moveDirection.LEFT);
                return result;
            }
            else if(agent.getPosY() == height - 1 && agent.getPosX() == 0){
                result.add(moveDirection.UP);
                result.add(moveDirection.RIGHT);
                return result;
            }
            else if (agent.getPosY() == height - 1) {
                result.add(moveDirection.RIGHT);
                result.add(moveDirection.LEFT);
                result.add(moveDirection.UP);
                return result;
            }
            else if (agent.getPosX() == width - 1) {
                result.add(moveDirection.LEFT);
                result.add(moveDirection.DOWN);
                result.add(moveDirection.UP);
                return result;
            }
            else if (agent.getPosX() == 0) {
                result.add(moveDirection.UP);
                result.add(moveDirection.RIGHT);
                result.add(moveDirection.DOWN);
                return result;
            }
            else if (agent.getPosY() == 0) {
                result.add(moveDirection.RIGHT);
                result.add(moveDirection.DOWN);
                result.add(moveDirection.LEFT);
                return result;
            }
            result.add(moveDirection.UP);
            result.add(moveDirection.RIGHT);
            result.add(moveDirection.LEFT);
            result.add(moveDirection.DOWN);
            return result;
        }
        if (!isIn(getVisited(), agent.getPosX() + 1, agent.getPosY())) {
            result.add(moveDirection.RIGHT);
            
        }
        if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
            result.add(moveDirection.UP);
            
        }
        if (!isIn(getVisited(), agent.getPosX() - 1, agent.getPosY())) {
            result.add(moveDirection.LEFT);
            
        }
        if (!isIn(getVisited(), agent.getPosX(), agent.getPosY() - 1)) {
            result.add(moveDirection.DOWN);
            
        }
        return result;
    }
    
<<<<<<< HEAD:src/model/movement/RandomMove.java
    public moveDirection nextMove(Agent agent, EnvironmentModel env) {
        ArrayList<moveDirection> lmovementsArray = legalMovements(visited, env.getHeight(), env.getWidth(), agent);
        int index = randomGenerator.nextInt(lmovementsArray.size());
        moveDirection movement = lmovementsArray.get(index);
        visited.add(agent.getPos());

        switch (movement) {
        case DOWN:
            agent.getPos().setLocation(agent.getPosX() , agent.getPosY()+ 1);
            visited.add(new Position(agent.getPosX(), agent.getPosY()));
            return moveDirection.DOWN;
        case UP:
            agent.getPos().setLocation(agent.getPosX() , agent.getPosY()- 1);
            visited.add(new Position(agent.getPosX() , agent.getPosY()));
        case LEFT:
            agent.getPos().setLocation(agent.getPosX() - 1, agent.getPosY() );
            visited.add(new Position(agent.getPosX(), agent.getPosY() ));
            return moveDirection.LEFT;
        case RIGHT:
            agent.getPos().setLocation(agent.getPosX() + 1, agent.getPosY() );
            visited.add(new Position(agent.getPosX(), agent.getPosY() ));
=======
    @Override
    public moveDirection nextMove (Agent agent, EnvironmentPanel panel) {
        ArrayList<moveDirection> lmovementsArray = legalMovements(panel.getElements().getHeight(), panel.getElements().getWidth(), agent);
        int index = randomGenerator.nextInt(lmovementsArray.size());
        moveDirection movement = lmovementsArray.get(index);
        if (!getVisited().contains(agent.getPos())) {
            getVisited().add(agent.getPos());
        }
        System.out.println("gi");

        switch (movement) {
        case DOWN:
            agent.setPos(new Point((int) agent.getPosX() , (int) agent.getPosY()+ 1));
            if(!isIn(getVisited(),  agent.getPosX(), agent.getPosY())) {
                getVisited().add(agent.getPos());
            }
            return moveDirection.DOWN;
        case UP:
            agent.setPos(new Point((int) agent.getPosX() , (int) agent.getPosY()- 1));
            if(!isIn(getVisited(),  agent.getPosX(), agent.getPosY()))
                getVisited().add(agent.getPos());
            return moveDirection.UP;
        case LEFT:
            agent.setPos(new Point((int) agent.getPosX() -1 , (int) agent.getPosY()));

            if(!isIn(getVisited(),  agent.getPosX(), agent.getPosY())) {
                getVisited().add(agent.getPos());
            }
            return moveDirection.LEFT;
        case RIGHT:
            agent.setPos(new Point((int) agent.getPosX() + 1 , (int) agent.getPosY()));
            if(!isIn(getVisited(),  agent.getPosX(), agent.getPosY())) {
                getVisited().add(agent.getPos());           }
>>>>>>> 06f519ccea2edcec8cbd5fe88789e09b01b2d71d:src/test/agent/RandomMove.java
            return moveDirection.RIGHT;
        default:
            break;
        }
        return null;
    }

    /**
     * @return the visited
     */
    public ArrayList<Point> getVisited() {
        return this.visited;
    }

    /**
     * @param visited the visited to set
     */
    public void setVisited(ArrayList<Point> visited) {
        this.visited = visited;
    }

    @Override
    public moveDirection nextMove(Agent agent, EnvironmentModel env) {
        // TODO Auto-generated method stub
        return null;
    }
    


}
