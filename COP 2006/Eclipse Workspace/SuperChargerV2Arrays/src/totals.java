import java.util.ArrayList;

public class totals {

	// Fields initialized here

		private static double chargeTime;
		private static double chargeCost;
		private static double stallTotal;
		private static double stallTime;
		private static double total;
		private static double time;
		private static double totalComplimentary;
		private static double timeComplimentary;
		
		public static ArrayList<Double> stallTotalList;
		public static ArrayList<Double> stallTimeList;
		public static ArrayList<Double> totalList;
		public static ArrayList<Double> timeList;
		public static ArrayList<Double> totalComplimentaryList;
		public static ArrayList<Double> timeComplimentaryList;
		
		// Getters and setters for fields
		public double getChargeTime() {
			return this.chargeTime;
		}
		public void setChargeTime(double newChargeTime) {
			this.chargeTime = newChargeTime;
		}
		
		public double getChargeCost() {
			return this.chargeCost;
		}
		public void setChargeCost(double newChargeCost) {
			this.chargeCost = newChargeCost;
		}
		
		public double getStallTotal() {
			return this.stallTotal;
		}
		public void setStallTotal(double newStallTotal) {
			this.stallTotal = newStallTotal;
		}
		
		public double getStallTime() {
			return this.stallTime;
		}
		public void setStallTime(double newStallTime) {
			this.stallTime = newStallTime;
		}
		
		public double getTotal() {
			return this.total;
		}
		public void setTotal(double newTotal) {
			this.total = newTotal;
		}
		
		public double getTime() {
			return this.time;
		}
		public void setTime(double newTime) {
			this.time = newTime;
		}
		
		public double getTotalComplimentary() {
			return this.totalComplimentary;
		}
		public void setTotalComplimentary(double newTotalComplimentary) {
			this.totalComplimentary = newTotalComplimentary;
		}
		
		public double getTimeComplimentary() {
			return this.timeComplimentary;
		}
		public void setTimeComplimentary(double newTimeComplimentary) {
			this.timeComplimentary = newTimeComplimentary;
		}
	
		//Getters and setters for arrays
		public ArrayList<Double> getStallTotalList() {
			return this.stallTotalList;
		}
		public double getIndexStallTotalList(int index) {
			return this.stallTotalList.get(index);
		}
		public int getSizeStallTotalList() {
			return this.stallTotalList.size();
		}
		public void addToStallTotalList(double amount) {
			this.stallTotalList.add(amount);
		}
		public void clearStallTotalList() {
			this.stallTotalList.clear();
		}
		
		public ArrayList<Double> getStallTimeList() {
			return this.stallTimeList;
		}
		public double getIndexStallTimeList(int index) {
			return this.stallTimeList.get(index);
		}
		public int getSizeStallTimeList() {
			return this.stallTimeList.size();
		}
		public void addToStallTimeList(double amount) {
			this.stallTimeList.add(amount);
		}
		public void clearStallTimeList() {
			this.stallTimeList.clear();
		}
		
		public ArrayList<Double> getTotalList() {
			return this.totalList;
		}
		public double getIndexTotalList(int index) {
			return this.totalList.get(index);
		}
		public int getSizeTotalList() {
			return this.totalList.size();
		}
		public void addToTotalList(double amount) {
			this.totalList.add(amount);
		}
		
		public ArrayList<Double> getTimeList() {
			return this.timeList;
		}
		public double getIndexTimeList(int index) {
			return this.timeList.get(index);
		}
		public int getSizeTimeList() {
			return this.timeList.size();
		}
		public void addToTimeList(double amount) {
			this.timeList.add(amount);
		}
		
		public ArrayList<Double> getTotalComplimentaryList() {
			return this.totalComplimentaryList;
		}
		public double getIndexTotalComplimentaryList(int index) {
			return this.totalComplimentaryList.get(index);
		}
		public int getSizeTotalComplimentaryList() {
			return this.totalComplimentaryList.size();
		}
		public void addToTotalComplimentaryList(double amount) {
			this.totalComplimentaryList.add(amount);
		}
		
		public ArrayList<Double> getTimeComplimentaryList() {
			return this.timeComplimentaryList;
		}
		public double getIndexTimeComplimentaryList(int index) {
			return this.timeComplimentaryList.get(index);
		}
		public int getSizeTimeComplimentaryList() {
			return this.timeComplimentaryList.size();
		}
		public void addToTimeComplimentaryList(double amount) {
			this.timeComplimentaryList.add(amount);
		}
}
