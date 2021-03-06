import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TollBooth {

	/**
	 * The numVehicles attribute refers the the number of vehicles at a given toll booth.
	 */
	private int numVehicles;
	/**
	 * The boothTotal attribute refers to the total tolls collected by a given toll booth.
	 */
	private double boothTotal;
	/**
	 * The vehicleList attribute is an ArrayList of objects of the class Vehicle.
	 */
	private ArrayList<Vehicle> vehicleList;
	/**
	 * The j attribute is used as an index for several loops.
	 */
	private int j;
	
	// Use these methods to manage loop iteration
	
		/**
		 * The getJ method is a getter for the value of j.
		 * @return This returns the value of j.
		 */
		public int getJ() {
			return this.j;
		}
		
		/**
		 * The setJ method is a setter for the value of j.
		 * @param newJ This is the new value to be used for j.
		 */
		public void setJ(int newJ) {
			this.j = newJ;
		}
		
	// Use these methods to manage the number of vehicles
		
		/**
		 * The getNumVehicles method is a getter for the value of numVehicles.
		 * @return This returns the value of numVehicles.
		 */
		public int getNumVehicles() {
			return this.numVehicles;
		}
		
		/**
		 * The setNumVehicles method is a setter for the value of numVehicles.
		 * @param newNumVehicles This is the new value to be used for numVehicles.
		 */
		public void setNumVehicles(int newNumVehicles) {
			this.numVehicles = newNumVehicles;
		}
		
	// Use these methods to manage the array of vehicles
		
		/**
		 * The TollBooth method is a constructor that creates an ArrayList to be filled with Vehicle objects.
		 */
		public TollBooth() {
			this.vehicleList = new ArrayList<Vehicle>();
		}
		
		/**
		 * The getVehicleList method is a getter for the ArrayList called vehicleList.
		 * @return This returns the ArrayList called vehicleList.
		 */
		public ArrayList<Vehicle> getVehicleList() {
			return this.vehicleList;
		}
		
		/**
		 * The addToVehicleList method adds an object to a new index in the ArrayList called vehicleList.
		 * @param car This is an object of the class Vehicle.
		 */
		public void addToVehicleList(Vehicle car) {
			this.vehicleList.add(car);
		}
		
		/**
		 * The getIndexVehicleList gets the object at the specified index of the ArrayList called vehicleList.
		 * @param index This is the index to be specified from which to pull an object.
		 * @return This returns the object of class Vehicle taken from the specified index.
		 */
		public Vehicle getIndexVehicleList(int index) {
			return this.vehicleList.get(index);
		}
		
		/**
		 * The getSizeVehicleList gets the length of the ArrayList called vehicleList.
		 * @return This returns the length of the ArrayList called vehicleList.
		 */
		public int getSizeVehicleList() {
			return this.vehicleList.size();
		}
		
	// Use these methods to manage the total tolls collected by the toll booth
		
		/**
		 * The getBoothTotal method is a getter for the value of boothTotal.
		 * @return This returns the value of boothTotal.
		 */
		public double getBoothTotal() {
			return this.boothTotal;
		}
		
		/**
		 * The setBoothTotal method is a setter for the value of boothTotal.
		 * @param newBoothTotal This is the new value to be used for boothTotal.
		 */
		public void setBoothTotal(double newTotal) {
			this.boothTotal = newTotal;
		}
		
	// Use the following method to create an object for each vehicle
		
		/**
		 * The countVehicles method takes input from the numvehicles.txt file and uses that input to create Vehicle objects in the ArrayList called vehicleList, then runs methods to calculate the toll for each vehicle object in the ArrayList.
		 * @param station This is an object of the class TollStation
		 * @param car This is an object of the class Vehicle
		 * @param calculate This is an object of the class TollCollection
		 */
		public void countVehicles(TollStation station, Vehicle car, TollCollection calculate) {
			NumberFormat twoDec = new DecimalFormat("#0.00");
			Scanner keyboard = new Scanner(System.in);
			try {
				System.out.println("How many vehicles used booth " + station.getI() + "?");
				this.setNumVehicles(calculate.inputNumVehiclesFile());
				System.out.println(this.getNumVehicles());
			}
			catch(InputMismatchException e){
				System.out.println("Invalid input. Please check the file named 'numvehicles.txt'. For now, enter the number of vehicles manually. The number must be a nonnegative integer.");
				while(!keyboard.hasNextInt() || keyboard.nextInt() < 0) {
					System.out.println("Invalid input, try again.");
					keyboard.next();
				}
				this.setNumVehicles(keyboard.nextInt());
			}
			catch(Exception e){
				System.out.println("An unknown error occurred. Please check the file named 'numvehicles.txt'. For now, enter the number of vehicles manually. The number must be a nonnegative integer.");
				while(!keyboard.hasNextInt() || keyboard.nextInt() < 0) {
					System.out.println("Invalid input, try again.");
					keyboard.next();
				}
				this.setNumVehicles(keyboard.nextInt());
			}
			for (j = 1; j < this.getNumVehicles() + 1; j++) {
				this.addToVehicleList(new Vehicle());
				car.vehicleToll(this, calculate, station);
			}
			
		}
}
