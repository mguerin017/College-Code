import java.util.Scanner;

public class ClassStanding {

	public static void main(String[] args) {
		
		// Get credit hours from user
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your credit hours:");
		int hours = keyboard.nextInt();
		keyboard.close();
		
		//Determine class standing
		String standing = "";
		if (hours > 120) {
			standing = "excess";
		}
		else if (hours >= 90) {
			standing = "senior";
		}
		else if (hours >= 60) {
			standing = "junior";
		}
		else if (hours >= 30) {
			standing = "sophomore";
		}
		else if (hours >= 0) {
			standing = "freshman";
		}
		else {
			standing = "invalid";
		}
		
		// Output standing to user
		System.out.println("Your class standing is " + standing);
	
	}
}
