import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Vehicle {

	/**
	 * The axles attribute refers to the number of axles on a given vehicle.
	 */
	private int axle;
	/**
	 * The payType attribute refers to the payment type used by the driver of a given vehicle.
	 */
	private int payType;
	/**
	 * The vehicleType attribute refers to the type of a given vehicle, be it electric, hybrid, or gasoline.
	 */
	private int vehicleType;
	/**
	 * The vehicleTotal attribute refers to the total toll collected for a given vehicle.
	 */
	private double vehicleTotal;
	
	/**
	 * The getAxle method is a getter for the value of axle.
	 * @return This returns the value of axle.
	 */
	public int getAxle() {
		return this.axle;
	}
	
	/**
	 * The setAxle method is a setter for the value of axle.
	 * @param newAxle This is the new value to be used for axle.
	 */
	public void setAxle(int newAxle) {
		this.axle = newAxle;
	}
	
	/**
	 * The getPayType method is a getter for the value of payType.
	 * @return This returns the value of payType.
	 */
	public int getPayType() {
		return this.payType;
	}
	
	/**
	 * The setPayType method is a setter for the value of payType.
	 * @param newPayType This is the new value to be used for payType.
	 */
	public void setPayType(int newPayType) {
		this.payType = newPayType;
	}
	
	/**
	 * The getVehicleType method is a getter for the value of vehicleType.
	 * @return This returns the value of vehicleType.
	 */
	public int getVehicleType() {
		return this.vehicleType;
	}
	
	/**
	 * The setVehicleType method is a setter for the value of vehicleType.
	 * @param newVehicleType This is the new value to be used for vehicleType.
	 */
	public void setVehicleType(int newVehicleType) {
		this.vehicleType = newVehicleType;
	}
	
	/**
	 * The getVehicleTotal method is a getter for the value of vehicleTotal.
	 * @return This returns the value of vehicleTotal.
	 */
	public double getVehicleTotal() {
		return this.vehicleTotal;
	}
	
	/**
	 * The setVehicleTotal method is a setter for the value of vehicleTotal.
	 * @param newVehicleTotal This is the new value to be used for vehicleTotal.
	 */
	public void setVehicleTotal(double newVehicleTotal) {
		this.vehicleTotal = newVehicleTotal;
	}
	
	/**
	 * The vehicleToll method calculates the toll for a given vehicle and adds any necessary totals to their respective attributes. It also writes the toll of the individual vehicle to the output file.
	 * @param booth This is an object of the class TollBooth.
	 * @param calculate This is an object of the class TollCollection.
	 * @param station This is an object of the class TollStation.
	 */
	public void vehicleToll(TollBooth booth, TollCollection calculate, TollStation station) {
		Scanner keyboard = new Scanner(System.in);
		NumberFormat twoDec = new DecimalFormat("#0.00");
		// Determine vehicle type
		try {
			System.out.println("Is vehicle " + booth.getJ() + " 1) electric, 2) hybrid, or 3) gasoline? (Enter a number)");
			vehicleType = calculate.inputVehicleSpecsFile();
			System.out.println(this.getVehicleType());
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid input. Please check the file named 'vehiclespecs.txt'. For now, enter the number manually.");
			while(!keyboard.hasNextInt() || (keyboard.nextInt() != 1 && keyboard.nextInt() != 2 && keyboard.nextInt() != 3)) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			vehicleType = keyboard.nextInt();
		}
		catch(Exception e) {
			System.out.println("An unknown error occurred. Please check the file named 'vehiclespecs.txt'. For now, enter the number manually.");
			while(!keyboard.hasNextInt() || (keyboard.nextInt() != 1 && keyboard.nextInt() != 2 && keyboard.nextInt() != 3)) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			vehicleType = keyboard.nextInt();
		}
		// Determine number of axles
		try {
			System.out.println("How many axles does vehicle " + booth.getJ() + " have? (Enter a number)");
			axle = calculate.inputVehicleSpecsFile();
			System.out.println(this.getAxle());
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid input. Please check the file named 'vehiclespecs.txt'. For now, enter the number manually.");
			while(!keyboard.hasNextInt() || keyboard.nextInt() < 1) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			axle = keyboard.nextInt();
		}
		catch(Exception e) {
			System.out.println("An unknown error occurred. Please check the file named 'vehiclespecs.txt'. For now, enter the number manually.");
			while(!keyboard.hasNextInt() || keyboard.nextInt() < 1) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			axle = keyboard.nextInt();
		}
		// Determine payment type
		try {
			System.out.println("Is vehicle " + booth.getJ() + " paying with 1) cash, 2) card, or 3) an electronic payment system, such as SunPass? (Enter a number)");
			payType = calculate.inputVehicleSpecsFile();
			System.out.println(this.getPayType());
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid input. Please check the file named 'vehiclespecs.txt'. For now, enter the number manually.");
			while(!keyboard.hasNextInt() || (keyboard.nextInt() != 1 && keyboard.nextInt() != 2 && keyboard.nextInt() != 3)) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			payType = keyboard.nextInt();
		}
		catch(Exception e) {
			System.out.println("An unknown error occurred. Please check the file named 'vehiclespecs.txt'. For now, enter the number manually.");
			while(!keyboard.hasNextInt() || (keyboard.nextInt() != 1 && keyboard.nextInt() != 2 && keyboard.nextInt() != 3)) {
				System.out.println("Invalid input, try again.");
				keyboard.next();
			}
			payType = keyboard.nextInt();
		}
		System.out.println();
		// Calculate the toll for the vehicle
		vehicleTotal = calculate.getBaseRate();
		calculate.axles(this);
		calculate.vehicleTypes(this);
		calculate.payTypes(this);
		booth.setBoothTotal(booth.getBoothTotal() + vehicleTotal);
		System.out.println("The toll collected for vehicle " + booth.getJ() + " at booth " + station.getI() + " was $" + twoDec.format(vehicleTotal) + ".");
		calculate.recordToTollsFile("The toll collected for vehicle " + booth.getJ() + " at booth " + station.getI() + " was $" + twoDec.format(vehicleTotal) + ".\n");
	}
}
