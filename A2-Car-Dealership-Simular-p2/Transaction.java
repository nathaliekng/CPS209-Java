/**
 * @author Nathalie Ng
 * 500921963
 */
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction{
    protected int ID;
    protected Calendar date;
    protected String name;
    protected String transType; 
    protected double price;
    private Car car;

    /**
     * Transaction constructor to set variables, used in add() from AccountingSystem
     * @param transID of type int
     * @param c of type Calendar
     * @param person of type string
     * @param type of type string
     * @param cost of type double
     * @param car of type Car
     */
    public Transaction(int transID, Calendar c, String person, String type, double cost, Car car){
        transType = type;
        date = c;
        name = person;
        price = cost;
        ID = transID; 
        this.car = car;

    }
    /**
     * Following methods return their own variables for use in other classes
     */
    public Calendar getDate(){
        return date;
    }
    public String getPerson(){
        return name;
    }
    public Car getCar(){
        return car;
    }
    public int getID(){
        return ID;
    }
    public double getPrice(){
        return price;
    }
    public int getMonth(){
        return date.get(Calendar.MONTH);
    }
    /**
     * display() will be used in AccountingSystem to display after transactions are made
     */
    public String display(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        return "ID: " + ID + " " + sdf.format(date.getTime()) + " " + transType + " Sales Person: " + name + " Car: "+car.display();  
    }

}