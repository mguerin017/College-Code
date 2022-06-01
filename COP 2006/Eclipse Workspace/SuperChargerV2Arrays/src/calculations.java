import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class calculations {

	private static double cap;
	private static double percent;
	private static int complimentary;
	// The supercharger charges at a rate of 60 kW on average and costs $0.28 per kWh
	private static final double CHARGERATE = 60;
	private static final double COSTPERKWH = 0.28;
	
	public double getCap() {
		return this.cap;
	}
	public void setCap(double newCap) {
		this.cap = newCap;
	}
	
	public double getPercent() {
		return this.percent;
	}
	public void setPercent(double newPercent) {
		this.percent = newPercent;
	}
	
	public int getComplimentary() {
		return this.complimentary;
	}
	public void setComplimentary(int newComplimentary) {
		this.complimentary = newComplimentary;
	}
	
	public void calculateTotals() {
		
		totals total = new totals();
		
		NumberFormat twoDec = new DecimalFormat("#0.00");
		NumberFormat noDec = new DecimalFormat("#");
		
		if (getComplimentary() == 1) {
			
			
			total.setChargeTime(((getCap() * (getPercent()/100)) / (CHARGERATE)));
			total.setChargeCost((((getCap() * (getPercent()/100)) * COSTPERKWH)));
			total.addToStallTimeList(total.getChargeTime());
			total.addToTotalComplimentaryList(total.getChargeCost());
			total.addToTimeComplimentaryList(total.getChargeTime());
			total.addToTimeList(total.getChargeTime());
			
			System.out.println("The owner of this vehicle was not charged.");
			System.out.println("It took " + noDec.format(Math.floor(total.getChargeTime())) + " hours and " + noDec.format(Math.floor(((total.getChargeTime() - Math.floor(total.getChargeTime())) * 60))) + " minutes to charge this vehicle.");
			System.out.println();
		}
		else {
			total.setChargeTime(((getCap() * (getPercent()/100)) / (CHARGERATE)));
			total.setChargeCost((((getCap() * (getPercent()/100)) * COSTPERKWH)));
			total.addToStallTotalList(total.getChargeCost());
			total.addToStallTimeList(total.getChargeTime());
			total.addToTotalList(total.getChargeCost());
			total.addToTimeList(total.getChargeTime());
			
			System.out.println("The owner of this vehicle was charged $" + twoDec.format(total.getChargeCost()) + ".");
			System.out.println("It took " + noDec.format(Math.floor(total.getChargeTime())) + " hours and " + noDec.format(Math.floor(((total.getChargeTime() - Math.floor(total.getChargeTime())) * 60))) + " minutes to charge this vehicle.");
			System.out.println();
		}
	}
}
