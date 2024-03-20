



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class RunPaymentsApp 
{
	//Driver class


	public static List<User> usersList =new ArrayList<User>();
	public static List<BankAccount> bankAcctList = new ArrayList<BankAccount>();
	public static int currUserId = -1;
 
   
	  public static Map<Integer, Wallet> walletList = new HashMap<Integer,Wallet>();
	public static void main(String[] args) {
		
		int selectedOption=0;		
		
		Scanner opt = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("Payments App Actions:");
			System.out.println("1. Register New User");
			System.out.println("2. Login");
			System.out.println("3. ADD Bank Account");
			System.out.println("4. List of Users");
			System.out.println("5. Current User");
			System.out.println("6. List All User Bank Accounts");
			System.out.println("7. Add Money To Wallet");
			System.out.println("8. Delet the BankAccount");
			System.out.println("9. list User Wallet Balances");
			System.out.println("10. Do a Transaction");
			System.out.println("-1. Quit/ Logout");
			System.out.println("Choose an Option:");
			
			String optStr = opt.next();
			try {
				selectedOption = Integer.parseInt(optStr);
				
			}catch(NumberFormatException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Number format Error Occured Please choose another option.");
				
			}catch(ArithmeticException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("arithmetic Error Occured Please choose another option.");
				
			}catch(Exception e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Some Error Occured Please choose another option.");
			}finally{
				System.out.println();
			}
			
			System.out.println("User selected "+selectedOption);
			UserOperations ops = new UserOperations();
			if(optStr.equalsIgnoreCase("1")) {
				registerUser();
			}else if(optStr.equalsIgnoreCase("2")) {
			if(currUserId!=-1)
			{
				System.out.println("logout current user");
			}
			else
			{
				loginUser();
			}
			}
			else if(optStr.equalsIgnoreCase("3")) {
				if(validateCurrentUser()) {
					addBankAccount();
				}
			}else if(optStr.equalsIgnoreCase("4")) {
				ops.printUserList(usersList);
			}else if(optStr.equalsIgnoreCase("5")) {
				if(currUserId != -1) {
					ops.printCurrUserDetails(currUserId);
				}
			}else if(optStr.equalsIgnoreCase("6")) {
				if(currUserId != -1) {
					printUserBAnkAcctsList();
				}
			}
			else if(optStr.equalsIgnoreCase("7"))
			{
				UserOperations u1=new UserOperations();
				if(currUserId != -1) {
					Scanner sc=new Scanner(System.in);
					double amount=sc.nextDouble();
				u1.addMoneyToWallet(amount);
				}
			}
			else if(optStr.equalsIgnoreCase("8"))
			{
				if (currUserId != -1) {
			        Scanner scanner = new Scanner(System.in);
			        System.out.println("Enter the account number to delete:");
			        String accountNumber = scanner.next();
			        ops.deleteBankAccount(currUserId, accountNumber);
			    } else {
			        System.out.println("No user logged in.");
			    }
			}
			else if(optStr.equalsIgnoreCase("9")) {
				if( currUserId!= -1) {
					System.out.println(ops.checkWalletBalance()); 
				}else {
					System.out.println("Please Log in to Check Balance In Wallet");
				}
			}
			else if(optStr.equalsIgnoreCase("10")) {
				UserOperations u1=new UserOperations();
				u1.DoTransaction();
			}
			else if(optStr.equalsIgnoreCase("-1")) {
				currUserId=-1;
				
			}else {
				
			}
			
		}
	}
	
	public static void registerUser() {
		Scanner opt = new Scanner(System.in);
		UserOperations ops = new UserOperations();

		System.out.println("Please do provide user details as asked:");
		System.out.println("First Name:");
		String fName = opt.next();
		System.out.println("Last Name:");
		String lName = opt.next();
		System.out.println("Phone Number:");
		long phNo = Long.parseLong(opt.next());
		System.out.println("Date Of Birth:");
		String dob = opt.next();
		System.out.println("Address:");
		String addr = opt.next();
		System.out.println("Password:");
		String password = opt.next();

		User u=null;
		try {
			u = ops.doUserRegistration(fName, lName, password, phNo, dob, addr);			
			//usersList.add(u);
			PaymentsAppDAO dao = new PaymentsAppDAO();
			dao.storeUserDetails(u);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Wallet w = new Wallet();
		int UserId = u.getUserId();
		walletList.put(UserId, w);
		
	}
	
	public static void loginUser() {
		Scanner opt = new Scanner(System.in);
		UserOperations ops = new UserOperations();
		
		System.out.println("Login Through UserId");
		System.out.println("Enter User Id :");
		int UId = opt.nextInt();
		System.out.println("Enter Password :");
		String PassWord = opt.next();
		PaymentsAppDAO db = new PaymentsAppDAO();
		if(PaymentsAppDAO.ValidateLogin(UId, PassWord)) {
			currUserId = UId;
			
			System.out.println("Login Success");
			
		}else {
			System.out.println("Login Failed");
		}
		
		
	}
	
	public static boolean validateCurrentUser() {
		if(currUserId != -1) {
			return true;
		}else {
			return false;
		}
	}
	public static void AddBankAcc() {
		Scanner opt = new Scanner(System.in);
		User u = new User();
		
		UserOperations ops = new UserOperations();
		System.out.println("Add Bank Account Details");
		System.out.println("Enter The Account No : ");
		long AccNo = opt.nextLong();
		System.out.println("Enter the Bank Account Name :");
		String AcctBankName = opt.next();
		System.out.println("Enter the Bank Account Type");
		System.out.println("Please Select The Account Type :");
		for(AcctType type : AcctType.values()) {
			System.out.println(" "+ type);
		}
		 AcctType Accty = null;
	       
		try {
			System.out.println("Enter a number from 0 to 3:");
	       int Acct_TypeId = opt.nextInt();

	        if (Acct_TypeId < 0 || Acct_TypeId > 3) {
	            System.out.println("Invalid number. Please enter a number from 0 to 3.");
	        } else {
	            AcctType day = AcctType.values()[Acct_TypeId];
	            System.out.println("You selected: " + Acct_TypeId);
	        }
	
		}catch(IllegalArgumentException ie) {
			System.out.println("Please Select the Correct Acctype : ");
			for(AcctType type : AcctType.values()) {
				System.out.println(" "+ type);
			}
		}
	        
		System.out.println("Enter the Account IFSC Code :");
		String AcctIFSCCode = opt.next();
		System.out.println("Enter the Account Pin : ");
		int AcctPin = opt.nextInt();
		
		try {
			BankAccount b = null;
			
			b =opt.addBankAcc(AccNo, AcctBankName, Accty, AcctIFSCCode, AcctPin);
			PaymentsAppDAO p = new PaymentsAppDAO();
			PaymentsAppDAO.addBankAccountToDataBase(u, b);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printUserBAnkAcctsList() {
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
		
	

	
