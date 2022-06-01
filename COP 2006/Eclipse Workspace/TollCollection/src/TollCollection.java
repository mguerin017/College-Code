import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/* @author Mark Guerin
 * This program computes toll rates and totals for a set of toll booths.
 */

public class TollCollection {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		NumberFormat twoDec = new DecimalFormat("#0.00");
		System.out.println("Use this program to calculate tolls and totals for your toll booth station.");
		// Initialize variables
		double boothTot = 0;
		double cashTot = 0;
		double cardTot = 0;
		double elecTot = 0;
		double total = 0;
		int vehicleType = 0;
		int axles = 0;
		int payType = 0;
		// Create a loop for the number of booths
		int booths = 0;
		System.out.println("How many toll booths are at the station?");
		booths = keyboard.nextInt();
		for (int i = 1; i < booths+1; i++) {
			// Create a loop for the number of vehicles
			int vehicles = 0;
			System.out.println("How many vehicles passed through booth " + i + "?");
			vehicles = keyboard.nextInt();
			for (int j = 1; j < vehicles+1; j++) {
				// Determine vehicle type
				System.out.println("Is vehicle " + j + " 1) electric, 2) hybrid, or 3) gasoline? (Enter a number)");
				vehicleType = keyboard.nextInt();
				// Determine number of axles
				System.out.println("Does vehicle " + j + " have 1) one axle, 2) two axles, 3) three axles, or 4) more than three axles? (Enter a number)");
				axles = keyboard.nextInt();
				// Determine payment type
				System.out.println("Is vehicle " + j + " paying with 1) cash, 2) card, or 3) an electronic payment system, such as SunPass? (Enter a number)");
				payType = keyboard.nextInt();
				// Calculate the toll for the vehicle
				// Find a more efficient way to code this, if possible
				if (vehicleType == 1 && axles == 1 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $1.00.");
					System.out.println();
					boothTot += 1;
					cashTot += 1;
					total += 1;
				}
				else if (vehicleType == 1 && axles == 1 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $1.50.");
					System.out.println();
					boothTot += 1.5;
					cardTot += 1.5;
					total += 1.5;
				}
				else if (vehicleType == 1 && axles == 1 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $0.50.");
					System.out.println();
					boothTot += 0.5;
					elecTot += 0.5;
					total += 0.5;
				}
				else if (vehicleType == 1 && axles == 2 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $2.00.");
					System.out.println();
					boothTot += 2;
					cashTot += 2;
					total += 2;
				}
				else if (vehicleType == 1 && axles == 2 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $3.00.");
					System.out.println();
					boothTot += 3;
					cardTot += 3;
					total += 3;
				}
				else if (vehicleType == 1 && axles == 2 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $0.50.");
					System.out.println();
					boothTot += 0.5;
					elecTot += 0.5;
					total += 0.5;
				}
				else if (vehicleType == 1 && axles == 3 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $4.00.");
					System.out.println();
					boothTot += 4;
					cashTot += 4;
					total += 4;
				}
				else if (vehicleType == 1 && axles == 3 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $5.50.");
					System.out.println();
					boothTot += 5.5;
					cardTot += 5.5;
					total += 5.5;
				}
				else if (vehicleType == 1 && axles == 3 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $1.00.");
					System.out.println();
					boothTot += 1;
					elecTot += 1;
					total += 1;
				}
				else if (vehicleType == 1 && axles == 4 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $4.00.");
					System.out.println();
					boothTot += 4;
					cashTot += 4;
					total += 4;
				}
				else if (vehicleType == 1 && axles == 4 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $5.50.");
					System.out.println();
					boothTot += 5.5;
					cardTot += 5.5;
					total += 5.5;
				}
				else if (vehicleType == 1 && axles == 4 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $0.50.");
					System.out.println();
					boothTot += 0.5;
					elecTot += 0.5;
					total += 0.5;
				}
				else if (vehicleType == 2 && axles == 1 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $1.50.");
					System.out.println();
					boothTot += 1.5;
					cashTot += 1.5;
					total += 1.5;
				}
				else if (vehicleType == 2 && axles == 1 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $2.00.");
					System.out.println();
					boothTot += 2;
					cardTot += 2;
					total += 2;
				}
				else if (vehicleType == 2 && axles == 1 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $1.00.");
					System.out.println();
					boothTot += 1;
					elecTot += 1;
					total += 1;
				}
				else if (vehicleType == 2 && axles == 2 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $3.00.");
					System.out.println();
					boothTot += 3;
					cashTot += 3;
					total += 3;
				}
				else if (vehicleType == 2 && axles == 2 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $4.00.");
					System.out.println();
					boothTot += 4;
					cardTot += 4;
					total += 4;
				}
				else if (vehicleType == 2 && axles == 2 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $1.50.");
					System.out.println();
					boothTot += 1.5;
					elecTot += 1.5;
					total += 1.5;
				}
				else if (vehicleType == 2 && axles == 3 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $4.00.");
					System.out.println();
					boothTot += 4;
					cashTot += 4;
					total += 4;
				}
				else if (vehicleType == 2 && axles == 3 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $5.50.");
					System.out.println();
					boothTot += 5.5;
					cardTot += 5.5;
					total += 5.5;
				}
				else if (vehicleType == 2 && axles == 3 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $3.00.");
					System.out.println();
					boothTot += 3;
					elecTot += 3;
					total += 3;
				}
				else if (vehicleType == 2 && axles == 4 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $5.00.");
					System.out.println();
					boothTot += 5;
					cashTot += 5;
					total += 5;
				}
				else if (vehicleType == 2 && axles == 4 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $6.50.");
					System.out.println();
					boothTot += 6.5;
					cardTot += 6.5;
					total += 6.5;
				}
				else if (vehicleType == 2 && axles == 4 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $4.50.");
					System.out.println();
					boothTot += 4.5;
					elecTot += 4.5;
					total += 4.5;
				}
				else if (vehicleType == 3 && axles == 1 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $3.00.");
					System.out.println();
					boothTot += 3;
					cashTot += 3;
					total += 3;
				}
				else if (vehicleType == 3 && axles == 1 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $3.50.");
					System.out.println();
					boothTot += 3.5;
					cardTot += 3.5;
					total += 3.5;
				}
				else if (vehicleType == 3 && axles == 1 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $2.50.");
					System.out.println();
					boothTot += 2.5;
					elecTot += 2.5;
					total += 2.5;
				}
				else if (vehicleType == 3 && axles == 2 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $4.00.");
					System.out.println();
					boothTot += 4;
					cashTot += 4;
					total += 4;
				}
				else if (vehicleType == 3 && axles == 2 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $5.00.");
					System.out.println();
					boothTot += 5;
					cardTot += 5;
					total += 5;
				}
				else if (vehicleType == 3 && axles == 2 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $3.50.");
					System.out.println();
					boothTot += 3.5;
					elecTot += 3.5;
					total += 3.5;
				}
				else if (vehicleType == 3 && axles == 3 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $6.00.");
					System.out.println();
					boothTot += 6;
					cashTot += 6;
					total += 6;
				}
				else if (vehicleType == 3 && axles == 3 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $7.50.");
					System.out.println();
					boothTot += 7.5;
					cardTot += 7.5;
					total += 7.5;
				}
				else if (vehicleType == 3 && axles == 3 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $5.00.");
					System.out.println();
					boothTot += 5;
					elecTot += 5;
					total += 5;
				}
				else if (vehicleType == 3 && axles == 4 && payType == 1) {
					System.out.println("The toll collected for this vehicle is $8.00.");
					System.out.println();
					boothTot += 8;
					cashTot += 8;
					total += 8;
				}
				else if (vehicleType == 3 && axles == 4 && payType == 2) {
					System.out.println("The toll collected for this vehicle is $9.50.");
					System.out.println();
					boothTot += 9.5;
					cardTot += 9.5;
					total += 9.5;
				}
				else if (vehicleType == 3 && axles == 4 && payType == 3) {
					System.out.println("The toll collected for this vehicle is $10.00.");
					System.out.println();
					boothTot += 10;
					elecTot += 10;
					total += 10;
				}
				else {
					System.out.println();
					System.out.println("You have made an invalid input. Please start over.");
					keyboard.close();
					return;
				}
			}
			// Output the total tolls collected by the booth.
			System.out.println();
			System.out.println("The total tolls collected by booth " + i + " is $" + twoDec.format(boothTot) + ".");
			System.out.println();
			boothTot = 0;
		}
		// Output the total tolls collected by each payment method and the grand total of all tolls.
		System.out.println();
		System.out.println("The total tolls collected via cash is $" + twoDec.format(cashTot) + ".");
		System.out.println("The total tolls collected via card is $" + twoDec.format(cardTot) + ".");
		System.out.println("The total tolls collected via electronic systems is $" + twoDec.format(elecTot) + ".");
		System.out.println("The grand total of all tolls collected is $" + twoDec.format(total) + ".");
		keyboard.close();
	}

}
