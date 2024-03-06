package CharacterFiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * class what consist actions with map position and construction of map position
 */
public class Map_Location implements Serializable {
    private final int x;                 //x suradnica na mape
    private final int y;                 //y suradnica na mape
    private final int z;                 //z suradnica na mape
    private final Random random = new Random();

    /**
     * constructor of complete random map position in radius
     * @param startRadius radius of spawn where towns will be
     */
    public Map_Location(int startRadius) {
        this.x = random.nextInt(-startRadius,startRadius);
        this.y = random.nextInt(-startRadius,startRadius);
        this.z = random.nextInt(-startRadius,startRadius);
    }

    /**
     * constructor of random map position from given list
     * @param towns list of towns from which to choose
     */
    public Map_Location(ArrayList<Town> towns) {
        int temp = random.nextInt(0,towns.size());
        this.x = towns.get(temp).getMap().getX();
        this.y = towns.get(temp).getMap().getY();
        this.z = towns.get(temp).getMap().getZ();
    }
    /**
     * method of calculation of distance between two points
     * @param x another entity or town
     * @return distance between this and X
     */
    public double distance(Map_Location x) {
        double X = Math.pow(x.getX()-this.getX(),2);
        double Y = Math.pow(x.getY()-this.getY(),2);
        double Z = Math.pow(x.getZ()-this.getZ(),2);
        double sum = Math.round(Math.sqrt(X+Y+Z)*100);
        return sum/100;
    }

    /**
     * method of calculating third square root of real number
     * @param A real number
     * @return 3rd square root of A
     */
    static double thirdSqrt(double A) {
        double xPre = Math.random() % 10;
        double eps = 0.001;
        double delX = 2147483647;
        double xK = 0.0;
        while (delX > eps)
        {
            xK = ((3 - 1.0) * xPre + A / Math.pow(xPre, 3 - 1)) / (double) 3;
            delX = Math.abs(xK - xPre);
            xPre = xK;
        }
        return xK;
    }

    /**
     * method of calculating fuel consumption of car between two towns
     * @param x another entity or town
     * @return fuel consumption of road section
     */
    public double fuel(Map_Location x){                             //vypocet spotreby auta
        double X = Math.pow(x.getX() - this.getX(), 2);
        double Y = Math.pow(x.getY() - this.getY(), 2);
        double Z = Math.pow(x.getZ() - this.getZ(), 2);

        double fuel = Math.toDegrees(Math.acos(Math.sqrt(X + Y) / Math.sqrt(X + Y + Z)));
        if (this.getZ() > x.getZ())
            fuel = -fuel;

        double eq = 0.07*thirdSqrt(fuel);
        double distance = distance(x)*0.055;
        if(distance == 0)
            return 0;

        return distance + Math.pow(eq,3);
    }

    /**
     * getter of X coordinate
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * getter of Y coordinate
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * getter of Z coordinate
     * @return Z coordinate
     */
    public int getZ() {
        return z;
    }
}