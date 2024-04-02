

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class UserOperations {
	
	List<User> users = null;
	List<BankAccount> bankAcctList = null;
	  private static Map<Integer, Wallet> userWallets = new HashMap<>();
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
		    wallet.setUserId(u.getUserId()); // Associate wallet with the user
		    
		    u.setWallet(wallet);

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
	public void deleteBankAccount(int userId, String accountNumber) {
	    for (User user : users) {
	        if (user.getUserId() == userId) {
	            List<BankAccount> userBankAccounts = user.getBaList();
	            for (BankAccount account : userBankAccounts) {
	                if (account.getBankAcctNumber().equals(accountNumber)) {
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
	    Wallet wallet = userWallets.getOrDefault(userId, new Wallet());
	    wallet.setLimit(50000);
	    if (wallet.getCurrntBal() + amount <= wallet.getLimit()) {
	        wallet.setCurrntBal(wallet.getCurrntBal() + amount);
	        userWallets.put(userId, wallet);
	        System.out.println("New wallet balance for user " + userId + " is: " + wallet.getCurrntBal());
	    } else {
	        System.out.println("Maximum wallet amount is " + wallet.getLimit());
	    }
	}

	public double checkWalletBalance(int userId){
	    Wallet wallet = userWallets.getOrDefault(userId, new Wallet());
	    return wallet.getCurrntBal();
	}

	public void DoTransaction() {
	    Scanner sc = new Scanner(System.in);
	    Transaction txn = new Transaction();
	    
	    // Prompt user to select the source of the transaction
	    System.out.println("Select the option to send money from which account (CASH/WALLET): ");
	    try {
	        String srcType = sc.next();
	        TransactionSource src = TransactionSource.valueOf(srcType);
	        txn.setTrnxsrc(src);
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        return;
	    }

	    // Process transaction based on the selected source
	    if (txn.getTrnxsrc() == TransactionSource.CASH) {
	        // Handle transaction from cash
	        // Code for cash transaction
	    } else if (txn.getTrnxsrc() == TransactionSource.WALLET) {
	        // Handle transaction from wallet
	    	
	        System.out.println("Enter amount:");
	        
	        double amount = sc.nextDouble();
	        int recipientId = sc.nextInt();
            for (User user : users) {
                if (user.getUserId() == recipientId) {
	     
	        
	        // Update sender's wallet balance
	        addMoneyToWallet(RunPaymentsApp.currUserId, -amount);
	        
	        // Update recipient's wallet balance
	        addMoneyToWallet(recipientId, amount);
	        
	        System.out.println("Transaction successful!");
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
            System.out.println("Enter amount:");
            double amount = sc.nextDouble();
            System.out.println("Enter recipient's user ID:");
            int recipientId = sc.nextInt();
            for (User user : users) {
                if (user.getUserId() == recipientId) {
                    Wallet senderWallet = userWallets.get(RunPaymentsApp.currUserId);
                    if (senderWallet.getCurrntBal() >= amount) {
                        senderWallet.setCurrntBal(senderWallet.getCurrntBal() - amount);
                        Wallet recipientWallet = userWallets.get(recipientId);
                        recipientWallet.setCurrntBal(recipientWallet.getCurrntBal() + amount);
                        System.out.println("Transaction successful!");
                    } else {
                        System.out.println("Insufficient balance in wallet.");
                    }
                    return;
                }
            }
            System.out.println("Recipient not found.");
        } else if (txn.getTrnxDest() == TransactionDestination.BANKACCOUNT) {
            System.out.println("Enter recipient's bank account number:");
            String recipientBankAccountNumber = sc.next();
            for (User user : users) {
                if (user.getUserId() == RunPaymentsApp.currUserId) {
                    List<BankAccount> userBankAccounts = user.getBaList();
                    for (BankAccount account : userBankAccounts) {
                        if (account.getBankAcctNumber().equals(recipientBankAccountNumber)) {
                            System.out.println("Enter amount:");
                            double amount = sc.nextDouble();
                            if (amount <= 0) {
                                System.out.println("Invalid amount.");
                                return;
                            }
                            Wallet senderWallet = userWallets.get(RunPaymentsApp.currUserId);
                            if (senderWallet.getCurrntBal() >= amount) {
                                senderWallet.setCurrntBal(senderWallet.getCurrntBal() - amount);
                                account.setBalance(account.getBalance() + amount); // Increase balance in bank account
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



        
       


