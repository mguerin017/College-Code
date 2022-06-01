import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class calculations {

	private static double cap;
	private static double percent;
	private static int complimentary;
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
	
	public void calculateTotals(totals total) {
		
		// totals total = new totals();
		
		NumberFormat twoDec = new DecimalFormat("#0.00");
		NumberFormat noDec = new DecimalFormat("#");
		
		if (getComplimentary() == 1) {
			
			// The supercharger charges at a rate of 60 kW on average and costs $0.28 per kWh
			
			total.setChargeTime(((getCap() * (getPercent()/100)) / (CHARGERATE)));
			total.setChargeCost((((getCap() * (getPercent()/100)) * COSTPERKWH)));
			total.setStallTime(total.getStallTime() + total.getChargeTime());
			total.setTotalComplimentary(total.getTotalComplimentary() + total.getChargeCost());
			total.setTimeComplimentary(total.getTimeComplimentary() + total.getChargeTime());
			total.setTime(total.getTime() + total.getChargeTime());
			
			System.out.println("The owner of this vehicle was not charged.");
			System.out.println("It took " + noDec.format(Math.floor(total.getChargeTime())) + " hours and " + noDec.format(Math.floor(((total.getChargeTime() - Math.floor(total.getChargeTime())) * 60))) + " minutes to charge this vehicle.");
			System.out.println();
		}
		else {
			total.setChargeTime(((getCap() * (getPercent()/100)) / (CHARGERATE)));
			total.setChargeCost((((getCap() * (getPercent()/100)) * COSTPERKWH)));
			total.setStallTotal(total.getStallTotal() + total.getChargeCost());
			total.setStallTime(total.getStallTime() + total.getChargeTime());
			total.setTotal(total.getTotal() + total.getChargeCost());
			total.setTime(total.getTime() + total.getChargeTime());
			
			System.out.println("The owner of this vehicle was charged $" + twoDec.format(total.getChargeCost()) + ".");
			System.out.println("It took " + noDec.format(Math.floor(total.getChargeTime())) + " hours and " + noDec.format(Math.floor(((total.getChargeTime() - Math.floor(total.getChargeTime())) * 60))) + " minutes to charge this vehicle.");
			System.out.println();
		}
	}
}
