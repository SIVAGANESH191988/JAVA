

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;




public class RunPaymentsApp 
{
//Driver class


public static List<User> UsersList =new ArrayList<User>();
public static List<BankAccount> BAList = new ArrayList<BankAccount>();
public static int CurrentUserId = -1;
 
public static Map<Integer, Wallet> UsersWallet = new HashMap<Integer, Wallet>();
public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int selectedOption=0;		
		Scanner opt = new Scanner(System.in);
		
		while(true) {
			System.out.println("           🏦      💲💲💲💲💲💲      🏦          ");
			System.out.println("   <------- Payments App Options: ------->    ");
			System.out.println("");
			
			System.out.println("1 . New User? / Register.");
			
			System.out.println("2 . Existing User? / Login.");
			
			System.out.println("3 . ADD New Bank Account.");
			
			System.out.println("4 . Show Users List.");
			
			System.out.println("5 . Show Current Logged In User.");
			
			System.out.println("6 . Show All Users Bank Accounts List.");
			
			System.out.println("7 . Add Money To Wallet.");
			
			System.out.println("8 . Delete Bank Account.");
			
			System.out.println("9 . Show Users Wallet Balance.");
			
			System.out.println("10. Make A Transaction.");
			
			System.out.println("11. LOGOUT.");
			
			System.out.println("-1. EXIT APP.");
			
			System.out.println("");
			System.out.println("Please Select An Option From Above :");
			
			String optStr = opt.next();
		try {
				selectedOption = Integer.parseInt(optStr);
				
		}
		catch(NumberFormatException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Number format Error Has Been Occured Please choose another option. Thank You");
				
		}
		catch(ArithmeticException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Arithmetic Error Has Been Occured Please choose another option. Thank You");
				
		}
		catch(Exception e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Some Error has Been Occured Please choose another option. Thank You");
		}
		finally{
				System.out.println();
			}
			
			    System.out.println("User selected "+selectedOption);
			    UserOperations ops = new UserOperations();
		if(optStr.equalsIgnoreCase("1")) {
				registerUser();
		}
		else if(optStr.equalsIgnoreCase("2")) {
		if(CurrentUserId!=-1)
			{
				System.out.println("Please logout From Current Logged In user");
		}
		else
		{ 
			loginUser();
		}
		}
//		else
//			{
//		if(!loginUser()) {
//					break;
//				}
//			  }
//			}
		else if(optStr.equalsIgnoreCase("3")) {
		if(validateCurrentUser()) {
					addBankAccount();
				}
		}
		else if(optStr.equalsIgnoreCase("4")) {
				ops.printUserList(UsersList);
		}
		else if(optStr.equalsIgnoreCase("5")) {
		if(CurrentUserId != -1) {
					ops.printCurrUserDetails(CurrentUserId);
				}
		}
		else if(optStr.equalsIgnoreCase("6")) {
		if(CurrentUserId != -1) {
					printUserBankAcctsList();
				}
			}
		else if(optStr.equalsIgnoreCase("7"))
			{
				UserOperations u1 = new UserOperations();
		if(CurrentUserId != -1) {
					Scanner sc=new Scanner(System.in);
					double Amount=sc.nextDouble();
				    u1.AddingMoneyToWallet(Amount);
				}
			}
		else if(optStr.equalsIgnoreCase("8"))
			{
		if (CurrentUserId != -1) {
			        Scanner scanner = new Scanner(System.in);
			        System.out.println("Enter the AccountNumber to Delete:");
			        String AccountNumber = scanner.next();
			        ops.DeletingBankAccount(CurrentUserId, AccountNumber);
			    }
		else {
			        System.out.println("No User Has logged In.");
			    }
			}
		else if(optStr.equalsIgnoreCase("9")) {
		if( CurrentUserId!= -1) {
					System.out.println("Your Current Balance Is :"  + ops.checkWalletBalance());
				}
		else {
					System.out.println("Please Login to Check Wallet Balance");
				}
			}
			
		else if(optStr.equalsIgnoreCase("10")) {
				UserOperations u1=new UserOperations();
				u1.DoTransaction();
			}
			
		else if(optStr.equalsIgnoreCase("11")) {
			System.out.println("You Have Been logged Out Thank You 😄 ");
				CurrentUserId=-1;
		
			}
		else if(optStr.equalsIgnoreCase("-1"))
		{
			System.out.println("");
System.out.println("You Have Been Exit Successfully !....Thank You.");	
break;
			}
			
		}
	}
	
public static void registerUser() {
	try {
		Scanner Opt = new Scanner(System.in);
		System.out.println("");
		UserOperations Operations = new UserOperations();
		
		System.out.println("Please Provide Your Details As Shown Below:");
		System.out.println("");
		System.out.println("Enter First Name:");
		String FirstName = Opt.next();
		System.out.println("Enter Last Name:");
		String LastName = Opt.next();
		System.out.println("Enter Phone Number:");
		long PhoneNo = Long.parseLong(Opt.next());
		System.out.println("Enter Date Of Birth:");
		String DOB = Opt.next();
		System.out.println("Enter Address:");
		String CommunicationAddress = Opt.next();
		System.out.println("Enter Password:");
		String Password = Opt.next();
		System.out.println("");
		System.out.println("          You Have Been Successfully Registered !....Thank You.        ");
		System.out.println("");
		System.out.println("Want To Continue ? Please Select An Option Below");
		
	   
User u  = Operations.doUserRegistration(FirstName, LastName, Password, PhoneNo, DOB, CommunicationAddress);
			

         	try {
				PaymentsBankingDAO DAO = new PaymentsBankingDAO();
				DAO.storeUserDetails(u);
			}
         	
         	catch(Exception e) {
				e.printStackTrace();
			}
			
			UsersList.add(u);
			Wallet wallet=new Wallet();
			int UserID =u.getUserId();
			UsersWallet.put(UserID, wallet);
		}
	    catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
public static void  loginUser()   {
	
		Scanner Option2 = new Scanner(System.in);
		UserOperations ops = new UserOperations();
		System.out.println("Enter UserId:");
		int UserId = Option2.nextInt();
		System.out.println("Enter Password:");
	   	String Password = Option2.next();
	   	PaymentsBankingDAO db = new PaymentsBankingDAO();
	    if(PaymentsBankingDAO.verifyLoginDetails(UserId, Password)) {
			CurrentUserId = UserId;
			
			System.out.println("Login Success");
	    }
	    else {
			System.out.println("Login Failed, Please Enter Correct Password:");
			System.out.println("");
			System.out.println("Please Select Login Option Again :");
			System.out.println("");
			
			
		}
	
}
public static boolean validateCurrentUser() {
	if(CurrentUserId != -1) {
			return true;
		}
	    else {
			return false;
		}
	}
	
public static void addBankAccount() {

		BankAccount BankAccount = new BankAccount();
		Scanner Option3 = new Scanner(System.in);
		
		System.out.println("Enter Bank Account Number:");
		String AccountNumber = Option3.next();
		
		System.out.println("Enter IFSC Code:");
		String IFSCCODE = Option3.next();
		
		System.out.println(" Enter Account Type : ");
		System.out.println("SA: SAVINGS");
		System.out.println("CU: CURRENT");
		System.out.println("LN: LOAN");
		System.out.println("SL: SALARY");
	    try {
			String Accounttype = Option3.next();
			AccountType Type = AccountType.valueOf(Accounttype);
			BankAccount.setBankAcctAcctType(Type);
			}
	    catch(IllegalArgumentException e) {
				System.out.println("Please Select the Correct Account Type : ");
				e.printStackTrace();
			}
    
		System.out.println("Enter Account PIN:");
	
		String AccountPin = Option3.next();
		BankAccount.setBankAccountNumber(AccountNumber);
		BankAccount.setIFSCNumber(IFSCCODE);
		BankAccount.setBankAccountPin(AccountPin);
		BankAccount.setUserId(CurrentUserId);
		
		for(User u:UsersList) {
		if(u.getUserId() == CurrentUserId) {
				u.getBankAccList().add(BankAccount);
			}
		}
		
BAList.add(BankAccount);
try {
	PaymentsBankingDAO DAO = new PaymentsBankingDAO ();
	DAO.storeBankAcctDetails(BankAccount);
	
}
catch(ClassNotFoundException e) {
	e.printStackTrace();
}
catch(SQLException e) {
	e.printStackTrace();
}
	}
	
public static void printUserBankAcctsList() {
		UserOperations ops = new UserOperations();
Map<User,List<BankAccount>> mapItems = ops.getUserBankAccounts();

	    for(User u:mapItems.keySet()) {
			List<BankAccount> //baList = new ArrayList<BankAccount>();
					baList = mapItems.get(u);
			System.out.println(u);
	    if(baList != null) {
	    for(BankAccount ba: baList) {
	  	System.out.println("--"+ba.printBankAccountDetails());
	  	
				}
			}
			
		}
	
}
}

		
	

	