package PatternFiles;
import CharacterFiles.*;
import java.io.Serializable;

/**
 * class handling specific Observer implementation
 */
public class ObserverDemand implements Observer, Serializable {
    private final Town town;
    private final Entity player;

    /**
     * constructor of Observer
     * @param town observed town
     * @param player player
     */
    public ObserverDemand(Town town, Entity player)
    {
        this.town = town;
        this.player = player;
    }

    /**
     *  method overridden implemented interface, handling demand observing
     */
    @Override
    public void inform() {
        try{
            int temp = town.getDemand().stream().mapToInt(Comodity::getComodityIO).sum();
            if(temp == 0)
            {
                player.setMoney(player.getMoney() + town.getReward());
                player.setEnd(true);
                town.setReward(0);
            }
        }
        catch (NullPointerException ignored){}
    }

    /**
     * getter of Observed town
     * @return observed town
     */
    public Town getTown() {
        return town;
    }
}
