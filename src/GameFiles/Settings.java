package GameFiles;

import java.io.Serializable;

/**
 * class handling variables of game difficulties
 */
public class Settings implements Serializable {
    private final int startRadius;
    private int demand;
    private int gameDay;
    private final double degeneration;
    private double fuel;
    private final int maxProductionInventory;
    private final int startingGeneration;
    private final int demandEvolving;

    /**
     * constructor of Settings
     * @param startRadius radius of starting town generation
     * @param startingGeneration starting production town generation
     * @param demand sum of demand
     * @param gameDay game day
     * @param degeneration degeneration over time
     * @param maxProductionInventory max production town inventory
     * @param demandEvolving addition of demand after cycle
     */
    Settings(int startRadius, int startingGeneration, int demand, int gameDay, double degeneration, int maxProductionInventory, int demandEvolving)
    {
        this.startRadius = startRadius;
        this.startingGeneration = startingGeneration;
        this.demand = demand;
        this.gameDay = gameDay;
        this.degeneration = degeneration;
        this.maxProductionInventory = maxProductionInventory;
        this.demandEvolving = demandEvolving;
    }

    /**
     * getter of starting radius
     * @return starting radius
     */
    public int getStartRadius() {
        return startRadius;
    }

    /**
     * getter of demand
     * @return demand
     */
    public int getDemand() {
        return demand;
    }

    /**
     * setter of demand
     * @param demand new demand
     */
    public void setDemand(int demand) {
        this.demand = demand;
    }

    /**
     * getter of game day
     * @return game day
     */
    public int getGameDay() {
        return gameDay;
    }

    /**
     * setter of game day
     * @param gameDay new game day
     */
    public void setGameDay(int gameDay) {
        this.gameDay = gameDay;
    }

    /**
     * getter of degeneration
     * @return degeneration
     */
    public double getDegeneration() {
        return degeneration;
    }

    /**
     * getter of maximum production town inventory
     * @return maximum production town inventory
     */
    public int getMaxProductionInventory() {
        return maxProductionInventory;
    }

    /**
     * getter of starting generation
     * @return starting generation
     */
    public int getStartingGeneration() {
        return startingGeneration;
    }

    /**
     * getter of demand addition after cycle
     * @return demand addition after cycle
     */
    public int getDemandEvolving() {
        return demandEvolving;
    }

    /**
     * getter of fuel consumption per path
     * @return fuel consumption per path
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * setter of fuel consumption
     * @param fuel new fuel consumption per path
     */
    public void setFuel(double fuel) {
        this.fuel = fuel;
    }
}
