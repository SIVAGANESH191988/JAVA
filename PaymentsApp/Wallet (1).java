package paymentsapp;



public class Wallet {
    private double currntBal;

	private int userId;
	private double limit;
	public double getCurrntBal() {
		return currntBal;
	}
	public void setCurrntBal(double currntBal) {
		this.currntBal = currntBal;
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