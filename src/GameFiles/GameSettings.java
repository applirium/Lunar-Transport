package GameFiles;
import java.io.*;
import java.util.*;
import CharacterFiles.*;
import PatternFiles.ObserverDemand;

/**
 *  class handling whole game logic
 */
public class GameSettings implements Serializable
{
    private Settings gameSettings;                                                  //struktura nastavenia hry
    private ArrayList<Town> townList;                                               //list vsetkych aktivnych miest
    private ArrayList<Entity> entityList;                                           //list vsetkych aktivnych entit
    private ArrayList<ArrayList<Town>> dividedTownList;                             //list rozdelenych vsetkych miest PRODUCTION - POPULATION
    private ArrayList<Town> path;                                                   //list miest naplanovanej cesty
    private ArrayList<Comodity> demandList;                                         //list vsetkych pozadovancych komodit
    private ArrayList<Comodity>  comodityListAll;                                   //list vsetkych existujucich komodit
    private final ArrayList<ObserverDemand> observerDemandList = new ArrayList<>(); //list observerov
    private static final TUI tui = new TUI();
    private static final Random random = new Random();

    /**
     * main method where is game running
     * @param args args
     */
    public static void main(String[] args)
    {
        GameSettings game = new GameSettings();
        game.gameStart();
        do
        {
            game = game.gameLoopDecisionLogic(game.getGameSettings());
        }
        while(game != null);
    }

    /**
     * method of pinning the observer to town
     * @param town town to Observe
     * @param player player
     */
    public void ObserverFollow(Town town, Entity player) {      //pridavanie observerov
        observerDemandList.add(new ObserverDemand(town, player));
    }

    /**
     * method of updating the observer
     * @param town town to Observe
     */
    public void ObserverUpdate(Town town) {
        for(ObserverDemand observerDemand: observerDemandList)
        {
            if(observerDemand.getTown() == town)
                observerDemand.inform();
        }
    }

    /**
     * method of setting main settings of a game, setting towns,entities and observers, setting starting demand, comodity prices,
     */
    public void gameStart() {
        path = null;
        townList = new ArrayList<>();
        entityList = new ArrayList<>();
        comodityListAll = new ArrayList<>();
        demandList = new ArrayList<>();
        dividedTownList = new ArrayList<>();

        comodityListAll.add(new Comodity("Wood",3));
        comodityListAll.add(new Comodity("Water",3));
        comodityListAll.add(new Comodity("Copper",5));
        comodityListAll.add(new Comodity("Iron",5));
        comodityListAll.add(new Comodity("Steel",7));
        comodityListAll.add(new Comodity("Processor",12));
        comodityListAll.add(new Comodity("Gene",21));
        comodityListAll.add(new Comodity("Robotics",40));

        for(int i = 0; i<comodityListAll.size(); i++)
        {
            if(i < 2)
                dividedTownList.add(new ArrayList<>());

            this.demandList.add(new Comodity(comodityListAll.get(i).getComodityType(),0));
        }

        String gameDifficulty;
        do
        {
            tui.getStatement("difficulty");
            gameDifficulty = tui.input().toLowerCase();
        }
        while(!gameDifficulty.equals("easy") && !gameDifficulty.equals("moderate") && !gameDifficulty.equals("hard"));

        switch(gameDifficulty)
        {
            case "easy"-> setGameSettings(new Settings(5,10,300,0,0.3,200,50));     //parametre ako sa vytvori hra pomocou obtiaznosti
            case "moderate"-> setGameSettings(new Settings(10,7,500,0,0.5,150,70));
            case "hard"-> setGameSettings(new Settings(20,5,1000,0,1,100,100));
            default -> tui.getStatement("error");
        }

        Town townPopulation = new Population_Town(gameSettings.getStartRadius(),comodityListAll);
        dividedTownList.get(1).add(townPopulation);
        townList.add(townPopulation);

        for(int i = 0; i < 8; i++)
        {
            Comodity TownInventory = new Comodity(comodityListAll.get(i).getComodityType(),0);
            Town townProduction = new Production_Town(gameSettings.getStartRadius(),TownInventory,gameSettings.getStartingGeneration());
            dividedTownList.get(0).add(townProduction);
            townList.add(townProduction);
        }

        entityList.add(new Player(dividedTownList.get(1),comodityListAll));
        entityList.add(new Bandits(dividedTownList.get(0)));


        for(Town town: dividedTownList.get(1))
        {
            ObserverFollow(town,entityList.get(0));
        }

        demandList(gameSettings.getDemand());
    }

    /**
     * method handling main game cycle
     * @param gameSettings settings of game
     * @return game
     */
    public GameSettings gameLoopDecisionLogic(Settings gameSettings) {
        PathSettings pathSettings = new PathSettings(this);
        ActionSettings actionSettings = new ActionSettings(this);
        while(dividedTownList.get(1).size() != 0)
        {
            if(path == null)
                tui.inform_startCycle(this, entityList.get(0));

            tui.getStatement("decision");
            switch (tui.input())
            {
                case "plan" ->{                     //planovanie
                    pathSettings.pathSettingsIO();
                    Town lastTown = dividedTownList.get(1).get(0);

                    for(Town town: dividedTownList.get(1))
                    {
                        if(entityList.get(0).getMap() == town.getMap())
                        {
                            lastTown = town;
                            break;
                        }
                    }
                    tui.inform_planed_path(this,lastTown);
                    gameSettings.setFuel(pathSettings.pathInfo(path));
                }
                case "action" -> {                  //vykonavanie tras
                    try{
                        for(Town town: path)
                            if(!entityList.get(0).getEnd())
                                actionSettings.ActionPlay(town, gameSettings.getMaxProductionInventory(), gameSettings.getDegeneration());
                    }
                    catch(NullPointerException e){
                        tui.getStatement("actionError");
                        continue;
                    }
                    entityList.get(0).setMoney(entityList.get(0).getMoney() - (int)Math.round(gameSettings.getFuel() *25));
                    tui.inform_fuel((int)Math.round(gameSettings.getFuel() *25));
                    demandUpgrade(gameSettings.getDemand(), gameSettings.getDemandEvolving());
                }
                case "save" -> {                //serializacia
                    try {
                        FileOutputStream fileOut = new FileOutputStream("object.ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(this);
                        out.close();
                        fileOut.close();
                        tui.getStatement("saveStatement");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "load" -> {                //deserializacia
                    GameSettings game;
                    try {
                        FileInputStream fileIn = new FileInputStream("object.ser");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        game = (GameSettings) in.readObject();
                        in.close();
                        fileIn.close();
                        tui.getStatement("loadStatement");
                        return game;
                    }
                    catch (Exception e) {
                        tui.getStatement("loadError");
                    }
                }
                case "info" -> {                //informacie o entitach a mestach
                    tui.inform_comodity(demandList);
                    tui.inform_comodity(comodityListAll);
                    for(Entity entity: entityList)
                        entity.infoEntity(tui);

                    for(Town town: townList)
                        town.infoTown(tui);
                }
                case "upgrade" -> carUpgrade(entityList.get(0));        //upgrade auta
                case "help" -> tui.getStatement("helpActions");    //help
                case "end" -> {                                          //ukoncenie hry
                    tui.inform_game_over(this);
                    return null;
                }
                default -> tui.getStatement("error");
            }
        }
        return null;
    }

    /**
     * method handling upgrading and resetting demand list of population town after executing the path
     * @param actualDemand sum of demands
     * @param demandEvolution addition of demand
     */
    public void demandUpgrade(int actualDemand, int demandEvolution) {  //reset pozadovanych surovin do dalsieho kola
        gameSettings.setDemand(actualDemand + demandEvolution);
        demandList(gameSettings.getDemand());
        entityList.get(0).setEnd(false);
        path = null;

        for(Town town: townList)
        {
            try
            {
                town.getProduction().setComodityIO(0);
            }
            catch (Exception ignored){}

            town.setEnergy(100);
        }
    }

    /**
     * method handling creation of demand list for population town
     * @param maxDemand sum of demands
     */
    public void demandList(int maxDemand) {
        int oneComodit, numberOfComodits = dividedTownList.get(1).size() * comodityListAll.size();

        for(Town town: dividedTownList.get(1))
        {
            for(int i = comodityListAll.size()-1; i >= 0 ; i--)
            {
                if(numberOfComodits <= 2)
                    oneComodit = Math.round((float)(maxDemand / numberOfComodits));

                else
                {
                    oneComodit =  Math.round((float)(maxDemand / numberOfComodits)) + random.nextInt(-10,10);
                    if(oneComodit < 0)
                        oneComodit = 0;
                }

                town.setDemand(i, oneComodit);
                town.setReward(oneComodit * comodityListAll.get(i).getComodityIO() + town.getReward());
                demandList.get(i).setComodityIO(demandList.get(i).getComodityIO() + oneComodit);

                maxDemand -= oneComodit;
                numberOfComodits--;
            }
        }
    }

    /**
     * inner class handling exception in GameSettings
     */
    public static class NotEnoughMoneyExeption extends Exception implements Serializable {
        /**
         * method creating new own exception
         * @param message ""
         */
        public NotEnoughMoneyExeption(String message)
        {
            super(message);
        }
    }

    /**
     * method handling upgrading of players car
     * @param entity player
     */
    public void carUpgrade(Entity entity) {
        Car car = entity.getCar();
        int money = entity.getMoney();
        int constant;
        int capacity;

        switch (car.getLevel()) {
            case 1 -> {
                capacity = 50;
                constant = 1000;
            }
            case 2 -> {
                capacity = 100;
                constant = 10000;
            }
            case 3 -> {
                capacity = 200;
                constant = 20000;
            }
            default -> {
                tui.getStatement("informMax");
                return;
            }
        }
        try {
            if(money - constant < 0)
                throw new NotEnoughMoneyExeption("");       //ak hrac m nedostatok penazí, výnimka

            car.setLevel(car.getLevel() + 1);
            car.setCarryingCapacity(capacity);
            entity.setMoney(money - constant);

            tui.inform_upgrade(entityList.get(0).getCar());

        }
        catch (NotEnoughMoneyExeption e) {
            tui.getStatement("notEnoughMoney");
            entity.setMoney(money);
        }

    }

    /**
     * getter of divided town list
     * @return divided town list
     */
    public ArrayList<ArrayList<Town>> getDividedTownList() {
        return dividedTownList;
    }

    /**
     * getter of demand list
     * @return demand list
     */
    public ArrayList<Comodity> getDemandList() {
        return demandList;
    }

    /**
     * getter of entity list
     * @return entity list
     */
    public ArrayList<Entity> getEntityList() {
        return entityList;
    }

    /**
     * getter of path
     * @return path
     */
    public ArrayList<Town> getPath() {
        return path;
    }

    /**
     * getter of town list
     * @return town list
     */
    public ArrayList<Town> getTownList() {
        return townList;
    }

    /**
     * getter of TUI
     * @return TUI
     */
    public TUI getTui() {
        return tui;
    }

    /**
     * getter of Settings
     * @return Settings
     */
    public Settings getGameSettings() {
        return gameSettings;
    }

    /**
     * setter of Settings
     * @param gameSettings new settings
     */
    public void setGameSettings(Settings gameSettings) {
        this.gameSettings = gameSettings;
    }

    /**
     * setter of Path
     * @param path new path
     */
    public void setPath(ArrayList<Town> path) {
        this.path = path;
    }
}