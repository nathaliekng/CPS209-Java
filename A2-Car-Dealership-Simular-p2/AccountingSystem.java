/**
 * @author Nathalie Ng
 * 500921963
 */
import java.util.*;

class AccountingSystem{
    protected ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    protected int transID;
    protected String name;
    protected String transtype;
    protected double salecost;
    protected Calendar date;
    protected double totalCost;
    protected int totalSales;
    protected int totalReturn;
    private Transaction trans;
    public Map<Integer, Integer> highest = new HashMap<Integer, Integer>() ;
    public Map<Integer, ArrayList<Transaction>> monthly = new HashMap<Integer, ArrayList<Transaction>>();
    
    Car car = new Car();

    /**
     * add() method will input values into Transaction, and add it to the arraylist of transactions 
     * It also checks to see if the Map is empty, and if it is, then it will set the value of the corresponding key to 1, 
     * if it is not null, then it will add 1 
     * It also checks if it is type RET or BUY, and if it is RET, then it will subtract from the total sales price and add to total returned
     * If it is BUY, it adds to the total sales price, and adds to total cars bought
     * The new arraylist and if statements followed will check again, if null and make sure to add all into the arrayList instead of overwriting and stores it in map
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salesPrice
     * @param type
     * @param salesPrice 
     * @return trans.display(), which prints the transaction
     */
    public String add(Calendar date, Car car, String salesPerson, String type, double salesPrice ){
        Random rand = new Random();
        name = salesPerson;
        transtype = type;
        salecost = salesPrice;
        transID = rand.nextInt(99)+1;
        this.date = date;
        this.car = car;
        trans = new Transaction(transID, this.date, salesPerson, type, salesPrice, this.car);
        transactions.add(trans);
        if(highest.get((Integer)date.get(Calendar.MONTH)) != null){
             highest.put((Integer)date.get(Calendar.MONTH), highest.get(date.get(Calendar.MONTH) + 1));
        }
        else
        {
            highest.put((Integer)date.get(Calendar.MONTH), 1);
        }
        if(type.equals("RET")){
            totalReturn += 1;
            totalCost -= salecost;
        }
        else if(type.equals("BUY")){
            totalSales +=1;
            totalCost += salecost;
        }
        ArrayList<Transaction> tempListForMonthly = new ArrayList<Transaction>();
        if (monthly.get(date.get(Calendar.MONTH)) == null){
            tempListForMonthly.add(trans);
        }else if (monthly.get(date.get(Calendar.MONTH)) != null){
            tempListForMonthly.addAll(monthly.get(date.get(Calendar.MONTH)));
            tempListForMonthly.add(trans);
        }
        monthly.put(date.get(Calendar.MONTH), tempListForMonthly);

        return trans.display();
    }
    
    /**
     * getCost() will return the total sales price
     * @return totalCost
     */
    public double getCost(){
        return totalCost;
    }

    /**
     * getMonth() will print out all the sales for the given month using the map 
     * @param month of type int
     */
    public void getMonth(int month){
        ArrayList<Transaction> t = new ArrayList<Transaction>();
        if (monthly.get((Integer)month) != null){
            t.addAll(monthly.get((Integer)month));
        }else if (monthly.get((Integer)month) == null){
            System.out.println("No transactions for this month");
        }
        if (!t.isEmpty()){
            for(int x = 0; x<t.size();x++){
                System.out.println(t.get(x).display());
            }
        }
    }

    /**
     * getTransID() will return the transaction ID, used in carDealership to use in main function for return and buy
     * @return transID
     */
    public int getTransID(){
        return transID;
    }

    /**
     * checkBuy() will check if the transaction type is BUY
     * @return boolean true, if it equals
     */
    public boolean checkBuy(){
        return transtype.equals("BUY");
    }

    /**
     * getStats() uses a map to find the highest value, and highest month 
     * It will set maxVal to the highest value, and find month that has that value in the for loop
     * followed by a block of if satements to set the actual month to display instead of numbers 
     */
    public void getStats(){
        int highestMonth = 0;
        int maxVal = -1;
        double average = totalCost/12;
        if (highest.values().isEmpty()){
            highestMonth = -1;
        }else{
            maxVal = Collections.max(highest.values());
        }
        String highestMonthLine = "";
        for (Integer x : highest.keySet()){
            if (highest.get(x) == maxVal){
                highestMonth = x;
                break;
            }
        }
        if(highestMonth == 0){
            highestMonthLine = "January";
        }
        else if(highestMonth == 1){
            highestMonthLine = "February";
        }
        else if(highestMonth == 2){
            highestMonthLine = "March";
        }
        else if(highestMonth == 3){
            highestMonthLine = "April";
        }
        else if(highestMonth == 4){
            highestMonthLine = "May";
        }
        else if(highestMonth == 5){
            highestMonthLine = "June";
        }
        else if(highestMonth == 6){
            highestMonthLine = "July";
        }
        else if(highestMonth == 7){
            highestMonthLine = "August";
        }
        else if(highestMonth == 8){
            highestMonthLine = "September";
        }
        else if(highestMonth == 9){
            highestMonthLine = "October";
        }
        else if(highestMonth == 10){
            highestMonthLine = "November";
        }
        else if(highestMonth == 11){
            highestMonthLine = "December";
        }
        else{
            highestMonthLine = "Not available";
        }

        System.out.println("Total Sales: " + this.totalCost + " Total Sold: " + totalSales + " Avg Sales: " + (int)average + " Total Returned: "+ totalReturn + " Best Month: " + highestMonthLine + ": cars sold - " + maxVal);
    }

    /**
     * getTransactions() will return all the transactions
     * @return transactions
     */
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }

    /**
     * getTransaction() will will get one of the transaction, that corresponds to the ID
     * @return transaction that matches the same ID
     */
    public Transaction getTransaction(int id){
        boolean check = false;
        int x = 0;
        for(int j=0; j<transactions.size(); j++){
            if(transactions.get(j).getID() == id){
                check = true;
                x = j;
            }
        }
        if(!check){
            return null;
        }
        else{
            return transactions.get(x);
        }
    }
    
}