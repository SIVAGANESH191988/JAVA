import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*; 


public class PaymentsBankingDAO {

	public void storeUserDetails(User u) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		Statement stmt = con.createStatement();
		String Query = "insert into  User_Details(First_Name,Last_Name,Phone_Num,DOB,Address,Password,Wallet_Balance) "
		+ "values('"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getPhoneNum()+"','"+u.getDateOfBirth()+"','"+u.getCommunicationAddress()+"','"+u.getPassword()+"',"+0+")";
//		System.out.println(Query);
		stmt.executeUpdate(Query);
		con.close();
	}
	
	
	public static  boolean verifyLoginDetails(int uId ,String Password) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		Statement stmt = con.createStatement();
		System.out.println("Enter Your Password :");
		String Query ="select User_ID,Password from User_Details";
		ResultSet res = stmt.executeQuery(Query);
		System.out.println();
		while (res.next()) {
			if(res.getInt("User_ID")== uId && res.getString("Password").equals(Password)) {
				
				return true;
			}
		}
		
		con.close();
	}
	catch(SQLException  | ClassNotFoundException e) {
		
		e.printStackTrace();
	}
		
		return false;
	}
	
	
	public void storeBankAcctDetails(BankAccount b) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		Statement stmt = con.createStatement();
		String Query ="insert into Bank_Acct_Details"
		+"values('"+b.getUserId()+"','"+b.getBankAccountNumber()+"','"+b.getBankBalance()+"','"+b.getIFSCNumber()+"','"+b.getBankName()+"','"+b.getBankAccountType()+"')";
		System.out.println(Query);
		stmt.executeUpdate(Query);
		con.close();
	}
	
	public void addingMoneyToWallet(double amount) throws ClassNotFoundException, SQLException {
		Wallet w = new Wallet();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		Statement stmt = con.createStatement();
//		String query = "SELECT * FROM User_Details WHERE User_Id = '"+RunPaymentsApp.CurrentUserId+"'";
		String query ="UPDATE User_Details SET Wallet_Balance = Wallet_Balance + '" + amount + "' WHERE User_Id ='"+RunPaymentsApp.CurrentUserId+"'";
	   int res = stmt.executeUpdate(query);
	   con.close();
	}
	
	public void storeUsersList() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		Statement stmt = con.createStatement();
		String Query ="select * from USer_Details";
		ResultSet res = stmt.executeQuery(Query);
		while(res.next()) {
			System.out.println(res.getInt(1)+""+res.getString(2));
		}
		con.close();
	}
	
	public void storeCurrLoginUser() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		Statement stmt = con.createStatement();
		String Query="select * from User_Details where User_ID ='"+RunPaymentsApp.CurrentUserId+"'";
		ResultSet res = stmt.executeQuery(Query);
		while(res.next()) {
			System.out.println(res.getInt(1)+""+res.getString(2));
		}
		con.close();
	}
	
}



