package CharacterFiles;

import GameFiles.TUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * abstract class what consist actions with town and construction of town
 */
public abstract class Town implements Serializable
{
    private double energy;                  //celkova energia mesta
    private final int happiness;            //celkove stastie mesta
    private final int development;          //celkova vyspelost mesta
    private String name;                    //meno mesta
    private final double people;            //pocet obyvatelov
    private Map_Location map;               //pozicia mesta
    private String townType;                //typ mesta

    /**
     * constructor of general towns specific values
     */
    public Town() {
        Random random = new Random();
        this.energy = 100;
        this.people = random.nextInt(1,100);
        this.development = random.nextInt(0,100);
        this.happiness = random.nextInt(0,100);
    }

    /**
     * general method of outputting information about general town
     * @param tui TUI
     */
    public abstract void infoTown(TUI tui);

    /**
     * general method handling input and output of towns
     * @param entity player
     * @param demandList actual cycle demand list
     * @param gameDay game day
     */
    public abstract void IO(Player entity,ArrayList<Comodity> demandList, int gameDay);

    /**
     * getter towns happiness
     * @return towns happiness
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * getter of towns development
     * @return towns development
     */
    public int getDevelopment() {
        return development;
    }

    /**
     * getter of towns name
     * @return towns name
     */
    public String getName() {
        return name;
    }

    /**
     * setter of towns name
     * @param  name towns new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter of towns population
     * @return towns population
     */
    public double getPeople() {
        return people;
    }

    /**
     * getter of towns actual position
     * @return towns actual position
     */
    public Map_Location getMap() {
        return map;
    }

    /**
     * setter of towns actual position
     * @param map towns new position
     */
    public void setMap(Map_Location map) {
        this.map = map;
    }

    /**
     * getter of towns energy
     * @return towns energy
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * setter of towns energy
     * @param energy new towns energy
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }

    /**
     * getter of towns type
     * @return towns type
     */
    public String getTownType() {
        return townType;
    }

    /**
     * setter of towns type
     * @param townType new towns type
     */
    public void setTownType(String townType) {
        this.townType = townType;
    }

    /**
     * getter of towns demand
     * @return towns demand
     */
    public ArrayList<Comodity> getDemand() {return null;}

    /**
     * setter of towns demand
     * @param index iterator
     * @param quantity number of comodity to set
     */
    public void setDemand(int index,int quantity) {}

    /**
     * getter of towns reward
     * @return towns reward
     */
    public int getReward() {return 0;}

    /**
     * setter of towns reward
     * @param reward new towns reward
     */
    public void setReward(int reward) {}

    /**
     * getter of towns production
     * @return towns production
     */
    public Comodity getProduction(){return null;}

    /**
     * getter of towns production per day
     * @return towns production per day
     */
    public int getProductionPerTick() {return 0;}
}