import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/* @author Mark Guerin
 * This program computes the charging time, charging speed, and cost of charging an electric vehicle
 */

public class EVCharging {

	public static void main(String[] args) {
		
		//Tell the user the purpose of the program
		System.out.println("This program will calculate the charging time, charging speed, and cost to charge your electric vehicle.");
		try {
		Thread.sleep(2500);
		}
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		// Gets the battery capacity, mileage, power outlet voltage and current, and percentage of charge from the user
		double cap = 0;
		System.out.println("Enter the battery capacity of your vehicle in kWh.");
		Scanner keyboard = new Scanner(System.in);
		cap = keyboard.nextDouble();
		
		double mile = 0;
		System.out.println("Enter the mileage of your vehicle in kWh/100 mi.");
		mile = keyboard.nextDouble();
		
		double voltage = 0;
		System.out.println("Enter the output voltage of yor power socket in volts.");
		voltage = keyboard.nextDouble();
		
		double current = 0;
		System.out.println("Enter the output current of your power socket in amperes.");
		current = keyboard.nextDouble();
		
		double percent = 0;
		System.out.println("What percentage of your vehicle's battery do you want to charge?");
		percent = keyboard.nextDouble();
		keyboard.close();
		
		//Calculates the charging time, charging speed, and cost of charge
		double chargeTime = ((cap * (percent/100)) / ((voltage * current * 0.9)/1000));
		double chargeSpeed = (((voltage * current * 0.9)/1000) * 100 / mile);
		double chargeCost = (((cap * (percent/100)) * 0.104) / 0.9);
		
		//Displays the information to the user
		NumberFormat twoDec = new DecimalFormat("#0.00");
		NumberFormat noDec = new DecimalFormat("#");
		System.out.println("It will take " + noDec.format(Math.floor(chargeTime)) + " hours and " + noDec.format(Math.floor(((chargeTime - Math.floor(chargeTime)) * 60))) + " minutes to charge your vehicle.");
		System.out.println("You will gain " + twoDec.format(chargeSpeed) + " miles per hour of charge, totaling to " + twoDec.format(chargeSpeed * chargeTime) + " miles gained over the entire charging time.");
		System.out.println("It will cost $" + twoDec.format(chargeCost) + " to charge your vehicle this much in Fort Myers.");
	}

}
