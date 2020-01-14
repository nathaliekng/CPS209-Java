/**
 * @author Nathalie Ng
 * 500921963
 */
public class Car extends Vehicle implements Comparable<Car>{

    /**
     * New instance variables created, protected so that we can still access the variable later 
     * SEDAN, SUV, SPORTS, MINIVAN are constant variables
     */
    public static enum ModelType {
        SEDAN, SUV, SPORTS, MINIVAN;
    }
    public ModelType model;
    protected int maxRange;
    protected double safetyRating;
    protected boolean AWD;
    protected double price;

    /**
     * Constructor method to initialize variables 
     */
    public Car(){
        super();
        maxRange = 0;
        safetyRating = 0;
        price = 0;
        AWD = true;
    }
    /**
     * Constructor method with parameters to set variables if the car is not electric
     * Each of the values taken by the parameters will set the corresponding instance variable to that value 
     * @param manufacterer to mfr in class Vehicle
     * @param col to color in class vehicle
     * @param carModel to model
     * @param pwr to power in class vehicle
     * @param safety to safetyRating
     * @param range to maxRange
     * @param drive to AWD
     * @param cost to price
     */
    public Car(String manufacterer, String col, ModelType carModel, PowerSource pwr, double safety, int range, boolean drive, double cost){
        super(manufacterer, col, pwr);
        model = carModel;
        safetyRating = safety;
        maxRange = range;
        AWD = drive;
        price = cost;
        
    }
    
    /**
     * display() is used to create the string that we later print
     * @return manufacture name and color (from Vehicle), model, safetyRating, maxRange and price
     */
    public String display(){
        return super.display() + " " + model + " " + price + "$" + " SF: " + safetyRating + " RNG: " + maxRange;
    }

    /**
     * Compares this Car object and other Car object for equality
     * @param other object
     * @return value (boolean type true or false);
     */
    public boolean equals(Car other){
        boolean value = false;
        if(super.equals(other) == true){
            if(this.model == other.model){
                if(this.AWD == other.AWD){
                    value = true;
                }
            }
        }
        return value;
    }

    /**
     * Compares this Car object and other Car object using price, later used to sort 
     * @param other object 
     * @return integer value to determine placement when sorting 
     */
    public int compareTo(Car other){
        if( this.price >other.price ){
            return 1;
        }
        else if(this.price<other.price){
            return -1;
        }
        else{
            return 0;
        }
    }
}