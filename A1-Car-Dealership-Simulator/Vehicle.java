/**
 * @author Nathalie Ng
 * 500921963
 */
public class Vehicle{
    /**
     * New instance variables created
     * GAS_ENGINE and ELECTRIC_MOTOR are constant variables
     */
    public static enum PowerSource{
        GAS_ENGINE, ELECTRIC_MOTOR;
    }
    public PowerSource power;
    protected String mfr;
    protected String color;
    protected int numWheels;

    /** 
     * This method is a constructor method to initialize instance variables
     */
    public Vehicle(){
        mfr = "unknown";
        color = "unknown";
        numWheels = 4;
    }

    /**
     * This method is a constructor method to initialize instance variables with set parameters
     * @param manuf is 
     * @param color
     * @param power
     */

    public Vehicle( String manuf, String color, PowerSource power){
        mfr = manuf;
        this.color = color;
        this.power = power;
    }
    /**
     * The following methods of get__() are to return the instance variables 
     * @return mfr for manufacturer 
     * @return color for color
     * @return power for power
     * @return numWheels for number of wheels
     */
    public String getMfr(){
        return mfr;
    }
    public String getColor(){
        return color;
    }
    public PowerSource getPower(){
        return power;
    }
    public int getWheels(){
        return numWheels;
    }
    /** 
     * methods of set___() are to assign parameter values to the instance variables
     * setMfr() will set the manufacturer name
     * @param name for manufacturer
     * setColor() will set the colour
     * @param c1
     * setPower will set the power
     * @param pwr
     * setWheels() will set the number of wheels
     * @param wheels
     */
    public void setMfr(String name){
        mfr = name;
    }
    public void setColor(String c1){
        color = c1;
    }
    public void setPower(PowerSource pwr){
        power = pwr;
    }
    public void setWheels(int wheels){
        numWheels = wheels;
    }

    /** 
     * This method will compare this vehicle to other vehicle obejct for equality
     * @param other type Vehicle 
     * @return value, which is either true or false
     */
    public boolean equals(Vehicle other){
        //compares this vehicle to other vehicle object for equality
        boolean value = false;;
        if(this.mfr == other.mfr ){
            if(this.numWheels == other.numWheels){
                if(this.power  == other.power){
                    value = true;
                }
            }
        }
        return value;
    }

    /**
     * display() is used to create the string that we later print
     * @return manufacture name and color
     */
    public String display(){
        return mfr + " " + color;

    }
}
