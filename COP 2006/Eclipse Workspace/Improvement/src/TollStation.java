import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TollStation {

	
	/**
	 * The numBooths attribute refers to the number of toll booths at the toll station.
	 */
	private int numBooths;
	/**
	 * The tollBoothList attribute is an ArrayList of objects of the class TollBooth.
	 */
	private ArrayList<TollBooth> tollBoothList;
	/**
	 * The i attribute is used as an index for several loops.
	 */
	private int i;
	
	// Use these methods to manage loop iteration
	
		/**
		 * The getI method is a getter for the value of i.
		 * @return This returns the value of i.
		 */
		public int getI() {
			return this.i;
		}
		
		/**
		 * The setI method is a setter for the value of i.
		 * @param newI This is the new value to be used for i.
		 */
		public void setI(int newI) {
			this.i = newI;
		}
		
	// Use these methods to manage the number of toll booths
		
		/**
		 * The getNumBooths method is a getter for the value of numBooths.
		 * @return This returns the value of numBooths.
		 */
		public int getNumBooths() {
			return this.numBooths;
		}
		
		/**
		 * The setNumBooths method is a setter for the value of numBooths.
		 * @param newNumBooths This is the new value to be used for numBooths.
		 */
		public void setNumBooths(int newNumBooths) {
			this.numBooths = newNumBooths;
		}
		
	// Use these methods to manage the array of toll booths
		
		/**
		 * The TollStation method is a constructor that creates an ArrayList to be filled with TollBooth objects.
		 */
		public TollStation() {
			this.tollBoothList = new ArrayList<TollBooth>();
		}
		
		
		/**
		 * The getTollBoothList method is a getter for the ArrayList called tollBoothList.
		 * @return This returns the ArrayList called tollBoothList.
		 */
		public ArrayList<TollBooth> getTollBoothList() {
			return this.tollBoothList;
		}
		
		/**
		 * The addToTollBoothList method adds an object to a new index in the ArrayList called tollBoothList.
		 * @param booth This is an object of the class TollBooth.
		 */
		public void addToTollBoothList(TollBooth booth) {
			this.tollBoothList.add(booth);
		}
		
		/**
		 * The getIndexTollBoothList gets the object at the specified index of the ArrayList called tollBoothList.
		 * @param index This is the index to be specified from which to pull an object.
		 * @return This returns the object of class TollBooth taken from the specified index.
		 */
		public TollBooth getIndexTollBoothList(int index) {
			return this.tollBoothList.get(index);
		}
		
		/**
		 * The getSizeTollBoothList gets the length of the ArrayList called tollBoothList.
		 * @return This returns the length of the ArrayList called tollBoothList.
		 */
		public int getSizeTollBoothList() {
			return this.tollBoothList.size();
		}
		
	// Use the following method to create an object for each toll booth
		
		/**
		 * The countBooths method takes input from the booths.txt file and uses that input to create TollBooth objects in the ArrayList called tollBoothList, then runs methods to count the number of vehicles at each booth and calculate the tolls collected by each booth, then outputs the toll amounts to the console and to the output file.
		 * @param calculate This is an object of the class TollCollection.
		 * @param station This is an object of the class TollStation.
		 * @param booth This is an object of the class TollBooth.
		 * @param car this is an object of the class Vehicle.
		 */
		public void countBooths(TollCollection calculate, TollStation station, TollBooth booth, Vehicle car) {
			NumberFormat twoDec = new DecimalFormat("#0.00");
			Scanner keyboard = new Scanner(System.in);
			try {
				System.out.println("How many toll booths were used at the station?");
				station.setNumBooths(calculate.inputBoothsFile());
				System.out.println(station.getNumBooths());
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid input. Please check the file named 'booths.txt'. For now, enter the number of booths manually. The number must be a nonnegative integer.");
				while(!keyboard.hasNextInt() || keyboard.nextInt() < 0) {
					System.out.println("Invalid input, try again.");
					keyboard.next();
				}
				station.setNumBooths(keyboard.nextInt());
			}
			catch(Exception e){
				System.out.println("An unknown error occurred. Please check the file named 'booths.txt'. For now, enter the number of booths manually. The number must be a nonnegative integer.");
				while(!keyboard.hasNextInt() || keyboard.nextInt() < 0) {
					System.out.println("Invalid input, try again.");
					keyboard.next();
				}
				station.setNumBooths(keyboard.nextInt());
			}
			for (station.setI(1); station.getI() < station.getNumBooths() + 1; station.setI(station.getI()+1)) {
				station.addToTollBoothList(new TollBooth());
				booth.countVehicles(station, car, calculate);
				calculate.setGrandTotal(calculate.getGrandTotal() + booth.getBoothTotal());
				System.out.println("The total tolls collected by booth " + station.getI() + " is $" + twoDec.format(booth.getBoothTotal()) + ".");
				calculate.recordToTollsFile("The total tolls collected by booth " + station.getI() + " is $" + twoDec.format(booth.getBoothTotal()) + ".\n");
				booth.setBoothTotal(0);
			}
		}

}
