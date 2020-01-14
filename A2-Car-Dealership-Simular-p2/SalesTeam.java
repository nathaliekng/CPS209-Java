/**
 * @author Nathalie Ng
 * 500921963
 */
import java.util.*;

class SalesTeam{

    private String people = "";
    public LinkedList<String> list;
    public ArrayList<Integer> sales;

    /**
     * Constructor method SalesTeam() adds names to list, and a starting value of 0 to sales
     * which is an arrayList made to correspond with the names 
     * i.e. list(0) (George) will have sales(0) sales. 
     */
    public SalesTeam(){
        list = new LinkedList<String>();
        sales = new ArrayList<Integer>();
        list.add("George");
        list.add("Paul");
        list.add("Jean");
        list.add("Ringo");
        list.add("Billy");
        list.add("Sheehan");
        sales.add(0); sales.add(0); sales.add(0); sales.add(0); sales.add(0); sales.add(0);

    }

    /**
     * The following method will return one of the six sales people when called 
     * @return a sales person string 
     */ 
    public String getPerson(){
        Random random = new Random();
        int i = random.nextInt(5); 
        sales.set(i, sales.get(i) + 1);
        return list.get(i);
    }

    /**
     * topSales() is used to find the person with the most sales, and how many they have 
     * for loop is used find the max number of sales, and the corresponding person 
     * @return string with top sale person and their number of sales 
     */
    public String topSales(){
        int topVal = 0;
        String topSale = "";
        for(int i=0; i<list.size(); i++){
            if(sales.get(i)>topVal){
                topVal = sales.get(i);
                topSale = list.get(i);
            }
            else if(sales.get(i)==topVal){
                topSale += " " + list.get(i);
            }
        }
        return "Top SP: " + topSale + " " + topVal;
    }

    /**
     * displayAll() is used to display all sales team
     * @return string of people in the team 
     */
    public String displayAll(){
        Iterator<String> iterate = list.iterator();
        while(iterate.hasNext()){
            people=people + iterate.next()+ " ";
        }
        return people;
    }
}