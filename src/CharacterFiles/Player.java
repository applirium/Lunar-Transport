package CharacterFiles;

import GameFiles.TUI;

import java.util.ArrayList;

/**
 * class what consist actions with player and construction of player
 */
public class Player extends Entity {
    private int money;          //celkovy pocet penzai hraca
    private final Car car;      //auto hraca
    private boolean end;        //status ci dokoncil trasu

    /**
     * constructor of player with random population town of spawn
     * @param towns list of towns from which to choose
     * @param inventory inventory of all comodities in game
     */
    public Player(ArrayList<Town> towns, ArrayList<Comodity> inventory) {
        super(towns);
        this.setEntityType("Player");
        this.money = 0;
        this.car = new Car(inventory);
    }

    /**
     * method of player action on specific one town
     * @param town specific town
     * @param demandList actual cycle demand list
     * @param gameDay game day
     */
    @Override
    public void action(Town town, ArrayList<Comodity> demandList, int gameDay) {
        this.move(town);
        town.IO(this,demandList, gameDay);
    }

    /**
     * method of player action on random town
     * @param towns list of towns from which to choose
     * @param demandList actual cycle demand list
     * @param gameDay game day
     */
    @Override
    public void action(ArrayList<Town> towns, ArrayList<Comodity> demandList, int gameDay) {
        Town temp = this.move(towns);
        action(temp,demandList,gameDay);
    }

    /**
     * method of outputting information about bandit
     * @param tui TUI
     */
    @Override
    public void infoEntity(TUI tui) {
        tui.inform_player(this);
    }

    /**
     * getter of players actual money balance
     * @return players money balance
     */
    public int getMoney() {
        return money;
    }

    /**
     * setter of players actual money balance
     * @param money new players money balance
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * getter of players car
     * @return players car
     */
    public Car getCar() {
        return car;
    }

    /**
     * getter of path ending
     * @param end true or false if path ended
     */
    public void setEnd(boolean end) {
        this.end = end;
    }

    /**
     * setter of path ending
     * @return true or false if path ended
     */
    public boolean getEnd() {
        return end;
    }
}