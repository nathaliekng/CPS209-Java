/**
 * @author Nathalie Ng
 * 500921963
 */
import java.util.ArrayList;
import java.util.Scanner;

public class CarDealershipSimulator 
{
  public static void main(String[] args)
  {
	  // Create a CarDealership object
	  CarDealership carDealership = new CarDealership();
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
		int numBuy = 0; //numBuy is used to store which car is most recently purchased.
		double minimumPrice = 0; //minimum price for filtering by price
		double maximumPrice = 0; //maximum price for filtering by price
		int x =0; //x value will determine whether to quit or loop again
		Car removed = null;
		//Scanner object for commands
		Scanner input = new Scanner(System.in);
 
	 /**
		* Cars added according to cars.txt file
	  */
	  
	  cars.add(new Car("Toyota", "blue", Car.ModelType.SEDAN, Vehicle.PowerSource.GAS_ENGINE, 9.5, 500, false, 25000));
	  cars.add(new Car("Honda", "red", Car.ModelType.SPORTS, Vehicle.PowerSource.GAS_ENGINE, 9.2, 450, false, 30000));
	  cars.add(new Car("Kia", "white", Car.ModelType.MINIVAN, Vehicle.PowerSource.GAS_ENGINE, 9.7, 550, false, 20000));
	  cars.add(new Car("BMW", "black", Car.ModelType.SEDAN, Vehicle.PowerSource.GAS_ENGINE, 9.6, 600, true, 55000));
	  cars.add(new ElectricCar("Tesla", "red", Car.ModelType.SEDAN, Vehicle.PowerSource.ELECTRIC_MOTOR, 9.1, 425, true, 85000,"Lithium", 30));
	  cars.add(new Car("Chevy", "red", Car.ModelType.MINIVAN, Vehicle.PowerSource.GAS_ENGINE, 9.25, 475, false, 40000));
	  cars.add(new ElectricCar("ChevyVolt", "green", Car.ModelType.SEDAN, Vehicle.PowerSource.ELECTRIC_MOTOR, 8.9, 375, true, 37000, "Lithium",45));
	  cars.add(new Car("Bentley", "black", Car.ModelType.SEDAN, Vehicle.PowerSource.GAS_ENGINE, 9.8, 575, false, 150000));
	  cars.add(new ElectricCar("NissanLeaf", "green", Car.ModelType.SEDAN, Vehicle.PowerSource.ELECTRIC_MOTOR, 8.8, 325, true, 32000,"Lithium", 55));

	  while(x==0){
			String line = input.next(); //line is the next input
			String line2 = input.nextLine(); //line2 is the next input line 

			 //this if statement will continue to run if line of next input is equal to BUY or FP, and will create a new scanner
			 //new scanner is meant for the next inputs that we take from line2, to retrieve integers used for BUY and FPR
			if(line.equalsIgnoreCase(Buy) || line.equalsIgnoreCase(FPR)){
				Scanner commandLine = new Scanner(line2);
				if(line.equalsIgnoreCase(Buy)){
					//commandLine will scan the next integer from line2 and convert from string to integer
					if(commandLine.hasNext()){
						String num1 = commandLine.next();
						numBuy = Integer.parseInt(num1);
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
			 removed = carDealership.buyCar(numBuy);
			}
			else if(line.equalsIgnoreCase(RET)){
				carDealership.returnCar(removed);
				//removed is set back to null, so if user calls RET again, it will not return the car already returned
				removed = null;
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
			else{
				//if none of the commands are called, it will prompt to try again instead of exiting the program completely. 
				System.out.println("Command not recognized. Please try again.");
			}
	  }
		input.close();
	}
}