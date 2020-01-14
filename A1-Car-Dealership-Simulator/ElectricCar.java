/**
 * @author Nathalie Ng
 * 500921963
 */
public class ElectricCar extends Car{

    /**
     * New instance variables created
     */
    private int rechargeTime;
    private String batteryType;

    /**
     * Constructor method to initialize variables 
     */
    public ElectricCar(){
        super();
        rechargeTime = 0;
        batteryType = "unknown";
    }

     /**
     * Constructor method with parameters to set variables if the car is electric
     * Each of the values taken by the parameters will set the corresponding instance variable to that value 
     * @param manufacterer to mfr in class Vehicle
     * @param col to color in class vehicle
     * @param carModel to model
     * @param pwr to power in class vehicle
     * @param safety to safetyRating
     * @param range to maxRange
     * @param drive to AWD
     * @param cost to price
     * @param battery to batteryType
     * @param recharge to rechargeTime
     */
    public ElectricCar(String manufacterer, String col, ModelType carModel, PowerSource pwr, double safety, int range, boolean drive, double cost, String battery, int recharge){
        super(manufacterer,col, carModel, pwr, safety,range,drive,cost);
        batteryType = battery;
        rechargeTime = recharge;
    }
    /**
     * get__() methods will return instance variables 
     * @return rechargeTime
     * @return batteryType
     */
    public int getRechargeTime(){
        return rechargeTime;
    }
    public String getBatteryType(){
        return batteryType;
    }
    
    /**
     * set__() methods will set instance variables
     * setRechargeTime()
     * @param time
     * setBatteryType
     * @param type 
     */
    public void setRechargeTime(int time){
        rechargeTime = time;
    }
    public void setBatteryType(String type){
        batteryType = type;
    }

    /**
     * display() is used to create the string that we later print
     * @return manufacture name and color
     */
    public String display(){
        return super.display() + " EL, BAT: " + batteryType + " RCH: " + rechargeTime;
    }
}