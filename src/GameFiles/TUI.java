package GameFiles;

import CharacterFiles.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class handling text user interface
 */
public class TUI extends Thread implements Serializable {
    private final Scanner scanner = new Scanner(System.in);
    private Thread t;

    /**
     *  method handling creation of new Thread
     */
    public void start () {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    /**
     * constructor of TUI on new Thread
     */
    public TUI()
    {
        this.start();
    }

    /**
     * method handling simple text prints
     * @param input string of what type of output to execute
     */
    public void getStatement(String input)
    {
        switch (input)
        {
            case "difficulty"-> System.out.println("Choose your game difficulty Easy / Moderate / Hard");
            case "error"-> System.out.println("Invalid input");
            case "decision" -> System.out.println("Choose a decision - To plan / To action / To get informations / To upgrade Car / To Save / To load / To end the game / Help to get explanation");
            case "actionError" -> System.out.println("Cannot act without path");
            case "informPath" -> System.out.println("Choose to create path - Normal path / Random path / Reversed path / Help to get explanation");
            case "notEnoughMoney" -> System.out.println("Dont have enough money");
            case "informMax" -> System.out.println("You have upgraded car to maximum level");
            case "loadError" -> System.out.println("You can't load a game because you haven't saved a game");
            case "loadStatement" -> System.out.println("You successfully loaded a game");
            case "saveStatement" -> System.out.println("You successfully saved a game");
            case "helpActions" ->
            {
                System.out.println("Plan = creating potential route");
                System.out.println("Action = playing last planned route");
                System.out.println("Save = Saving the game");
                System.out.println("Action = Loading the game");
                System.out.println("End = Ending the game");
                System.out.println("Informations = Get infomations about the towns and entities");
            }
            case "helpPath"->
            {
                System.out.println("Normal path = Delivering lower value material first,after higher value material");
                System.out.println("Random path = Delivering random value materials");
                System.out.println("Reversed path = Delivering higher value material first,after lower value material");
            }
        }
    }

    /**
     * method handling input from user
     * @return input from console
     */
    public String input()
    {
        return scanner.nextLine().toLowerCase();
    }

    /**
     * method handling output of fuel cost
     * @param fuel fuel consumption of path
     */
    public void inform_fuel(int fuel)
    {
        System.out.println("Cost of fuel: "+fuel+ " money.");
    }

    /**
     * method handling output for new demand cycle
     * @param game game
     * @param entity player
     */
    public void inform_startCycle(GameSettings game,Entity entity)
    {
        System.out.print("Day: "+game.getGameSettings().getGameDay()+" ");
        game.getDemandList().forEach((n) -> System.out.print("| " +n.getComodityType() + " " + n.getComodityIO() +" |"));
        System.out.println(" Money: "+entity.getMoney());
    }

    /**
     * method handling output for path distance sum and fuel consumption
     * @param fuel fuel consumption of path
     * @param distance sum of distance of path
     */
    public void inform_fuelDinstance(double fuel,double distance)
    {
        System.out.printf("Whole fuel consumption: %.02f l Distance: %.02f km\n",fuel,distance);
    }

    /**
     * method handling output for game end
     * @param game game
     */
    public void inform_game_over(GameSettings game)
    {
        System.out.println("Days survived "+ game.getGameSettings().getGameDay() +"\nGame over");
    }

    /**
     * method handling output for car upgrade
     * @param car players car
     */
    public void inform_upgrade(Car car)
    {
        System.out.println("You have upgraded car to level "+ car.getLevel() +" it has capacity of "+car.getCarryingCapacity()+" units");
    }

    /**
     * method handling output for general entity information
     * @param entity entity
     */
    public void inform_entity(Entity entity)
    {
        System.out.println(entity.getEntityType());
        System.out.println("Coordinates: "+entity.getMap().getX()+", "+entity.getMap().getY()+", "+entity.getMap().getZ());
    }

    /**
     * method handling output for player information
     * @param entity player
     */
    public void inform_player(Entity entity)
    {
        inform_entity(entity);
        System.out.println("Money: "+entity.getMoney());
        System.out.println("Car max carrying capacity: "+entity.getCar().getCarryingCapacity());
        System.out.print("Inventory: ");
        for (int i = 0; i < entity.getCar().getActualInventory().size(); i++)
        {
            System.out.print("| " + entity.getCar().getActualInventory().get(i).getComodityType() + " " + entity.getCar().getActualInventory().get(i).getComodityIO() +" |");
        }
        System.out.println("\n");
    }

    /**
     * method handling output for bandit information
     * @param entity bandit
     */
    public void inform_bandits(Entity entity)
    {
        inform_entity(entity);
        System.out.println(entity.getEntityType());
        System.out.println("Coordinates: "+entity.getMap().getX()+", "+entity.getMap().getY()+", "+entity.getMap().getZ() +"\n");
    }

    /**
     * method handling output for general town information
     * @param town town
     */
    public void inform_town(Town town)
    {
        System.out.println(town.getTownType());
        System.out.println("Coordinates: "+town.getMap().getX()+", "+town.getMap().getY()+", "+town.getMap().getZ());
        System.out.println("Development: "+town.getDevelopment());
        System.out.println("Energy: "+town.getEnergy());
        System.out.println("Name: "+town.getName());
        System.out.println("Happiness: "+town.getHappiness());
        System.out.println("People: "+town.getPeople());
    }

    /**
     * method handling output for production town information
     * @param town production town
     */
    public void inform_productionTown(Town town)
    {
        inform_town(town);
        System.out.println("Type of production: "+town.getProduction().getComodityType());
        System.out.println("Production per tick: "+town.getProductionPerTick());
        System.out.println("Actual inventory: " +town.getProduction().getComodityIO()+"\n");
    }

    /**
     * method handling output for population town information
     * @param town population town
     */
    public void inform_populationTown(Town town)
    {
        inform_town(town);
        System.out.println("Reward: "+town.getReward());
        System.out.print("Demand: ");
        inform_comodity(town.getDemand());
    }

    /**
     * method handling output for inventory information
     * @param comodity comodity
     */
    public void inform_comodity(ArrayList<Comodity> comodity)
    {
        comodity.forEach((n) -> System.out.print("| " +n.getComodityType() + " " + n.getComodityIO() +" |"));
        System.out.println("\n");
    }

    /**
     * method handling output for delivery information
     * @param gameDay game day
     * @param town population town
     * @param input actual delivery
     * @param i iteratpr
     */
    public void inform_deliveryOutput(int gameDay,Town town,int input,int i)
    {
        System.out.println("Day "+gameDay+ " Player delivered "+ input +" "+ town.getDemand().get(i).getComodityType() + " to "+town.getName());
    }

    /**
     * method handling output for collection information
     * @param gameDay dame day
     * @param town production town
     * @param input actual collection
     */
    public void inform_deliveryInput(int gameDay,Town town,int input)
    {
        System.out.println("Day "+gameDay+ " Player picked up "+ input +" "+ town.getProduction().getComodityType() + " from "+town.getName());
    }

    /**
     * method handling output for path information
     * @param game game
     * @param lastTown last visited town
     */
    public void inform_planed_path(GameSettings game, Town lastTown)
    {
        int i = 0;
        for (Town town : game.getPath()) {
            if(!lastTown.getName().equals(town.getName()))
            {
                System.out.println("Day " + (game.getGameSettings().getGameDay() + i) + " player will travel from " + lastTown.getName() + " to " + town.getName());
                lastTown = town;
            }
            i++;
        }
    }
}
