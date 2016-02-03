package model.entity.agent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.ImageIcon;

import model.entity.Entity;
import model.entity.object.EnvObject;
import model.entity.object.ObjectBeehive;
import model.entity.object.ObjectFlower;
import model.environment.EnvironmentModel;
import model.movement.MovementType;
import model.movement.RandomMove;
import util.movement.AStar;
import util.typedef.Position;

public class AgentBee extends Agent{
    
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
    
    private AStar searchAlgorithm;
    private behaviourType behaviour;    // Current behavior of the AgentBee.
    private String hiveName;    // Position of the bee's hive. TODO: rn bees now where hive is magically.
    private Integer pollenCarried;
    private ArrayList<Position> pathToHive;
    private Position bestFlowerPos;  // Position of best flower.
    private Integer bestFlowerPollen;   // Pollen that best flower has.
    private String bestFlowerName;  // Name of best flower. In case there is more than one in the space.
    private ArrayList<Position> pathToFlower;
    
    /*
     * Constructors
     */
    
    public AgentBee(Position pos, behaviourType behaviour) {
        super(Entity.type.AGENT_BEE, pos);
        this.behaviour = behaviour;
        pathFinding = defaultInitialPathfinding;
        
        setPollenCarried(new Integer(ZERO));
        setPathToHive(new ArrayList<Position>());
        bestFlowerPos = new Position(0,0);
        bestFlowerPollen = 0;
        bestFlowerName = "Flowey";
        setPathToFlower(new ArrayList<Position>());
    }
    
    public AgentBee(Position pos){
        this(pos, defaultInitialBehaviour);
        // TODO: mess
        this.searchAlgorithm = new AStar(10, 10);
    }
    
    public AgentBee(Position pos, String hiveName){
        this(pos);
        this.hiveName = hiveName;
    }
    /*
    @Override
    protected void initializeIcon() {
//        super.initializeIcon();
        try {
            Enumeration<URL> e = ClassLoader.getSystemResources("bee.png");
            while(e.hasMoreElements()){
                URL param = (URL) e.nextElement();
                System.out.println(param);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.icon = new ImageIcon(ClassLoader.getSystemResource("beeHave/res/image/bee.png"));
    }*/
    
    @Override
    public void simulationStep(EnvironmentModel env) {
        moveAgent(env);
        // TODO: dirty code
//        if(pos.equals(bestFlowerPos))
//            getPollen(env, (ObjectFlower)env.getEntity(bestFlowerName));
//        if (pos.equals(env.getEntity(hiveName))){
//            unloadPollen((ObjectBeehive)env.getEntity(hiveName)); // will only unload if on same pos
//            communicate(env, (ObjectBeehive)env.getEntity(hiveName)); // same
//        }

        // For each flower in the environment
        for (Entity f : env.getEntities(type.OBJECT_FLOWER)){
            ObjectFlower flower = (ObjectFlower)f;
            // If this bee is on the flower
            if (this.pos.equals(flower.getPos()) && this.behaviour != behaviourType.RETURN) {
                // If the flower has any pollen.
                if (flower.getPollen() != 0) {
                // Get pollen from the flower and head back to the hive.
                getPollen(flower);
                behaviour = behaviourType.RETURN;
                System.out.println(String.format("My pos is %s", pos));
                System.out.println(String.format("My hive is called %s", hiveName));
                searchAlgorithm.knowledgeInit(this.pos, env.getEntity(hiveName).getPos());
                pathToHive = searchAlgorithm.run(this.pos, env.getEntity(hiveName).getPos());
                break;
                }
                else {
                    this.behaviour = behaviourType.SCOUT;
                    break;
                }
            }
        }
        if (this.behaviour == behaviourType.IDLE)
            this.communicate(env, (ObjectBeehive)env.getEntity(hiveName));
        if (this.bestFlowerPollen == 0 && this.behaviour == behaviourType.IDLE || this.behaviour == behaviourType.GO_TO_POINT &&
                this.pos.equals(env.getEntity(hiveName).getPos()))
            this.behaviour = behaviourType.SCOUT;
        ArrayList<String> beesInsideHive = ((ObjectBeehive)env.getEntity(hiveName)).getNamesOfBeesInside();
        if (this.behaviour == behaviourType.IDLE && (beesInsideHive.contains(this.name) && beesInsideHive.size() >= 2)){
            searchAlgorithm.knowledgeInit(this.pos, bestFlowerPos);
            pathToFlower = searchAlgorithm.run(this.pos, bestFlowerPos);
            behaviour = behaviourType.GO_TO_POINT;
            ((ObjectBeehive)env.getEntity(hiveName)).getNamesOfBeesInside().remove(this.name);
        }
        if (this.behaviour == behaviourType.RETURN && this.pos.equals((env.getEntity(hiveName).getPos()))) {
             this.behaviour = behaviourType.IDLE;
             if (!((ObjectBeehive)env.getEntity(hiveName)).getNamesOfBeesInside().contains(this.name))
                 ((ObjectBeehive)env.getEntity(hiveName)).getNamesOfBeesInside().add(this.name);
             this.unloadPollen(((ObjectBeehive)env.getEntity(hiveName)));
         }
         if (this.behaviour == behaviourType.GO_TO_POINT && this.pathToFlower.isEmpty()) {
             searchAlgorithm.knowledgeInit(this.pos, bestFlowerPos);
             this.pathToFlower = searchAlgorithm.run(this.pos, bestFlowerPos);
        }
    }
    
    public void getPollen(ObjectFlower flower){
        addPollen(flower.removePollen(MAX_CARRY));
//      if (getPollenCarried() == MAX_CARRY) {
//          setBehaviour(behaviourType.RETURN);
//      }
        if (flower.getPollen() > bestFlowerPollen) {
            bestFlowerName = flower.getName();
            bestFlowerPollen = flower.getPollen();
            bestFlowerPos = flower.getPos();
        }
    }
    
    public void moveAgent(EnvironmentModel model){
        
        // The pathfinding module decides the next step and executes it.
//        System.out.println(this.toString());
        if (getBehaviour() == behaviourType.RETURN) {
//            System.out.println("return");       
            returnToHive();
        }
        if (getBehaviour() == behaviourType.IDLE){
//            System.out.println("idle");     
            }
        else if (getBehaviour() == behaviourType.SCOUT){
//            System.out.println("scout");
            getPathFinding().nextMove(this, model);
        }
        if (getBehaviour() == behaviourType.GO_TO_POINT){
//            System.out.println("point");
            moveToPoint(getPathToFlower());
        }

    }
    
    public void unloadPollen(ObjectBeehive hive){
        if (getPollenCarried() > 0) {
            hive.setPollenInHive(getPollenCarried());
            setPollenCarried(ZERO);
        }

    }
    
    public void communicate(EnvironmentModel env, ObjectBeehive hive){
        if (hive.getNamesOfBeesInside().isEmpty()) return; // No bees in hive.
        if (hive.getNamesOfBeesInside().size() == 1) return; // Only one bee.
        
        for ( String nameOfBeeInHive : hive.getNamesOfBeesInside())
            if(!nameOfBeeInHive.equals(this.name)) // Don't dance to yourself! 
                danceFor((AgentBee)env.getEntity(nameOfBeeInHive));
    }
    

    
    /**
     * Dance for another bee. The other bee will learn of this bee's flower if it has more pollen.
     * @param other Bee that is watching this bee dance.
     */
    public void danceFor(AgentBee other){
        if(this.bestFlowerPollen == null) return; // Nothing to dance about.
        if(this.bestFlowerPollen == 0) return; // Nothing to dance about.
        if(other.bestFlowerPollen > this.bestFlowerPollen)
            return; // Other bee won't listen since their flower is better.

        // Otherwise, the other bee learns of this bee's flower.
        other.bestFlowerName = this.bestFlowerName;
        other.bestFlowerPollen = this.bestFlowerPollen;
        other.bestFlowerPos = this.bestFlowerPos;
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

    public String getHiveName() {
        return hiveName;
    }

    public void setHiveName(String hiveName) {
        this.hiveName = hiveName;
    }

    public behaviourType getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(behaviourType behaviour) {
        this.behaviour = behaviour;
    }

    public String getBestFlowerName() {
        return bestFlowerName;
    }

    public void setBestFlowerName(String bestFlowerName) {
        this.bestFlowerName = bestFlowerName;
    }

}
