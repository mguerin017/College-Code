import java.util.Scanner;

/* header comment
 * This program computes the area of a circle
 * @author Mark Guerin
 */

public class Circle {

	public static void main(String[] args) {

		final double PI = 3.1415926535897932384626;
		
		//Get radius from user
		double radius = 0;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the radius");
		radius = keyboard.nextDouble();
		
		//compute the area of a circle	
		double area = PI * radius * radius;
		
		//display the area
		System.out.println("The area of the circle is " + area);
		
		//compute the perimeter of a circle
		double perimeter = 2 * PI * radius;
		
		//display the perimeter
		System.out.println("The perimeter of the circle is " + perimeter);
		
		//close the keyboard
		keyboard.close();
	}

}
