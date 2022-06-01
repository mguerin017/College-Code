import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.ArrayList;

/* @author Mark Guerin
 * This program computes toll rates and totals for a set of toll booths.
 */

public class main {
	// initialize attributes to be used in multiple classes
	static double vehicleTot;
	static int axle;
	static double cashTot;
	static double cardTot;
	static double elecTot;
	static int payType;
	static int vehicleType;
	
	public static void main(String[] args) {
		// call classes
		calculateToll getToll = new calculateToll();
		
		// initialize variables
		Scanner keyboard = new Scanner(System.in);
		NumberFormat twoDec = new DecimalFormat("#0.00");
		final double BASERATE = 2;
		ArrayList<Double> boothTot = new ArrayList<Double>();
		ArrayList<Double> total = new ArrayList<Double>();
		ArrayList<Double> boothSums = new ArrayList<Double>();
		int booths = 0;
		int vehicles = 0;
		double boothSum = 0;
		double sum = 0;
		
		// Create a loop for the number of booths
		System.out.println("How many toll booths are at the station?");
		booths = keyboard.nextInt();
		for (int i = 1; i < booths+1; i++) {
			// Create a loop for the number of vehicles
			vehicles = 0;
			System.out.println("How many vehicles passed through booth " + i + "?");
			vehicles = keyboard.nextInt();
			for (int j = 1; j < vehicles+1; j++) {
				// Determine vehicle type
				System.out.println("Is vehicle " + j + " 1) electric, 2) hybrid, or 3) gasoline? (Enter a number)");
				vehicleType = keyboard.nextInt();
				// Determine number of axles
				System.out.println("How many axles does vehicle " + j + " have? (Enter a number)");
				axle = keyboard.nextInt();
				// Determine payment type
				System.out.println("Is vehicle " + j + " paying with 1) cash, 2) card, or 3) an electronic payment system, such as SunPass? (Enter a number)");
				payType = keyboard.nextInt();
				System.out.println();
				// Calculate the toll for the vehicle
				vehicleTot = BASERATE;
				getToll.axles();
				getToll.vehicleTypes();
				getToll.payTypes();
				boothTot.add(vehicleTot);
				total.add(vehicleTot);
			}
			// Output the total tolls collected by the booth
			for(int j = 1; j - 1 < boothTot.size(); j++) {
				System.out.println("The toll collected for vehicle " + j + " is $" + twoDec.format(boothTot.get(j - 1)) + ".");
			}
			System.out.println();
			boothSum = 0;
			for(int j = 0; j < boothTot.size(); j++) {
				boothSum += boothTot.get(j);
			}
			boothSums.add(boothSum);
			boothTot.clear();
		}
		for(int i = 0; i < total.size(); i++) {
			sum += total.get(i);
		}
		System.out.println();
		for(int i = 1; i - 1 < boothSums.size(); i++) {
			System.out.println("The total tolls collected by booth " + i + " is $" + twoDec.format(boothSums.get(i - 1)) + ".");
		}
		System.out.println();
		System.out.println("The total tolls collected via cash is $" + twoDec.format(cashTot) + ".");
		System.out.println("The total tolls collected via card is $" + twoDec.format(cardTot) + ".");
		System.out.println("The total tolls collected via electronic systems is $" + twoDec.format(elecTot) + ".");
		System.out.println("The grand total of all tolls collected is $" + twoDec.format(sum) + ".");
		keyboard.close();
	}
}
