package CharacterFiles;

import java.io.Serializable;

/**
 *class what consist actions with comodity and construction of comodity
 */
public class Comodity implements Serializable {
    private final String comodityType;                      // všetky komodity v hre wood - water - copper - iron - steel - processors - gene - robotics
    private int comodityIO;

    /**
     * constructor of comodity
     * @param type name of comodity
     * @param number price or count of actual comodit
     */
    public Comodity(String type,int number) {
        this.comodityIO = number;
        this.comodityType = type;
    }

    /**
     * getter of comodity type
     * @return comodity type
     */
    public String getComodityType() {
        return comodityType;
    }

    /**
     * getter of comodity price or count of comodity in town
     * @return price or count of comodity
     */
    public int getComodityIO() {
        return comodityIO;
    }

    /**
     * setter of comodity price or number of comodity in townn
     * @param comodityIO new price or number of comodity
     */
    public void setComodityIO(int comodityIO) {
        this.comodityIO = comodityIO;
    }
}