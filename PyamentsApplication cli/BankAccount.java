

public class BankAccount {
	
	private Long bankAcctNumber;
	private String bankAcctBankName;
	private String bankAcctIFSC;
	private AcctType bankAcctAcctType;
	private String bankAcctPin;
	private int userId;
	private double Balance;
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Long getBankAcctNumber() {
		return bankAcctNumber;
	}
	public void setBankAcctNumber(Long bankAcctNumber) {
		this.bankAcctNumber = bankAcctNumber;
	}
	public String getBankAcctBankName() {
		return bankAcctBankName;
	}
	public void setBankAcctBankName(String bankAcctBankName) {
		this.bankAcctBankName = bankAcctBankName;
	}
	public String getBankAcctIFSC() {
		return bankAcctIFSC;
	}
	public void setBankAcctIFSC(String bankAcctIFSC) {
		this.bankAcctIFSC = bankAcctIFSC;
	}
	public AcctType getBankAcctAcctType() {
		return bankAcctAcctType;
	}
	public void setBankAcctAcctType(AcctType bankAcctAcctType) {
		this.bankAcctAcctType = bankAcctAcctType;
	}
	public String getBankAcctPin() {
		return bankAcctPin;
	}
	public void setBankAcctPin(String bankAcctPin) {
		this.bankAcctPin = bankAcctPin;
	}
	
	public String  printBankAccountDetails() {
		return "[" +this.bankAcctNumber+","+this.bankAcctIFSC+this.Balance+","+ "]";
	}
	
}