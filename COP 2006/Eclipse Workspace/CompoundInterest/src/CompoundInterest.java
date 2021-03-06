import java.util.Scanner;
import java.lang.Math;

public class CompoundInterest {

	public static void main(String[] args) {
			
		//Get initial balance
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your initial balance:");
		double initBalance = keyboard.nextDouble();
		//Get interest rate
		System.out.println("Enter your interest rate:");
		double interestRate = keyboard.nextDouble();
		//Get time elapsed
		System.out.println("How many years of interest would you like to calculate?");
		double time = keyboard.nextDouble();
		keyboard.close();
		//Calculate and display compound interest
		double compoundInterest = initBalance * Math.pow((1 + (interestRate / 100) / 12), (12 * time)) - initBalance;
		System.out.println("Your compound interest is "+ compoundInterest);
	}

}
