



public class BankAccount {
	
	private String   BankAccountNumber;
	private String   BankAccountName;
	private String   IFSCNumber;
	private AccountType BankAccountType;
	private String   BankAccountPin;
	private int      UserId;
	private double BankBalance;
	
	public double getBankBalance() {
	 return BankBalance;
	}
	public void setBankBalance(double Balance) {
		BankBalance = Balance;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		this.UserId = userId;
	}
	public String getBankAccountNumber() {
		return BankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.BankAccountNumber = bankAccountNumber;
	}
	public String getBankName() {
		return getBankName();
	}
	public void setBankAccountName(String BankAccountName) {
		this.BankAccountName = BankAccountName;
	}
	public String getIFSCNumber() {
		return IFSCNumber;
	}
	public void setIFSCNumber(String IFSCNumber) {
		this.IFSCNumber = IFSCNumber;
	}
	public AccountType getBankAccountType() {
		return BankAccountType;
	}
	public void setBankAcctAcctType(AccountType BankAccountType) {
		this.BankAccountType = BankAccountType;
	}
	public String getBankAccountPin() {
		return BankAccountPin;
	}
	public void setBankAccountPin(String BankAccountPin) {
		this.BankAccountPin = BankAccountPin;
	}
	
	public String  printBankAccountDetails() {
		return "[" +this.BankAccountNumber+","+this.IFSCNumber+ "]";
	}
	
}