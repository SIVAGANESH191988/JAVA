

	

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;

	

	public class PaymentsAppDAO {
		
		 
		public void storeUserDetails(User u) throws ClassNotFoundException, SQLException {
 Class.forName("com.mysql.cj.jdbc.Driver");
			 
			 Connection  con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/clipayments","root","root");
		
			Statement stmt = con.createStatement();
			String query = "insert into User(FirstName,LastName,PhoneNumber,DOB,CommunicationAddress,PassWord,Wallet) "
					+ "values('"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getPhoneNum()+"','"+u.getDateOfBirth()+"','"+u.getCommunicationAddr()+"','"+u.getPassword()+"',"+0+")";
			System.out.println(query);
			stmt.executeUpdate(query);
		}
		public void addAccountToDataBase(BankAccount b) throws SQLException, ClassNotFoundException {
                 Class.forName("com.mysql.cj.jdbc.Driver");
			 
                 
			 Connection  con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/clipayments","root","root");
		
			Statement stmt = con.createStatement();
			
			 String query = "insert into bankaccount "+"values('"+b.getUserId()+"','"+b.getBankAcctNumber()+"','"+b.getBankAcctBankName()+"','"+b.getBankAcctIFSC()+"','"+b.getBankAcctAcctType()+"','"+b.getBalance()+"')";
			 stmt.executeUpdate(query);
			 System.out.println(query);
			 con.close();
		 }
	}
		
			
