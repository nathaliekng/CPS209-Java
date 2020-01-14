/**
 * @author Nathalie Ng
 * 500921963
 */
import java.util.*;


public class CarDealership{
    
    /**
     * All instance variables and objects created here 
     */
    private ArrayList<Car> cars;
    private double minPrice;
    private double maxPrice;
    private boolean filterPrice;
    private boolean filterAWD;
    private boolean filterElectric;
    protected Car removed;
    protected Car ref;
    private Calendar calendar ;
    private Transaction returnedCarTrans;

    SalesTeam salesTeam = new SalesTeam();
    AccountingSystem accountingSystem = new AccountingSystem();

    /** 
     * Constructor method to create empty ArrayList of Car objects and initialize other variables 
     */
    public CarDealership(){
        cars = new ArrayList<Car>();
        minPrice = 0;
        maxPrice = 0;
    }

    /**
     * addCars() will add all newCars to the array 
     * @param newCars , a reference to an arraylist of car objects 
     */
    public void addCars(ArrayList<Car> newCars){
        cars.addAll(newCars);
    }

    /**
     * buyCar() will remove the car object from the arraylist at given VIN
     * also checks if the index is valid, and if not will return null
     * This method will throw an exception if the VIN is not valid
     * @param index 
     * @return variables to add() method in accountingSystem, which also displays the transaction
     * @return empty string if the index is outside of the range of the size of the arraylist
     */

    public String buyCar(int VIN) throws Exception{
        boolean check = false;
        for(int i=0; i<cars.size(); i++){
            if(cars.get(i).VIN == VIN){
                check = true;
                Car ref = cars.get(i);
                cars.remove(i);
                String person = salesTeam.getPerson();
                Random rand = new Random();
                int day = rand.nextInt(31);
                int month = rand.nextInt(11);
                calendar = new GregorianCalendar(2019, month, day);
                return accountingSystem.add(calendar, ref, person, "BUY", ref.price);
            }
        }
        if(!check){
            throw new Exception();
        }
        return "";
    }

    /**
     * getID() will get the transaction ID
     * @return transactionID associated with most recent BUY, used for return in main function
     */
    public int getID(){
        return accountingSystem.getTransID();
    }

    /** 
     *  returnCar() takes reference to transactionID and adds the transaction back to arraylist
     * Will also display the return transaction when called
     * @param transaction
     */
    public void returnCar(int transaction){
        returnedCarTrans = accountingSystem.getTransaction(transaction);
        int month = returnedCarTrans.getDate().MONTH;
        int dayOfMonth = returnedCarTrans.getDate().DAY_OF_MONTH;
        int newDay = (int)(Math.random() * 31 - dayOfMonth) + dayOfMonth;
        calendar = new GregorianCalendar(2019, month,newDay );
        cars.add(returnedCarTrans.getCar());
        accountingSystem.add(calendar, returnedCarTrans.getCar(), returnedCarTrans.getPerson(), "RET", returnedCarTrans.getPrice());
        System.out.println(returnedCarTrans.display());
    }

    /**
     * The following methods are used to call the sales methods
     * Displayed either by printing from the salesTeam/AccountingSystem itself, or in main
     */
    public String getSalesTeam(){
        return salesTeam.displayAll();
    }
    public String getTopSales(){
        return salesTeam.topSales();
    }
    public void getTrans(){
        ArrayList<Transaction> all = accountingSystem.getTransactions();
        for(int i=0; i<all.size(); i++){
            System.out.println(all.get(i).display());
        }
    }
    public void stats(){
        accountingSystem.getStats();
    }
    public void getMonthly(int month){
        accountingSystem.getMonth(month);
    }
    
    /**
     * Prints information about cars in arraylist based on set of filter values 
     */
    public void displayInventory(){
       for(int i=0; i<cars.size(); i++){
            if(filterElectric && !filterAWD && !filterPrice){
                if(cars.get(i).power.toString().equals("ELECTRIC_MOTOR")){
                    System.out.println(cars.get(i).display());
                }
            }
            if(filterElectric && filterAWD && filterPrice){
                if(cars.get(i).power.toString().equals("ELECTRIC_MOTOR") && cars.get(i).AWD == true && cars.get(i).price>=minPrice && cars.get(i).price <=maxPrice){
                    System.out.println(cars.get(i).display());
            }
            }
            else if(filterElectric && filterAWD && !filterPrice){
                if(cars.get(i).power.toString().equals("ELECTRIC_MOTOR") && cars.get(i).AWD == true){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(filterElectric && !filterAWD && filterPrice){
                if(cars.get(i).power.toString().equals("ELECTRIC_MOTOR") && cars.get(i).price>=minPrice && cars.get(i).price <=maxPrice){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(!filterElectric && !filterAWD && !filterPrice){
                    System.out.println(cars.get(i).display());
            }  
            else if(!filterElectric && !filterAWD && filterPrice){
                if(cars.get(i).price>=minPrice && cars.get(i).price <=maxPrice){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(!filterElectric && filterAWD && !filterPrice){
                if(cars.get(i).AWD == true){
                    System.out.println(cars.get(i).display());
                }
            }
            else if(!filterElectric && filterAWD && filterPrice){
                if(cars.get(i).price>=minPrice && cars.get(i).price <=maxPrice && cars.get(i).AWD == true){
                    System.out.println(cars.get(i).display());
                }
            }
        
         }
    } 

    /**
     * Follwoing methods will set Boolean filters for displayInventory()
     * If filterByElectric() is called, it will filter to only show the Electric cars
     * If filterByAWD() is called, it will filter to only show the AWD cars
     * If filterByPrice() is called, it will filter to only show the cars between minimum price and maximum price 
     * If FilterClear() is called, it will clear all filters (set to false) and show all available cars 
     * fitlerByElectric(), filterByAWD() have no parameters 
     * @return true
     * filterByPrice()
     * @param minPrice
     * @param maxPrice 
     * @return true 
     * FiltersClear() 
     * @return false
     */
    public void filterByElectric(){
        filterElectric = true;
    }
    public void filterByAWD(){
        filterAWD = true;
    }
    public void filterByPrice(double minPrice, double maxPrice){
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        filterPrice = true;
    }
    public void FiltersClear(){
        filterPrice = false;
        filterElectric = false;
        filterAWD = false;
    }

    /**
     * Following methods will sort by price, maximum range and safety rating 
     * new classes created in sortByMaxRange() and sortBySafetyRating() to implement comparator for sorting
     * sortByPrice() is sorted to increase in price when called
     * sortBySafetyRating() will sort to decrease in safety ratings when called
     * sortByMaxRange() will sort to decrease in maximum range when called
     */
    public void sortByPrice(){
        Collections.sort(cars);
    }
    public void sortBySafetyRating(){
        class Rating implements Comparator<Car>{
            public int compare(Car other, Car other2){
                if( other.safetyRating >other2.safetyRating ){
                    return -1;
                }
                else if(other.safetyRating <other2.safetyRating){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
        Collections.sort(cars, new Rating());
    }
    public void sortByMaxRange(){
        class RangeCompare implements Comparator<Car>{
            public int compare(Car other, Car other2){
                if( other.maxRange >other2.maxRange ){
                    return -1;
                }
                else if(other.maxRange<other2.maxRange){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
        Collections.sort(cars, new RangeCompare());
    }
}