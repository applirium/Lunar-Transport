package GameFiles;

import CharacterFiles.Town;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * class handling path generation
 */
public class PathSettings implements Serializable {
    /**
     * constructor of PathSettings
     * @param game game
     */
    PathSettings(GameSettings game)
    {
        this.game = game;
    }
    private final GameSettings game;

    /**
     * method handling path settings
     */
    public void pathSettingsIO() {
        do {
            game.getTui().getStatement("informPath");
            String input = game.getTui().input();
            switch (input) {
                case "normal path", "random path", "reversed path"-> game.setPath(pathSettings(game.getTownList().get(0),input));
                case "help" -> game.getTui().getStatement("helpPath");
                default -> game.getTui().getStatement("error");
            }
        }
        while(game.getPath() == null);
    }

    /**
     * method creating path using given path settings
     * @param town population town
     * @param input decision of which type of path generate
     * @return new path
     */
    public ArrayList<Town> pathSettings(Town town,String input) {
        ArrayList<Town> path = new ArrayList<>();
        ArrayList<Town> townCopyProd = new ArrayList<>(game.getDividedTownList().get(0));

        ArrayList<Integer> loop = new ArrayList<>();

        switch (input)
        {
            case "normal path" -> {         //od najmensej ceny tovaru po najvacsiu
                for(int i = 0; i < 8; i++)
                {
                    loop.add(i);
                }
            }
            case "reversed path" -> {       //od najvacsej ceny tovaru po najmensiu
                for(int i = 7; i >= 0; i--)
                {
                    loop.add(i);
                }
            }
            case "random path" -> {         //nahodne poradie tovaru
                for(int i = 0; i < 8; i++)
                {
                    loop.add(i);
                }
                Collections.shuffle(loop);
            }
        }
        for (int i: loop)
        {
            if (town.getDemand().get(i).getComodityIO() != 0 && town.getDemand().get(i).getComodityType().equals(townCopyProd.get(i).getProduction().getComodityType()))
            {
                float maxLoads = (float) game.getEntityList().get(0).getCar().getCarryingCapacity() / townCopyProd.get(i).getProductionPerTick();
                float repeat = (float)  town.getDemand().get(i).getComodityIO() / game.getEntityList().get(0).getCar().getCarryingCapacity();

                maxLoads = (float) Math.ceil(maxLoads);
                repeat = (float) Math.ceil(repeat);

                while (repeat > 0) {
                    for (int j = 0; j < (int) maxLoads; j++) {
                        path.add(game.getDividedTownList().get(0).get(i));
                    }
                    path.add(town);
                    repeat -= 1;
                }
                path.add(town);
            }
        }
        return path;
    }

    /**
     * method handling information about whole path complete fuel consumption and path distance
     * @param path path
     * @return fuel consumption of path
     */
    public double pathInfo(ArrayList<Town> path) {
        Town previousTown = null;
        double fuel = 0, distance = 0;
        for (Town town : path) {
            if (previousTown == null) {
                previousTown = town;
                continue;
            }
            distance += town.getMap().distance(previousTown.getMap());
            fuel += previousTown.getMap().fuel(town.getMap());
            previousTown = town;
        }
        game.getTui().inform_fuelDinstance(fuel,distance);
        return fuel;
    }
}
