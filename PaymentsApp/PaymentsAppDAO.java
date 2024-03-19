

	

	import java.sql.Connection;
	import java.sql.DriverManager;
import java.sql.ResultSet;
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
		public static void addBankAccountToDataBase(BankAccount b,User u) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			Statement Stm = Con.createStatement();
			String BankQuery = "insert into Bank_Account_Details (Bank_AcctNo,Bank_AcctBankName,Acct_TypeId,Bank_IFSC_Code,Bank_AcctPin,User_Id,Curr_Bank_Balance)+"
					+ "values"+"('"+b.getBankAcctNumber()+"','"+b.getBankAcctBankName()+"','"+b.getBankAcctAcctType()+"','"+b.getBankAcctIFSC()+"','"+b.getBankAcctPin()+"','"+u.getUserId()+"','"+0+"')";
			Stm.executeUpdate(BankQuery);
			Stm.close();
		}
		
		
		public static boolean ValidateLogin(int Uid ,String PassWord) {
			User u = new User();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
				Statement Stm = Con.createStatement();

				String Query = "Select UserID ,PassWord from User where UserID = '"+u.getUserId()+"'and PassWord ='"+u.getPassword()+"'";
				
				ResultSet res = Stm.executeQuery(Query);
				if(res.next()) {
					res.next();
					System.out.println("Login successful!");
					return true;
				}else{
					
					System.out.println("Login Failed!");
					
				}
				Stm.close();
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			return false;
		}
		public static void UsersList() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
				Statement Stm = Con.createStatement();
				String Query = "Select * from User";
				ResultSet res = Stm.executeQuery(Query);
				while(res.next()) {
					System.out.println(res.getInt(1)+" "+res.getString(2));
				}
				Stm.close();
			}catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			} 
		}
		public static void VerifyCurrentUser() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
				Statement Stm = Con.createStatement();
				String Query = "Select * from User where UserID = '"+RunPaymentsApp.currUserId+"'";
				ResultSet res = Stm.executeQuery(Query);
				while(res.next()) {
					System.out.println(res.getInt(1)+" "+res.getString(2));
				}
				Stm.close();
			}catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
		}
		public static void BankAcctList() {
			BankAccount ba = new BankAccount();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
				Statement Stm = Con.createStatement();
				String Query = "Select * from Bank_Account where UserID = '"+ba.getUserId()+"'";
				ResultSet res = Stm.executeQuery(Query);
				while(res.next()) {
					System.out.println(res.getInt(1)+" "+res.getString(2));
				}
				Stm.close();
			}catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
		
			
