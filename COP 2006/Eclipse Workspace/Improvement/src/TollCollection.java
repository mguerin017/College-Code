import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * <h1>Toll Collection</h1>
 * The TollCollection program accepts input from several files to calculate the tolls collected by a toll booth.
 * It will output the results to the console as well as to a file specified by the user in the command line.
 * 
 * @author Mark Guerin
 *
 */

public class TollCollection {
	/**
	 * The BASERATE attribute is the base toll rate for each car passing through, before any modifiers are added based on the type of car, axles, pay type, etc.
	 */
	private final double BASERATE = 2.00;
	/**
	 * The ELECTRIC attribute simply specifies a number to be associated with the electric car type.
	 */
	private final int ELECTRIC = 1;
	/**
	 * The HYBRID attribute simply specifies a number to be associated with the hybrid car type.
	 */
	private final int HYBRID = 2;
	/**
	 * The GASOLINE attribute simply specifies a number to be associated with the gasoline car type.
	 */
	private final int GASOLINE = 3;
	/**
	 * The CASH attribute simply specifies a number to be associated with the cash payment type.
	 */
	private final int CASH = 1;
	/**
	 * The CARD attribute simply specifies a number to be associated with the card payment type.
	 */
	private final int CARD = 2;
	/**
	 * The ELECTRONICSYSTEM attribute simply specifies a number to be associated with the electronic system payment type.
	 */
	private final int ELECTRONICSYSTEM = 3;
	/**
	 * The cashTotal attribute is the total tolls collected by the cash payment method.
	 */
	private double cashTotal;
	/**
	 * The cardTotal attribute is the total tolls collected by the card payment method.
	 */
	private double cardTotal;
	/**
	 * The elecTotal attribute is the total tolls collected by the electronic systems payment method.
	 */
	private double elecTotal;
	/**
	 * The grandTotal attribute is the total tolls collected by the toll station.
	 */
	private double grandTotal;
	/**
	 * The tolls attribute is used to write to the output file.
	 */
	private Formatter tolls;
	/**
	 * The boothNum attribute reads from the booths.txt file.
	 */
	private Scanner boothNum;
	/**
	 * The vehicleNum attribute reads from the numvehicles.txt file.
	 */
	private Scanner vehicleNum;
	/**
	 * The vehicleSpec attribute reads from the vehiclespecs.txt file.
	 */
	private Scanner vehicleSpec;

// The following methods are used for the file I/O stream
	
	/**
	 * The openTollsFile method allows the program to access or create an output file.
	 * @param fileName This is the name of the file to be used for output. In the current version of the program, this file name is entered as a command line argument.
	 */
	public void openTollsFile(String fileName) {
		try {
			tolls = new Formatter(fileName);
		}
		catch(Exception e) {
			System.out.println("There was a problem accessing or creating the output file. The program will not output your data to a file if this is the case. Please specify the name of the .txt file you want to write to in the command line before executing the program.");
			System.exit(1);
		}
	}
	
	/**
	 * The recordToTollsFile method writes data to the output file.
	 * @param data This is the string of text to be written to the output file on a new line.
	 */
	public void recordToTollsFile(String data) {
		tolls.format("%s", data);
	}
	/**
	 * The closeTollsFile method ends the program's access to the output file and saves/overwrites any changes to that file.
	 */
	public void closeTollsFile() {
		tolls.close();
	}
	
	/**
	 * The openBoothsFile method allows the program to begin reading from the booths.txt file.
	 */
	public void openBoothsFile() {
		try {
			boothNum = new Scanner(new File("booths.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("There was a problem accessing the file 'booths.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(NullPointerException e) {
			System.out.println("There was a problem accessing the file 'booths.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("There was an unknown error when accessing the file 'booths.txt'.");
			System.exit(1);
		}
	}
	
//	public void readBoothsFile() {
//			String data = boothNum.nextLine();
//			System.out.println(data);
//	}
	
	/**
	 * The inputBoothsFile method receives data from the booths.txt file to be used as input for the program.
	 * @return This returns the integer value parsed from the booths.txt file.
	 */
	public int inputBoothsFile() {
		String data = boothNum.nextLine();
		return Integer.parseInt(data);
	}
	
	/**
	 * The closeBoothsFile method ends the program's access to the booths.txt file.
	 */
	public void closeBoothsFile() {
		try {
			boothNum.close();
		}
		catch(NullPointerException e) {
			System.out.println("There was a problem accessing the file 'booths.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("There was an unknown error when accessing the file 'booths.txt'.");
			System.exit(1);
		}
	}
	
	/**
	 * The openNumVehiclesFile method allows the program to begin reading from the numvehicles.txt file.
	 */
	public void openNumVehiclesFile() {
		try {
			vehicleNum = new Scanner(new File("numvehicles.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("There was a problem accessing the file 'numvehicles.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(NullPointerException e) {
			System.out.println("There was a problem accessing the file 'numvehicles.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("There was an unknown error when accessing the file 'numvehicles.txt'.");
			System.exit(1);
		}
	}
	
//	public void readNumVehiclesFile() {
//			String data = vehicleNum.nextLine();
//			System.out.println(data);
//	}
	
	/**
	 * The inputNumVehiclesFile method receives data from the numvehicles.txt file to be used as input for the program.
	 * @return This returns the integer value parsed from the numvehicles.txt file.
	 */
	public int inputNumVehiclesFile() {
		String data = vehicleNum.nextLine();
		return Integer.parseInt(data);
	}
	
	/**
	 * The closeNumVehiclesFile method ends the program's access to the numvehicles.txt file.
	 */
	public void closeNumVehiclesFile() {
		try {
			vehicleNum.close();
		}
		catch(NullPointerException e) {
			System.out.println("There was a problem accessing the file 'numvehicles.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("There was an unknown error when accessing the file 'numvehicles.txt'.");
			System.exit(1);
		}
	}
	
	/**
	 * The openVehicleSpecsFile method allows the program to begin reading from the vehiclespecs.txt file.
	 */
	public void openVehicleSpecsFile() {
		try {
			vehicleSpec = new Scanner(new File("vehiclespecs.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("There was a problem accessing the file 'vehiclespecs.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(NullPointerException e) {
			System.out.println("There was a problem accessing the file 'vehiclespecs.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("There was an unknown error when accessing the file 'vehiclespecs.txt'.");
			System.exit(1);
		}
	}
	
//	public void readVehicleSpecsFile() {
//			String data = vehicleSpec.nextLine();
//			System.out.println(data);
//	}
	
	/**
	 * The inputVehicleSpecsFile method receives data from the vehiclespecs.txt file to be used as input for the program.
	 * @return This returns the integer value parsed from the vehiclespecs.txt file.
	 */
	public int inputVehicleSpecsFile() {
		String data = vehicleSpec.nextLine();
		return Integer.parseInt(data);
	}
	
	/**
	 * The closeVehicleSpecsFile method ends the program's access to the vehiclespecs.txt file.
	 */
	public void closeVehicleSpecsFile() {
		try {
		vehicleSpec.close();
		}
		catch(NullPointerException e) {
			System.out.println("There was a problem accessing the file 'vehiclespecs.txt'. The file does not exist or is in the wrong directory.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("There was an unknown error when accessing the file 'vehiclespecs.txt'.");
			System.exit(1);
		}
	}

// The following methods are used for the calculation of the toll for each vehicle, as well as keeping totals
	
	/**
	 * The getBaseRate method is a getter for the value of BASERATE.
	 * @return This returns the value of BASERATE.
	 */
	public double getBaseRate() {
		return this.BASERATE;
	}
	
	/**
	 * The getGrandTotal method is a getter for the value of grandTotal.
	 * @return This returns the value of grandTotal.
	 */
	public double getGrandTotal() {
		return this.grandTotal;
	}
	
	/**
	 * The setGrandTotal method is a setter for the value of grandTotal.
	 * @param newGrandTotal This is the new value to be used for grandTotal.
	 */
	public void setGrandTotal(double newGrandTotal) {
		this.grandTotal = newGrandTotal;
	}
	
	/**
	 * The axles method calculates the change in the toll for a vehicle based on the number of axles it has.
	 * @param car This is an object of the Vehicle class.
	 */
	public void axles(Vehicle car) {
		Scanner keyboard = new Scanner(System.in);
		if (car.getAxle() <= 3 && car.getAxle() > 0) {
			car.setVehicleTotal(car.getVehicleTotal() + car.getAxle() - 1);
		}
		else if (car.getAxle() > 3) {
			car.setVehicleTotal(car.getVehicleTotal() + 2 + (car.getAxle() - 3) * 5);
		}
		else {
			System.out.println();
			System.out.println("You have made an invalid input. The number of axles must be at least 1. Please enter the number of axles manually.");
			while(!keyboard.hasNextInt()) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			car.setAxle(keyboard.nextInt());
			this.axles(car);
		}
	}
	
	/**
	 * The vehicleTypes method calculates the change in the toll for a vehicle based on the type of vehicle it is.
	 * @param car This is an object of the Vehicle class.
	 */
	public void vehicleTypes(Vehicle car) {
		Scanner keyboard = new Scanner(System.in);
		if (car.getVehicleType() == ELECTRIC) {
			car.setVehicleTotal(car.getVehicleTotal() * 0.5);
		}
		else if (car.getVehicleType() == HYBRID) {
			car.setVehicleTotal(car.getVehicleTotal() * 0.75);
		}
		else if (car.getVehicleType() == GASOLINE) {
			car.setVehicleTotal(car.getVehicleTotal() * 1);
		}
		else {
			System.out.println();
			System.out.println("You have made an invalid input. The vehicle type must be a number from 1 to 3. Please enter the number manually. 1) electric, 2) hybrid, 3) gasoline");
			while(!keyboard.hasNextInt()) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			car.setVehicleType(keyboard.nextInt());
			this.vehicleTypes(car);
		}
	}
	
	/**
	 * The payTypes method calculates the change in the toll for a vehicle based on the type of payment the driver used.
	 * @param car This is an object of the Vehicle class.
	 */
	public void payTypes(Vehicle car) {
		Scanner keyboard = new Scanner(System.in);
		if (car.getPayType() == CASH) {
			car.setVehicleTotal(car.getVehicleTotal() * 1);
			this.cashTotal += car.getVehicleTotal();
		}
		else if (car.getPayType() == CARD) {
			car.setVehicleTotal(car.getVehicleTotal() * 1.15);
			this.cardTotal += car.getVehicleTotal();
		}
		else if (car.getPayType() == ELECTRONICSYSTEM) {
			car.setVehicleTotal(car.getVehicleTotal() * 0.8);
			this.elecTotal += car.getVehicleTotal();
		}
		else {
			System.out.println();
			System.out.println("You have made an invalid input. The payment type must be a number from 1 to 3. Please enter the number manually. 1) cash, 2) card, 3) electronic payment system");
			while(!keyboard.hasNextInt()) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			car.setPayType(keyboard.nextInt());
			this.payTypes(car);
		}
	}

// Main method begins here
	
	
	/**
	 * The main method begins the execution of the program and writes certain totals to the output file.
	 * @param args This is used for the name of the output file.
	 */
	public static void main(String[] args) {
		TollCollection calculate = new TollCollection();
		TollStation station = new TollStation();
		TollBooth booth = new TollBooth();
		Vehicle car = new Vehicle();
		NumberFormat twoDec = new DecimalFormat("#0.00");
		calculate.openTollsFile(args[0]);
		calculate.openBoothsFile();
		calculate.openNumVehiclesFile();
		calculate.openVehicleSpecsFile();
// The following three lines are used to skip the first line in each input file, which contains instructions for the user
		calculate.boothNum.nextLine();
		calculate.vehicleNum.nextLine();
		calculate.vehicleSpec.nextLine();
		calculate.recordToTollsFile("\n");
		station.countBooths(calculate, station, booth, car);
		System.out.println("The total collected via cash is $" + twoDec.format(calculate.cashTotal) + ".");
		calculate.recordToTollsFile("The total collected via cash is $" + twoDec.format(calculate.cashTotal) + ".\n");
		System.out.println("The total collected via card is $" + twoDec.format(calculate.cardTotal) + ".");
		calculate.recordToTollsFile("The total collected via card is $" + twoDec.format(calculate.cardTotal) + ".\n");
		System.out.println("The total collected via electronic systems is $" + twoDec.format(calculate.elecTotal) + ".");
		calculate.recordToTollsFile("The total collected via electronic systems is $" + twoDec.format(calculate.elecTotal) + ".\n");
		System.out.println("The grand total collected by the station is $" + twoDec.format(calculate.grandTotal) + ".");
		calculate.recordToTollsFile("The grand total collected by the station is $" + twoDec.format(calculate.grandTotal) + ".\n");
		calculate.closeTollsFile();
		calculate.closeBoothsFile();
		calculate.closeNumVehiclesFile();
		calculate.closeVehicleSpecsFile();
	}

}
