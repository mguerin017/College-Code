public class calculateToll {
	
	private final int ELECTRIC = 1;
	private final int HYBRID = 2;
	private final int GASOLINE = 3;
	private final int CASH = 1;
	private final int CARD = 2;
	private final int ELECTRONICSYSTEM = 3;

	public void axles() {
		main getMain = new main();
		if (getMain.axle <= 3) {
			getMain.vehicleTot = getMain.vehicleTot + getMain.axle - 1;
		}
		else if (getMain.axle > 3) {
			getMain.vehicleTot = getMain.vehicleTot + 2 + (getMain.axle - 3) * 5;
		}
		else {
			System.out.println();
			System.out.println("You have made an invalid input. Please start over.");
			return;
		}
	}
	
	public void vehicleTypes() {
		main getMain = new main();
		if (getMain.vehicleType == ELECTRIC) {
			getMain.vehicleTot = getMain.vehicleTot * 0.5;
		}
		else if (getMain.vehicleType == HYBRID) {
			getMain.vehicleTot = getMain.vehicleTot * 0.75;
		}
		else if (getMain.vehicleType == GASOLINE) {
			getMain.vehicleTot = getMain.vehicleTot * 1;
		}
		else {
			System.out.println();
			System.out.println("You have made an invalid input. Please start over.");
			return;
		}
	}
	
	public void payTypes() {
		main getMain = new main();
		if (getMain.payType == CASH) {
			getMain.vehicleTot = getMain.vehicleTot * 1;
			getMain.cashTot += getMain.vehicleTot;
		}
		else if (getMain.payType == CARD) {
			getMain.vehicleTot = getMain.vehicleTot * 1.15;
			getMain.cardTot += getMain.vehicleTot;
		}
		else if (getMain.payType == ELECTRONICSYSTEM) {
			getMain.vehicleTot = getMain.vehicleTot * 0.8;
			getMain.elecTot += getMain.vehicleTot;
		}
		else {
			System.out.println();
			System.out.println("You have made an invalid input. Please start over.");
			return;
		}
	}
}
