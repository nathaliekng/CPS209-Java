/**
 * @author Nathalie Ng
 * 500921963
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CarDealership{
    
    /**
     * All instance variables created here 
     */
    private ArrayList<Car> cars;
    private double minPrice;
    private double maxPrice;
    private boolean filterPrice;
    private boolean filterAWD;
    private boolean filterElectric;
    protected Car removed;

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
     * buyCar() will remove the car object from the arraylist at given index and return a reference to it
     * also checks if the index is valid, and if not will return null
     * @param index 
     * @return removed if the index is a valid index number
     * @return null if the index is outside of the range of the size of the arraylist
     */
    public Car buyCar(int index){
        Car removed;
        if(index>=0 && index<cars.size()){
            removed = cars.get(index);
            cars.remove(index);
            return removed;
        }
        else{
            return null;
        }
    }

    /**
     *  returnCar() takes reference to Car object and adds it back to arraylist as long as the reference is not null
     * @param car 
     */
    public void returnCar(Car car){
        if(car!=null){        
            cars.add(car);
        }

    }

    /**
     * Prints information about cars in arraylist based on set of filter values 
     * Also adds index number of car so user can input which car number they would like to buy in main function
     */
    public void displayInventory(){
       for(int i=0; i<cars.size(); i++){
            if(filterElectric && !filterAWD && !filterPrice){
                if(cars.get(i).power.toString().equals("ELECTRIC_MOTOR")){
                    System.out.print(i + " ");
                    System.out.println(cars.get(i).display());
                }
            }
            if(filterElectric && filterAWD && filterPrice){
                if(cars.get(i).power.toString().equals("ELECTRIC_MOTOR") && cars.get(i).AWD == true && cars.get(i).price>=minPrice && cars.get(i).price <=maxPrice){
                    System.out.print(i + " ");
                    System.out.println(cars.get(i).display());
            }
            }
            else if(filterElectric && filterAWD && !filterPrice){
                if(cars.get(i).power.toString().equals("ELECTRIC_MOTOR") && cars.get(i).AWD == true){
                    System.out.print(i + " ");
                    System.out.println(cars.get(i).display());
                }
            }
            else if(filterElectric && !filterAWD && filterPrice){
                if(cars.get(i).power.toString().equals("ELECTRIC_MOTOR") && cars.get(i).price>=minPrice && cars.get(i).price <=maxPrice){
                    System.out.print(i + " ");
                    System.out.println(cars.get(i).display());
                }
            }
            else if(!filterElectric && !filterAWD && !filterPrice){
                    System.out.print(i + " ");
                    System.out.println(cars.get(i).display());
            }  
            else if(!filterElectric && !filterAWD && filterPrice){
                if(cars.get(i).price>=minPrice && cars.get(i).price <=maxPrice){
                    System.out.print(i + " ");
                    System.out.println(cars.get(i).display());
                }
            }
            else if(!filterElectric && filterAWD && !filterPrice){
                if(cars.get(i).AWD == true){
                    System.out.print(i + " ");
                    System.out.println(cars.get(i).display());
                }
            }
            else if(!filterElectric && filterAWD && filterPrice){
                if(cars.get(i).price>=minPrice && cars.get(i).price <=maxPrice && cars.get(i).AWD == true){
                    System.out.print(i + " ");
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