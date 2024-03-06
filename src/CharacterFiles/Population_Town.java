package CharacterFiles;

import GameFiles.TUI;

import java.util.ArrayList;
import java.util.Random;

/**
 * class what consist actions with population town and construction of population town
 */
public class Population_Town extends Town
{
    private final ArrayList<Comodity> demand;     //dopyt populacneho mesta
    private int reward;                           //odmena za danny dopyt

    /**
     * constructor of population town specific values
     * @param comodityDemandAll inventory of all comodities in game
     */
    public Population_Town(ArrayList<Comodity> comodityDemandAll) {
        super();
        this.setTownType("Population");
        this.setName("Population"+ new Random().nextInt(999));
        this.demand = new ArrayList<>();
        for (Comodity comodity : comodityDemandAll) {
            this.demand.add(new Comodity(comodity.getComodityType(), 0));
        }
    }

    /**
     * constructor of population town with complete random coordinates
     * @param startRadius radius of spawn where towns will be
     * @param comodityDemandAll inventory of all comodities in game
     */
    public Population_Town(int startRadius,ArrayList<Comodity> comodityDemandAll) {
        this(comodityDemandAll);
        this.setMap(new Map_Location(startRadius));
    }

    /**
     * method of outputting information about population town
     * @param tui TUI
     */
    @Override
    public void infoTown(TUI tui) {                                                                       //celkove informacie o dannom meste
        tui.inform_populationTown(this);
    }

    /**
     * method handling outputting comodities from car to populaton town
     * @param entity player
     * @param demandList actual cycle demand list
     * @param gameDay game day
     */
    @Override
    public void IO(Player entity, ArrayList<Comodity> demandList, int gameDay) {                         //transfer surovin za auta do mesta
        int input;
        for(int i = 0; i < entity.getCar().getActualInventory().size(); i++)                            //loop medzi inventarmi
        {
            ArrayList<Comodity> carInventory = entity.getCar().getActualInventory();
            if(this.demand.get(i).getComodityIO() > carInventory.get(i).getComodityIO())
            {
                input = carInventory.get(i).getComodityIO();
                this.demand.get(i).setComodityIO(this.demand.get(i).getComodityIO() - carInventory.get(i).getComodityIO());
                demandList.get(i).setComodityIO(demandList.get(i).getComodityIO() - carInventory.get(i).getComodityIO());
                carInventory.get(i).setComodityIO(0);
            }
            else
            {
                input = this.demand.get(i).getComodityIO();
                carInventory.get(i).setComodityIO(carInventory.get(i).getComodityIO() - this.demand.get(i).getComodityIO());
                this.demand.get(i).setComodityIO(0);
                demandList.get(i).setComodityIO(0);
            }
            if(input != 0)
            {
                TUI tui = new TUI();
                tui.inform_deliveryOutput(gameDay,this,input,i);                                   //hlasenie na konzolu
            }
        }
    }

    /**
     * getter of demand list of population town
     * @return demand list
     */
    public ArrayList<Comodity> getDemand() {
        return demand;
    }

    /**
     * setter of demand of population town
     * @param index iterator
     * @param quantity number of comodity to set
     */
    public void setDemand(int index,int quantity) {
        this.demand.get(index).setComodityIO(quantity);
    }

    /**
     * getter of actual reward of population town
     * @return reward
     */
    public int getReward() {
        return reward;
    }

    /**
     * setter of actual reward of population town
     * @param reward new reward
     */
    public void setReward(int reward) {
        this.reward = reward;
    }
}