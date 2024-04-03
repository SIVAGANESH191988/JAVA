

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class UserOperations {
	
	List<User> users = RunPaymentsApp.usersList;
	List<BankAccount> bankAcctList =RunPaymentsApp.bankAcctList;
	  private static Map<Integer, Wallet> WalletList = RunPaymentsApp.userWallets;
	  List <Transaction> t1=new ArrayList<Transaction>();

	 // public static Map<Integer, List<Transaction>> TransactionList =new HashMap<>() ;
//	 public static List <Transaction> t1=new ArrayList<Transaction>();
//     private static Map<Integer,List<BankAccount>> userB = new HashMap<>();
	public UserOperations() {
		users= RunPaymentsApp.usersList;
		bankAcctList = RunPaymentsApp.bankAcctList;
	}
	
	public User doUserRegistration(String fName, String lName, String password, long phNum, String dob,String addr) throws Exception {
		User u = new User();
		u.setFirstName(fName);
		u.setLastName(lName);
		u.setPhoneNum(phNum);
		u.setDateOfBirth(dob);
		u.setCommunicationAddr(addr);
		u.setPassword(password);
		
		if((fName+lName).length() >50) {
			throw new Exception();
		}
		
		u.setUserId((int)(Math.random()*1000)+100);
		 Wallet wallet = new Wallet();
		 WalletList.put(u.getUserId(), wallet);
		   

		PaymentsFileOps pfOps = new PaymentsFileOps();
		pfOps.writeUserToFile(u);
		return u;
	}
	
	public void printUserList(List<User> users){
		for(User u:users) {
			if(users != null) {
				System.out.println("User Details of "+ u.getFirstName());
				System.out.println(u);
			}
		}
	}
	
	public boolean verifyUserLogin(String userId, String password){
		for(int i=0;i<users.size();i++) {
			if(String.valueOf(users.get(i).getUserId()).equals(userId)) {
				if(password.equals(users.get(i).getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void printCurrUserDetails(int userId){
		for(User u:users) {
			if(u.getUserId() == userId) {
				System.out.println(u);
			}else {
				System.out.println("No User Logged in.");
			}
		}
//		for(int i=0;i<users.size();i++) {
//			if(users.get(i).getUserId() != userId) {
//				System.out.println(users.get(i));
//				break;
//			}
//		}
	}
	
	public Map<User,List<BankAccount>> getUserBankAccounts() {
		
//		Map<User,BankAccount> userBankAcctMap = new HashMap<User,BankAccount>();
		Map<User,List<BankAccount>> userBankAcctMap = new HashMap<User,List<BankAccount>>();
				
		for(User u:users) {
			if(users != null) {
				userBankAcctMap.put(u, u.getBaList());
			}
		}
		return userBankAcctMap;
		
	}
	public void deleteBankAccount(int userId, Long accountNumber) {
	    for (User user : users) {
	        if (user.getUserId() == userId) {
	            List<BankAccount> userBankAccounts = user.getBaList();
	            for (BankAccount account : userBankAccounts) {
	                if (account.getBankAcctNumber()==accountNumber) {
	                    userBankAccounts.remove(account);
	                    System.out.println("Bank account deleted successfully.");
	                    return;
	                }
	            }
	            System.out.println("Bank account not found for the user.");
	            return;
	        }
	    }
	    System.out.println("User not found.");
	}

	public static void addMoneyToWallet(int userId, double amount) {
	    Wallet wallet = WalletList.get(userId);
	    
//	    
	    wallet.setLimit(50000);
	    if (wallet.getCurrntBal() + amount <= wallet.getLimit()) {
	        wallet.setCurrntBal(wallet.getCurrntBal() + amount);
	        WalletList.put(userId, wallet);
	        System.out.println( wallet.getCurrntBal());
	    } else {
	        System.out.println("Maximum wallet amount is " + wallet.getLimit());
	    }
//	    	}
	    	
	}
	

	public double checkWalletBalance(int userId){
	    Wallet wallet = WalletList.get(userId);
	    return wallet.getCurrntBal();
	}
	public void addMoneyBank(long accountNum, double amount) {
	    
	    for (User u:users) {
	    	List<BankAccount> bankAcctList=u.getBaList();
	    	{
	    		for(BankAccount ba:bankAcctList)
	    		{
	    			 if (ba.getBankAcctNumber() == accountNum) {
	    		            ba.setBalance(ba.getBalance() + amount);
	    		            System.out.println("Updated balance for account " + accountNum + ": " + ba.getBalance());
	    		            break;
	    		}
	    }
	       
	    

	}
	    }
	}

	
	public void checkBankBalance(long accountNum)
	{
		 for (User u:users) {
		    	List<BankAccount> bankAcctList=u.getBaList();
		    	{
		    		for(BankAccount ba:bankAcctList)
		    		{
		    			 if (ba.getBankAcctNumber() == accountNum) {
		    		            
		    		            System.out.println( ba.getBalance());
		    		            break;
		    		}
		    }
		       
		    

		}
	
	}
	}
	public Map<User,List<Transaction>> getUserTransactionList(int userid) {
		
//		Map<User,BankAccount> userBankAcctMap = new HashMap<User,BankAccount>();
		Map<User,List<Transaction>> userTransMap = new HashMap<User,List<Transaction>>();
		
		for(User u:users) {
			if(u.getUserId()==userid) {
				userTransMap.put(u,t1);
			}
		}
		return userTransMap;
		
	}
	public void transactionlist(double amount,TransactionSource Trnxsrc,TransactionDestination TrnxDest)
	{
		 Transaction txn = new Transaction();
		 int a= (int)(Math.random() * 1000) + 100;

         // Create transaction object and set properties
         txn.setTrnxnId(a);
         txn.setTrnxnAmt(amount);
         List <Transaction> t1=new ArrayList<Transaction>();
    	t1.add(txn);
    	
	}
   
	public void DoTransaction() {
		
	    Scanner sc = new Scanner(System.in);
	    Transaction txn = new Transaction();
	    System.out.println("Enter amount:");
	    double amount=sc.nextDouble();

	    System.out.println("Select the option to send money from which account (CASH/WALLET): ");
	    try {
	        String srcType = sc.next();
	        TransactionSource src = TransactionSource.valueOf(srcType);
	        txn.setTrnxsrc(src);
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	      
	    }
	    


	    if (txn.getTrnxsrc() == TransactionSource.CASH) {
	    	
	    	if(amount>0)
	    	{
//	    		Transaction t = new Transaction();
	    		
	    	addMoneyToWallet(RunPaymentsApp.currUserId, amount);
//	    	 int a= (int)(Math.random() * 1000) + 100;
//
//	         // Create transaction object and set properties
//	         txn.setTrnxnId(a);
//	         txn.setTrnxnAmt(amount);
//	         List <Transaction> t1=new ArrayList<Transaction>();
//	    	t1.add(txn);
//	    	TransactionList.put(RunPaymentsApp.currUserId,t1 );
	    	transactionlist(amount, txn.getTrnxsrc(), txn.getTrnxDest());
	    	 getUserTransactionList(RunPaymentsApp.currUserId);
	    	}
	    }

 else if (txn.getTrnxsrc() == TransactionSource.WALLET) {
        System.out.println("Select the destination type (WALLET/BANKACCOUNT):");
        try {
            String destType = sc.next();
            TransactionDestination dest = TransactionDestination.valueOf(destType);
            txn.setTrnxDest(dest);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return;
        }

        if (txn.getTrnxDest() == TransactionDestination.WALLET) {
           
           
            System.out.println("Enter recipient's user ID:");
            int recipientId = sc.nextInt();
            for (User user : users) {
            if (user.getUserId() == recipientId) {
            addMoneyToWallet(RunPaymentsApp.currUserId, -amount);
           addMoneyToWallet(recipientId, amount);
	        
	        System.out.println("Transaction successful!");
	
	        transactionlist(amount, txn.getTrnxsrc(), txn.getTrnxDest());
         // Create transaction object and set properties
       
            }
            
            
                }
        }
        else if (txn.getTrnxDest() == TransactionDestination.BANKACCOUNT) {
   		 int recipientId = sc.nextInt();
	 long recipientacc=sc.nextLong();
   		  for (User user : users) {
   	            if (user.getUserId() == recipientId) {
   		 addMoneyToWallet(RunPaymentsApp.currUserId, -amount);
   		 addMoneyBank(recipientacc,amount);
         transactionlist(amount, txn.getTrnxsrc(), txn.getTrnxDest());
   	            }
   		  }
   	 }
        
            }
 else if(txn.getTrnxsrc() == TransactionSource.BANKACCOUNT)
 {
	 if (txn.getTrnxDest() == TransactionDestination.BANKACCOUNT) {
   		 int recipientId = sc.nextInt();
	 long recipientacc=sc.nextLong();
   		  for (User user : users) {
   	            if (user.getUserId() == recipientId) {
   		 addMoneyToWallet(RunPaymentsApp.currUserId, -amount);
   		 addMoneyBank(recipientacc,amount);
         transactionlist(amount, txn.getTrnxsrc(), txn.getTrnxDest());
   	            }
   		  }
 }
	 
 }         
        
	 
}
}

	    
	

    






        
       


