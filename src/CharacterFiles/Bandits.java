package CharacterFiles;

import GameFiles.TUI;

import java.util.ArrayList;

/**
 * class what consist actions with bandits and construction of bandits
 */
public class Bandits extends Entity {
    /**
     * constructor of bandits with random town of spawn
     * @param towns list of towns from which to choose
     */
    public Bandits(ArrayList<Town> towns) {
        super(towns);
        this.setEntityType("Bandit");
    }

    /**
     * method of action of bandit on specific one town
     * @param town specific town
     * @param demandList demand list of cycle
     * @param gameDay game day
     */
    @Override
    public void action(Town town, ArrayList<Comodity> demandList, int gameDay) {
        this.move(town);
        if(town.getTownType().equals("Population") && town.getReward() - 10 > 0)
            town.setReward(town.getReward() - 10);

        else if(town.getProduction().getComodityIO() - 3 > 0)
            town.setEnergy(town.getEnergy() - 0.3);
    }

    /**
     * method of action of bandit on random town
     * @param towns list of towns from which to choose
     * @param demandList demand list of cycle
     * @param gameDay game day
     */
    @Override
    public void action(ArrayList<Town> towns, ArrayList<Comodity> demandList, int gameDay) {
        Town temp = this.move(towns);
        this.action(temp,demandList,gameDay);
    }

    /**
     * method of outputting information about bandit
     * @param tui TUI
     */
    @Override
    public void infoEntity(TUI tui) {                                                                //kompletne informacie o entite
        tui.inform_bandits(this);
    }
}