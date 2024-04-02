




import java.util.ArrayList;
import java.util.List;

public class User extends Object{
	
	private String FirstName;
	private String LastName;
	private long   PhoneNum;
	private String DateOfBirth;
	private String CommunicationAddress;
	private Wallet Wallet;
	private int    UserId;
	private String Password;
	
	private List<BankAccount> BankAccList = new ArrayList<BankAccount>();
	
//	private int paymentsAcctId;
	//private Wallet w;
	
	public Wallet getWallet() {
		return Wallet;
	}
	public void setWallet(Wallet wallet) {
		this.Wallet = wallet;
	}
	public List<BankAccount> getBankAccList() {
		return BankAccList;
	}
	public void setBaList(List<BankAccount> BankAccList) {
		this.BankAccList = BankAccList;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	public long getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(long PhoneNum) {
		this.PhoneNum = PhoneNum;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String DateOfBirth) {
		this.DateOfBirth = DateOfBirth;
	}
	public String getCommunicationAddress() {
		return CommunicationAddress;
	}
	public void setCommunicationAddr(String CommunicationAddress) {
		this.CommunicationAddress = CommunicationAddress;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int UserId) {
		this.UserId = UserId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	
//	Account[]
//	BankAccount[]
	@Override
	public String toString() {
		return this.UserId+":"+ this.FirstName +":"+ this.LastName + ":"+this.PhoneNum+":"+this.DateOfBirth+":"+this.CommunicationAddress;
	}
	
	public String userToFileRecord() {
		return this.UserId+","+ this.FirstName +","+ this.LastName + ","+this.PhoneNum+","+this.DateOfBirth+","+this.CommunicationAddress+"\n";
	}

	
	
}
