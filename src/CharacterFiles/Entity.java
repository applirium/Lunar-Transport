package CharacterFiles;

import GameFiles.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * abstract class what consist actions with general entity and construction of general entity
 */
public abstract class Entity implements Serializable {
    private Map_Location map;      //pozicia entity na mape
    private String entityType;     //typ entity

    /**
     * constructor of general entity with random town of spawn
     * @param towns list of towns from which to choose
     */
    public Entity(ArrayList<Town> towns) {
        this.map = new Map_Location(towns);
    }

    /**
     * method of movement entity to random town
     * @param towns list of towns from which to choose
     * @return new town
     */
    public Town move(ArrayList<Town> towns) {
        int randomTownIncluding = new Random().nextInt(0,towns.size());
        this.map = towns.get(randomTownIncluding).getMap();
        return towns.get(randomTownIncluding);
    }

    /**
     * method of movement entity to specific town
     * @param town town where to move
     */
    public void move(Town town) {                                                                            //pohyb do daneho mesta
        this.map = town.getMap();
    }

    /**
     * abstract method of action to specific town
     * @param town town where do an action
     * @param demandList actual cycle demand list
     * @param gameDay game day
     */
    public abstract void action(Town town, ArrayList<Comodity> demandList, int gameDay);                     //visitor

    /**
     * abstract method of action to random town
     * @param towns list of towns from which to choose
     * @param demandList actual cycle demand list
     * @param gameDay game day
     */
    public abstract void action(ArrayList<Town> towns, ArrayList<Comodity> demandList, int gameDay);        //visitor

    /**
     * abstract method of output of information about entity
     * @param tui TUI
     */
    public abstract void infoEntity(TUI tui);

    /**
     * getter of actual position of entity on map
     * @return map location of entity
     */
    public Map_Location getMap() {
        return map;
    }

    /**
     * getter of entity type
     * @return type of entity
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     * setter of entity type
     * @param entityType new type of entity
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    /**
     * getter of entity actual money balance
     * @return money balance of entity
     */
    public int getMoney() {return 0;}

    /**
     * setter of entity actual money balance
     * @param money new money balance of entity
     */
    public void setMoney(int money) {}

    /**
     * getter of entities car
     * @return car of entity
     */
    public Car getCar() {
        return null;
    }

    /**
     * getter of path ending
     * @return true or false if path ended
     */
    public boolean getEnd(){return false;}

    /**
     * setter o path ending
     * @param b new end
     */
    public void setEnd(boolean b) {
    }


}