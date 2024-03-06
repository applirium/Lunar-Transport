package CharacterFiles;

import GameFiles.TUI;

import java.util.ArrayList;
import java.util.Random;

/**
 * class what consist actions with production town and construction of production town
 */
public class Production_Town extends Town
{
    private final Comodity production;    //produkcia danneho mesta
    private int productionPerTick;        //produkcia danneho mesta za jedno kolo

    /**
     * constructor of production towns specific values
     * @param comodity production of specific town
     * @param startingGeneration starting generation of production town
     */
    public Production_Town(Comodity comodity,int startingGeneration) {
        super();
        this.setTownType("Production");
        this.setName("Production"+ new Random().nextInt(999));
        this.production = comodity;
        this.productionPerTick = startingGeneration;
    }

    /**
     * constructor of production town with complete random coordinates
     * @param startRadius radius of spawn where towns will be
     * @param comodity production of specific town
     * @param startingGeneration starting generation of production town
     */
    public Production_Town(int startRadius, Comodity comodity,int startingGeneration) {
        this(comodity, startingGeneration);
        this.setMap(new Map_Location(startRadius));
        this.productionPerTick = startingGeneration;
    }

    /**
     * method of outputting information about production town
     * @param tui TUI
     */
    @Override
    public void infoTown(TUI tui) {                                                         //celkove informacie o meste
        tui.inform_productionTown(this);
    }

    /**
     * method handling outputting comodities from production town to car
     * @param entity player
     * @param demandList actual cycle demand list
     * @param gameDay game day
     */
    @Override
    public void IO(Player entity, ArrayList<Comodity> demandList, int gameDay) {            //transfer surovin z mesta do auta
        int productionMax, inventorySum,input;
        Car car = entity.getCar();
        for(int i = 0; i < car.getActualInventory().size(); i++)
        {
            Comodity inverntory = car.getActualInventory().get(i);
            if(inverntory.getComodityType().equals(this.production.getComodityType()))      //finding index of produced comodity
            {
                inventorySum = 0;
                for(Comodity sameInventory: car.getActualInventory())                       //chcek ci sucet sa zmesti do inventaru
                {
                    inventorySum += sameInventory.getComodityIO();
                }

                productionMax = this.production.getComodityIO();
                if(demandList.get(i).getComodityIO() <= inverntory.getComodityIO() + productionMax)
                    productionMax = demandList.get(i).getComodityIO() - inverntory.getComodityIO();            //uprava produkcie aby sa do inventaru nebrali suroviny, ktore nie su pozadovane

                if(inventorySum + productionMax <= car.getCarryingCapacity() && inverntory.getComodityIO() + productionMax <= demandList.get(i).getComodityIO())     //chcek ci sucet sa zmesti do inventaru a nie je hranicny s demand listom
                {
                    input = productionMax;
                    inverntory.setComodityIO(inverntory.getComodityIO() + productionMax);
                    this.production.setComodityIO(this.production.getComodityIO() - productionMax);
                }
                else
                {
                    input = car.getCarryingCapacity() - inventorySum ;
                    this.production.setComodityIO(this.production.getComodityIO() + inventorySum - car.getCarryingCapacity());
                    inverntory.setComodityIO(inverntory.getComodityIO() - inventorySum + car.getCarryingCapacity());      //ak nie max
                }

                if(input != 0)
                {
                    TUI tui = new TUI();
                    tui.inform_deliveryInput(gameDay,this,input);                                                   //hlasenie na konzolu
                }
                break;
            }
        }
    }

    /**
     * getter of production of production town
     * @return production of specific town
     */
    public Comodity getProduction() {
        return production;
    }

    /**
     * setter of production of produciton town
     * @return production per day
     */
    public int getProductionPerTick() {
        return productionPerTick;
    }
}