

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class UserOperations {
	
	List<User> users = null;
	List<BankAccount> BankAccountList = null;
	Map<Integer, Wallet>UsersWallet  = RunPaymentsApp.UsersWallet;
	public UserOperations() {
		users= RunPaymentsApp.UsersList;
		BankAccountList = RunPaymentsApp.BAList;
	}
	
	public User doUserRegistration(String FirstName, String LastName, String Password, long PhoneNum, String DOB,String Address) throws Exception {
		User User = new User();
		User.setFirstName(FirstName);
		User.setLastName(LastName );
		User.setPhoneNum(PhoneNum);
		User.setDateOfBirth(DOB);
		User.setCommunicationAddr(Address);
		User.setPassword(Password);
		
		if((FirstName+LastName).length() >50) {
			throw new Exception();
		}
		
		User.setUserId((int)(Math.random()*1000)+100);
		PaymentsFileOps pfOps = new PaymentsFileOps();
		pfOps.writeUserToFile(User);
		return User;
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
	}
//		for(int i=0;i<users.size();i++) {
//			if(users.get(i).getUserId() != userId) {
//				System.out.println(users.get(i));
//				break;
//			}
//		}
	
	
public Map<User,List<BankAccount>> getUserBankAccounts() {
//		Map<User,BankAccount> userBankAcctMap = new HashMap<User,BankAccount>();
	
	   Map<User,List<BankAccount>> userBankAcctMap = new HashMap<User,List<BankAccount>>();
		
		for(User u:users) {
			if(users != null) {
				userBankAcctMap.put(u, u.getBankAccList());
			}
		}
		return userBankAcctMap;
		
	}
	public void DeletingBankAccount(int userId, String AccountNumber) {
	    for (User user : users) {
	        if (user.getUserId() == userId) {
	            List<BankAccount> userBankAccounts = user.getBankAccList();
	            for (BankAccount account : userBankAccounts) {
	                if (account.getBankAccountNumber().equals(AccountNumber)) {
	                    userBankAccounts.remove(account);
	                    System.out.println("Your Bank account deleted successfully.");
	                    return;
	                }
	            }
	            System.out.println("Bank account not found for the user.");
	            return;
	        }
	    }
	    System.out.println("User not found.");
	}


public void AddingMoneyToWallet(double amount) {
		    try {
		    	PaymentsBankingDAO DAO = new PaymentsBankingDAO();
				DAO.addingMoneyToWallet(amount);
		    }
		    catch (Exception e){
		    	e.printStackTrace();
		    }
	}
    
public double checkWalletBalance(){
   return UsersWallet.get(RunPaymentsApp.CurrentUserId).getCurrntBal();
   }

public void DoTransaction() {
	
    Scanner sc = new Scanner(System.in);
    Transaction txn = new Transaction();
    System.out.println("Select The Option to Send Money From Which Account (CASH / WALLET) : ");
    try {
        String srcType = sc.next();
        TransactionSource srccType = TransactionSource.valueOf(srcType);
        txn.setTrnxsrc(srccType);
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
        return;
    }

    if (txn.getTrnxsrc() == TransactionSource.CASH) {
    	System.out.println("Enter Amount :");
        double Amount = sc.nextDouble();
        System.out.println("Enter Recipient UserID :");
        int RUID = sc.nextInt();
        for (User user : users) 
        {
            if (user.getUserId() == RUID)
            {
            	Wallet RecipientWallet = UsersWallet.get(RUID);
            	RecipientWallet.setCurrntBal(RecipientWallet.getCurrntBal() + Amount);
            	System.out.println("Transaction Success !!!!");
            	  return;
            }
          
        }
        
        System.out.println("Recipient Has Not Found....");   
    } 
    
    else if (txn.getTrnxsrc() == TransactionSource.WALLET) {
        System.out.println("Enter Destination Type (WALLET / BANK ACCOUNT) :");
        try {
            String DesType = sc.next();
            TransactionDestination Dest = TransactionDestination.valueOf(DesType);
            txn.setTrnxDest(Dest);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (txn.getTrnxDest() == TransactionDestination.WALLET) {
            double amount = sc.nextDouble();
            int uId=sc.nextInt();
            for (User user : users) {
                if (user.getUserId() == uId) {
                    Wallet senderWallet = UsersWallet.get(RunPaymentsApp.CurrentUserId);
                    if (senderWallet.getCurrntBal() >= amount) {
                        senderWallet.setCurrntBal(senderWallet.getCurrntBal() - amount);
                        Wallet receiverWallet =  UsersWallet.get(uId);
                        receiverWallet.setCurrntBal(receiverWallet.getCurrntBal() + amount);
                        System.out.println("Transaction Success !!!!");
                        
                    } else {
                        System.out.println("Insufficient balance in wallet !!!!");
                    }
                    return;
                }
                
            }
            
        System.out.println("Recipient Has Not Found....");
    }
        else if (txn.getTrnxDest() == TransactionDestination.BANKACCOUNT) {
            System.out.println("Enter recipient's bank account number:");
            String recipientbankAccountNumber = sc.next();
            for (User user : users) {
                if (user.getUserId() == RunPaymentsApp.CurrentUserId) {
                    List<BankAccount> userBankAccounts = BankAccountList;
                    for (BankAccount account : userBankAccounts) {
                        if (account.getBankAccountNumber().equals(recipientbankAccountNumber)) {
                            System.out.println("Enter amount:");
                            double amount = sc.nextDouble();
                            if (amount <= 0) {
                                System.out.println("Invalid amount.");
                                return;
                            }
                            Wallet senderWallet = UsersWallet.get(RunPaymentsApp.CurrentUserId);
                            if (senderWallet.getCurrntBal() >= amount) {
                                senderWallet.setCurrntBal(senderWallet.getCurrntBal() - amount);
                                account.setBankBalance(account.getBankBalance() + amount); 
                                System.out.println("Transaction successful!");
                            } else {
                                System.out.println("Insufficient balance in wallet.");
                            }
                            return;
                        }
                    }
                    System.out.println("Recipient bank account not found.");
                    return;
                }
            }
            System.out.println("User not found.");
        }
  }
 }
}



       