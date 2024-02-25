

public class Wallet {
    private static double currntBal;

	private int userId;
	private double limit;
	public static double getCurrntBal() {
		return currntBal;
	}
	public static void setCurrntBal(double currntBal) {
		Wallet.currntBal = currntBal;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getLimit() {
		return limit;
	}
	public void setLimit(double limit) {
		this.limit = limit;
	}
	
}