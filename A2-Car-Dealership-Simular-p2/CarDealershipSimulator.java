/**
 * @author Nathalie Ng
 * 500921963
 */
import java.util.*;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.File ;
import java.util.Scanner ;

public class CarDealershipSimulator 
{
  public static void main(String[] args)
  {
	  // Create a CarDealership object
	  CarDealership carDealership = new CarDealership();
	  //AccountingSystem accountingSystem = new AccountingSystem();
	  // Then create an (initially empty) array list of type Car
		ArrayList<Car> cars = new ArrayList<Car>();
		//Variables set to command's 
		String Quit = "Q";
		String Buy = "BUY"; 
		String List = "L";
		String Add = "ADD";
		String RET = "RET";
		String SPR = "SPR";
		String FEL = "FEL";
		String FCL = "FCL";
		String FAW = "FAW";
		String FPR = "FPR";
		String SSR = "SSR";
		String SMR = "SMR";
		String SALES = "SALES";
		String SALESTEAM = "SALES TEAM";
		String TOPSP = "SALES TOPSP";
		String MONTH = "SALES ";
		String STATS = "SALES STATS";
		int numBuy = 0; //numBuy is used to store which car is most recently purchased.
		int numMonth =0;
		int transID=-1;
		boolean isCheckingMonth = false;
		double minimumPrice = 0; //minimum price for filtering by price
		double maximumPrice = 0; //maximum price for filtering by price
		int x =0; //x value will determine whether to quit or loop again
		//Car removed = null;
		//Scanner object for commands
		Scanner input = new Scanner(System.in);
 
	
	  try{
		/**
		* Cars added according to cars.txt file
		*/
		File infile = new File("cars.txt");
		Scanner scan = new Scanner(infile);
	
		while(scan.hasNext()){
			String carInputs = scan.nextLine();
			String [] carIn = carInputs.split("\\s+");
			boolean drive;
			//Checks to see if it is AWD or 2WD and will assign it to either false or true and converted to boolean
			if (carIn[6].equals ("2WD")){
				carIn[6] = "false";
			}else{
				carIn[6] = "true";
			}
			drive = Boolean.parseBoolean(carIn[6]);
			if (carIn[3].equals("ELECTRIC_MOTOR")){
				if(carIn[2].equals("SEDAN")){
					cars.add(new ElectricCar(carIn[0],carIn[1],Car.ModelType.SEDAN ,Vehicle.PowerSource.ELECTRIC_MOTOR,Double.parseDouble(carIn[4]),Integer.parseInt(carIn[5]),drive,Double.parseDouble(carIn[7]),carIn[8], Integer.parseInt(carIn[9])));
				}
				else if(carIn[2].equals("MINIVAN")){
					cars.add(new ElectricCar(carIn[0],carIn[1],Car.ModelType.MINIVAN ,Vehicle.PowerSource.ELECTRIC_MOTOR,Double.parseDouble(carIn[4]),Integer.parseInt(carIn[5]),drive,Double.parseDouble(carIn[7]),carIn[8], Integer.parseInt(carIn[9])));
				}
				else if(carIn[2].equals("SPORTS")){
					cars.add(new ElectricCar(carIn[0],carIn[1],Car.ModelType.SPORTS ,Vehicle.PowerSource.ELECTRIC_MOTOR,Double.parseDouble(carIn[4]),Integer.parseInt(carIn[5]),drive,Double.parseDouble(carIn[7]),carIn[8], Integer.parseInt(carIn[9])));
				}
				else if(carIn[2].equals("SUV")){
					cars.add(new ElectricCar(carIn[0],carIn[1],Car.ModelType.SUV ,Vehicle.PowerSource.ELECTRIC_MOTOR,Double.parseDouble(carIn[4]),Integer.parseInt(carIn[5]),drive,Double.parseDouble(carIn[7]),carIn[8], Integer.parseInt(carIn[9])));
				}
			}
			else{
				if(carIn[2].equals("SEDAN")){
					cars.add(new Car(carIn[0],carIn[1],Car.ModelType.SEDAN ,Vehicle.PowerSource.GAS_ENGINE ,Double.parseDouble(carIn[4]),Integer.parseInt(carIn[5]),drive,Double.parseDouble(carIn[7])));
				}
				else if(carIn[2].equals("MINIVAN")){
					cars.add(new Car(carIn[0],carIn[1],Car.ModelType.MINIVAN ,Vehicle.PowerSource.GAS_ENGINE ,Double.parseDouble(carIn[4]),Integer.parseInt(carIn[5]),drive,Double.parseDouble(carIn[7])));
				}
				else if(carIn[2].equals("SPORTS")){
					cars.add(new Car(carIn[0],carIn[1],Car.ModelType.SPORTS ,Vehicle.PowerSource.GAS_ENGINE ,Double.parseDouble(carIn[4]),Integer.parseInt(carIn[5]),drive,Double.parseDouble(carIn[7])));
				}
				else if(carIn[2].equals("SUV")){
					cars.add(new Car(carIn[0],carIn[1],Car.ModelType.SUV ,Vehicle.PowerSource.GAS_ENGINE ,Double.parseDouble(carIn[4]),Integer.parseInt(carIn[5]),drive,Double.parseDouble(carIn[7])));
				}
				}
			}
		}catch(FileNotFoundException e){
			System.out.println("error");
		}
	  	while(x==0){
			String line = input.next(); //line is the next input
			String line2 = input.nextLine(); //line2 is the next input line

			 //this if statement will continue to run if line of next input is equal to BUY or FP, and will create a new scanner
			 //new scanner is meant for the next inputs that we take from line2, to retrieve integers used for BUY and FPR and SALES
			if(line.equalsIgnoreCase(Buy) || line.equalsIgnoreCase(FPR)||line.equalsIgnoreCase("SALES")){
				Scanner commandLine = new Scanner(line2);
				//checks if the line is sales, if it is then it will scan for the second word/number and check if it is a command
				try{
				if(line.equalsIgnoreCase("sales")){
					if(commandLine.hasNext()){
						line2 = commandLine.next();
						if(line2.equalsIgnoreCase("team")){
							System.out.println("Team: " + carDealership.getSalesTeam());
						}
						if(line2.equalsIgnoreCase("topsp")){
							System.out.println(carDealership.getTopSales());
						}
						if(line2.equalsIgnoreCase("stats")){
							try{
							carDealership.stats();
							line2 = "sales stats";
							}catch(Exception e){
								System.out.println("Error: " + e);
							}
						}
						if(line2.length()<=3){
							numMonth = Integer.parseInt(line2);
							if(numMonth>=1 && numMonth<=12){
								carDealership.getMonthly(numMonth);
							}
							else{
								System.out.println("Please input valid month");
							}
						}
					}
					else{
							carDealership.getTrans();
						}
					}
				else if(line.equalsIgnoreCase(Buy)){
					//commandLine will scan the next integer from line2 and convert from string to integer
					if(commandLine.hasNext()){
						try{
						String num1 = commandLine.next();
						numBuy = Integer.parseInt(num1);
						}
						catch(Exception e){
							System.out.println("Try again");
						}
					}
					else{
						//if there is no number entered next to BUY, it will ask to try again instead of exiting from the program.
						System.out.println("Please try again with car# you would like to buy.");
					}
				}
				else{
					if(commandLine.hasNext()){
						//the commandLine scanner is used here to scan the two integers for prices from the entire line inputted through the input scanner
						//converts both string commandLine inputs, into integers for the methods inside the command's if statement
						String number = commandLine.next();
						String number2 = commandLine.next();
						minimumPrice = Double.parseDouble(number);
						maximumPrice = Double.parseDouble(number2);
					}
					else{
						//if there is no number entered next to BUY, it will ask to try again instead of exiting from the program.
						System.out.println("Please try again with price range");
					}
				}
			}catch(Exception e){
				System.out.println("Please input valid command");
			}
			} 
			/**
			 * The following methods called are explained in class CarDealership
			 * After the input line is scanned, the following if statements will check if they are equal to any of the command strings 
			 * If the user inputs something that is not a command option, it will ask to try again, so the only way to exit is "Q"
			 */
		  if(line.equalsIgnoreCase(List)){
			  	
				carDealership.displayInventory();
			}
			else if(line.equalsIgnoreCase(Quit)){
				//x is set to 1, so the while loop will stop and close scanner, exiting from the program completely
				x=1;
			}
			else if(line.equalsIgnoreCase(Buy)){
				try{
					System.out.println(carDealership.buyCar(numBuy));
					transID = carDealership.getID();
					numBuy = 0;
				
				}catch(Exception e){
					System.out.println("Error");
				}
			}
			else if(line.equalsIgnoreCase(RET)){
				if (transID != -1){
					carDealership.returnCar(transID);
					transID = -1;
				}
				
				//removed is set back to null, so if user calls RET again, it will not return the car already returned
			}
			else if(line.equalsIgnoreCase(Add)){
				carDealership.addCars(cars);
			}
			else if(line.equalsIgnoreCase(SPR)){
				carDealership.sortByPrice();
			}
			else if(line.equalsIgnoreCase(SSR)){
				carDealership.sortBySafetyRating();
			}
			else if(line.equalsIgnoreCase(SMR)){
				carDealership.sortByMaxRange();
			}
			else if(line.equalsIgnoreCase(FPR)){
				carDealership.filterByPrice(minimumPrice, maximumPrice);
			}
			else if(line.equalsIgnoreCase(FEL)){
				carDealership.filterByElectric();
			}
			else if(line.equalsIgnoreCase(FAW)){
				carDealership.filterByAWD();
			}
			else if(line.equalsIgnoreCase(FCL)){
				carDealership.FiltersClear();
			}
			/**
			 * The below if statements are still here, even though the corresponding sales command is called before this block of if statements
			 * It is so that the program will still recognize it as a command
			 */
			else if(line.equalsIgnoreCase(STATS)){
				System.out.print("");
			}
			else if(isCheckingMonth){
				System.out.print("");
			}
			else if(line.equalsIgnoreCase(SALESTEAM)){
				System.out.print("");
			}
			else if(line.equalsIgnoreCase(SALES) && !isCheckingMonth){
				System.out.print("");
			}
			else if(line.equalsIgnoreCase(TOPSP)){
				System.out.print("");
			}
			else{
				//if none of the commands are called, it will prompt to try again instead of exiting the program completely. 
				System.out.println("Command not recognized. Please try again.");
			}
	  	}
		input.close();
	}
}