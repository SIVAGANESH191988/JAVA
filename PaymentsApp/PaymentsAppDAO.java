

	

	import java.sql.Connection;
	import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
	import java.sql.Statement;
import java.util.*;
	

	public class PaymentsAppDAO extends Wallet {
		
		 
		public void storeUserDetails(User u) throws ClassNotFoundException, SQLException {
 Class.forName("com.mysql.cj.jdbc.Driver");
			 
			 Connection  con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/Payments_CLI_Application","root","root");
		
			Statement stmt = con.createStatement();
			String query = "insert into User_Info(FirstName,LastName,PhoneNumber,DOB,CommunicationAddress,PassWord,Wallet) "
					+ "values('"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getPhoneNum()+"','"+u.getDateOfBirth()+"','"+u.getCommunicationAddr()+"','"+u.getPassword()+"',"+0+")";
			System.out.println(query);
			stmt.executeUpdate(query);
		}
		
		
		
		public static boolean ValidateLogin(int userid, String passWord) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payments_CLI_Application", "root", "root"
						+ "");
				Statement Stm = Con.createStatement();
				String Query = "Select UserId,Password from User_Info";
				ResultSet res = Stm.executeQuery(Query);
				while(res.next()) {
					
					if(res.getInt("UserId")==userid && res.getString("Password").equals(passWord))
					{
						return true;
					}
				}
				Stm.close();
			}catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			return false;
		}
		public static void UsersList() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payments_CLI_Application", "root", "root");
				Statement Stm = Con.createStatement();
				String Query = "Select * from User_Info";
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
				Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payments_CLI_Application", "root", "root");
				Statement Stm = Con.createStatement();
				String Query = "Select * from BankAccount_Details where UserID = '"+ba.getUserId()+"'";
				ResultSet res = Stm.executeQuery(Query);
				while(res.next()) {
					System.out.println(res.getInt(1)+" "+res.getString(2));
				}
				Stm.close();
			}catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
		}
		public static void CurrUserId() throws SQLException, ClassNotFoundException
		{
			Scanner sc=new Scanner(System.in);
			int userId=sc.nextInt();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payments_CLI_Application", "root", "root");
			Statement Stm = Con.createStatement();
			String Query="Select UserId from User_Info";
			ResultSet res=Stm.executeQuery(Query);
			while(res.next()) {
				
				if(res.getInt("UserId")==userId )
				{
					System.out.println(userId);
				}
			}
		}
		public static void addBankAccountToDataBase(User u, BankAccount b) throws ClassNotFoundException, SQLException {
	
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payments_CLI_Application", "root", "root");
			Statement Stm = Con.createStatement();
			String BankQuery = "insert into Bank_Account_Details (Bank_AcctNo,Bank_AcctBankName,Acct_TypeId,Bank_IFSC_Code,Bank_AcctPin,User_Id,Curr_Bank_Balance)+"
					+ "values"+"('"+b.getBankAcctNumber()+"','"+b.getBankAcctBankName()+"','"+b.getBankAcctAcctType()+"','"+b.getBankAcctIFSC()+"','"+b.getBankAcctPin()+"','"+u.getUserId()+"','"+0+"')";
			Stm.executeUpdate(BankQuery);
			Stm.close();
		}
		public void addMoneyToWallet( double amount) throws ClassNotFoundException, SQLException
		{
			Wallet w=new Wallet();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payments_CLI_Application", "root", "root");
			Statement Stm = Con.createStatement();

			
			String Query = "Update user_info SET Wallet= wallet+'"+amount+"'Where UserID='"+RunPaymentsApp.currUserId+"'";
			
				int res=Stm.executeUpdate(Query);
		    Stm.close();
		}
		
	}
		
			
