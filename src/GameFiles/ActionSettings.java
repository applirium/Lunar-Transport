package GameFiles;
import CharacterFiles.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * class handling after path generation part of game
 */
public class ActionSettings implements Serializable {
    private final GameSettings game;

    /**
     * constructor of ActionSettings
     * @param game game
     */
    ActionSettings(GameSettings game)
    {
        this.game = game;
    }

    /**
     * method handling after day changes of game - IO and entity actions
     * @param town part of path
     * @param maxProductionInventory max production inventory
     * @param degeneration degeneration of price
     */
    public void ActionPlay(Town town, int maxProductionInventory, double degeneration) {    //spustenie naplanovanej cesty
        oneDay(maxProductionInventory,degeneration,game.getTownList());
        for(int i = 0; i < game.getEntityList().size(); i++)
        {
            Entity entity = game.getEntityList().get(i);
            if(i == 0)
            {
                entity.action(town,game.getDemandList(),game.getGameSettings().getGameDay());
                game.ObserverUpdate(town);
            }
            else
                entity.action(game.getTownList(), game.getDemandList(), game.getGameSettings().getGameDay());


        }

    }

    /**
     * method handling changes of day upgrading - addition of production per day, degeneration
     * @param maxProductionInventory max production inventory
     * @param degeneration degeneration of price
     * @param townList all towns in game
     */
    public void oneDay(int maxProductionInventory, double degeneration, ArrayList<Town> townList) {     //cyklus dní
        for (Town town: townList) {
            try
            {
                town.getProduction().setComodityIO(Math.min(town.getProductionPerTick() + town.getProduction().getComodityIO(), maxProductionInventory));
            }
            catch (NullPointerException ignored)
            {
                town.setReward(town.getReward() - (int)degeneration*5);        //ak sa do inverntaru mesta zmensti produkia zvysi sa, ak nie zvysi sa na maximum
            }
        }
        game.getGameSettings().setGameDay(game.getGameSettings().getGameDay() + 1);
    }
}
