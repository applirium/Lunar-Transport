package CharacterFiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * class what consist actions with a car and construction of car
 */
public class Car implements Serializable
{
    private int level;                                      //level auta
    private int carryingCapacity;                           //maximalna kapacita
    private final ArrayList<Comodity> actualInventory;      //inventar auta

    /**
     * constructor of car
     * @param inventory specific inventory
     */
    public Car(ArrayList<Comodity> inventory) {
        this.actualInventory = new ArrayList<>();
        for (Comodity comodity : inventory)
            this.actualInventory.add(new Comodity(comodity.getComodityType(), 0));

        this.level = 1;
        this.carryingCapacity = 30;
    }

    /**
     * getter o carrying capacity of car
     * @return capacity of car
     */
    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    /**
     * setter of carrying capacity of car
     * @param carryingCapacity new carrying capacity
     */
    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    /**
     * getter of actual inventory of car
     * @return inventory of car
     */
    public ArrayList<Comodity> getActualInventory() {
        return actualInventory;
    }

    /**
     * getter of actual car level
     * @return car level
     */
    public int getLevel() {
        return level;
    }

    /**
     * setter of actual car level
     * @param level new car level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}