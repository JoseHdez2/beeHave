package model.entity.agent;

import java.awt.Point;
import java.util.ArrayList;

import model.entity.Entity;
import model.entity.agent.AgentBee.BehaviourType;
import model.entity.object.Flower;
import model.entity.object.ObjectBeehive;
import model.environment.EnvironmentModel;
import model.movement.MovementType;
import model.movement.RandomMove;
import util.typedef.Position;

public class AgentOld extends Agent{
    
    private static behaviourType defaultInitialBehaviour = behaviourType.SCOUT;
    private static MovementType defaultInitialPathfinding = new RandomMove();
    
    public static int MAX_CARRY = 15;
    private static int ZERO = 0;
    
    public static enum behaviourType{
        SCOUT,
        RETURN,
        IDLE,
        GO_TO_POINT
    }
    
    private behaviourType behaviour;    // Current behavior of the AgentBee.
    private Position hivePos;    // Position of the bee's hive.
    private Integer pollenCarried;
    private ArrayList<Position> pathToHive;
    private Flower bestFlower;
    private ArrayList<Position> pathToFlower;
    
    /*
     * Constructors
     */
    
    public AgentOld(Position pos, behaviourType behaviour) {
        super(Entity.type.AGENT_BEE, pos);
        this.behaviour = behaviour;
        pathFinding = defaultInitialPathfinding;
        
        setPollenCarried(new Integer(ZERO));
        setPathToHive(new ArrayList<Position>());
        setBestFlower(null);
        getBestFlower().setPollen(ZERO);
        getBestFlower().setFlowerPosition(new Point());
        setPathToFlower(new ArrayList<Position>());
    }
    
    public AgentOld(Position pos){
        this(pos, defaultInitialBehaviour);
    }
    
    public AgentOld(Position pos, Position hivePos){
        this(pos);
        setHivePos(hivePos);
    }
    
    @Override
    public void simulationStep(EnvironmentModel environment) {
        // TODO Auto-generated method stub
        
    }
    
    public void getPollen(Flower flower){
        addPollen(flower.removePollen(MAX_CARRY));
//      if (getPollenCarried() == MAX_CARRY) {
//          setBehaviour(behaviourType.RETURN);
//      }
        if (flower.getPollen() > getBestFlower().getPollen()) {
            setBestFlower(flower);
        }
    }
    
    public void moveAgent(EnvironmentModel model){
        
        // The pathfinding module decides the next step and executes it.
        System.out.println(this.toString());
        if (getBehaviour() == behaviourType.RETURN) {
            System.out.println("return");       
            returnToHive();
        }
        if (getBehaviour() == behaviourType.IDLE){
            System.out.println("idle");     
            }
        else if (getBehaviour() == behaviourType.SCOUT){
            System.out.println("scout");
            getPathFinding().nextMove(this, model);
        }
        if (getBehaviour() == behaviourType.GO_TO_POINT){
            System.out.println("point");
            moveToPoint(getPathToFlower());
        }

    }
    
    public void unloadPollen(ObjectBeehive hive){
        if (getPollenCarried() > 0) {
            hive.setPollenInHive(getPollenCarried());
            setPollenCarried(ZERO);
        }

    }
    
    public void communicate(ObjectBeehive hive){
        
        if (!hive.getBeesInside().isEmpty()) {
            for ( AgentOld beeInHive : hive.getBeesInside()) {
                if (beeInHive.getBestFlower() == null){
                    setBestFlower(beeInHive.getBestFlower());
                }
                if (beeInHive.getBestFlower().getPollen() > getBestFlower().getPollen()) {
                    setBestFlower(beeInHive.getBestFlower());
                }
            }
        }
    }
    
    
    public void returnToHive(){
        moveToPoint(getPathToHive());
        // el elemento del soiguiente movimiento se escogera asi: min(abs(Point.getX + Point.getY) - (Start.getX + Start.getY)) con point siendo cada elemento de la lista
    }
    
    public void moveToPoint(ArrayList<Position> path){
        int min = Integer.MAX_VALUE; 
        
        for (Position point : path) {
            if (Math.abs((point.getX() + point.getY()) - (getPosX() + getPosY()) ) < min) {
                setPos(point);
            }
        }
        if ((path.size() > 0)) {
            path.remove(path.size() - 1);   
        }
    }

    public void addPollen(Integer pollen){
        setPollenCarried(getPollenCarried() + pollen); 
    }
    
    /*
     *  Getters and setters.
     */
    
    public Flower getBestFlower() {
        return bestFlower;
    }

    public void setBestFlower(Flower bestFlower) {
        this.bestFlower = bestFlower;
    }

    public Integer getPollenCarried() {
        return pollenCarried;
    }

    public void setPollenCarried(Integer pollenCarried) {
        this.pollenCarried = pollenCarried;
    }

    public ArrayList<Position> getPathToHive() {
        return pathToHive;
    }

    public void setPathToHive(ArrayList<Position> pathToHive) {
        this.pathToHive = pathToHive;
    }

    public ArrayList<Position> getPathToFlower() {
        return pathToFlower;
    }

    public void setPathToFlower(ArrayList<Position> pathToFlower) {
        this.pathToFlower = pathToFlower;
    }

    public Position getHivePos() {
        return hivePos;
    }

    public void setHivePos(Position hivePos) {
        this.hivePos = hivePos;
    }

    public behaviourType getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(behaviourType behaviour) {
        this.behaviour = behaviour;
    }

}
