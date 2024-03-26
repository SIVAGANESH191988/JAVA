



import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class UserOperations {
	
	List<User> users = null;
	List<BankAccount> bankAcctList = null;
	  Map<Integer,Wallet> walletList = RunPaymentsApp.walletList;
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
	public void deleteBankAccount(int userId, long accountNumber) {
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

	public void addMoneyToWallet( double amount) {
		PaymentsAppDAO db=new PaymentsAppDAO();
		try {
			db.addMoneyToWallet(amount);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
		} 
	

	public double checkWalletBalance(int userId){
	    Wallet wallet = walletList.get(userId);
	    if (wallet != null) {
	        return wallet.getCurrntBal();
	    } else {
	        return 0;
	    }
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
	
	if(txn.getTrnxsrc() == TransactionSource.CASH)
	{
		 System.out.println("Enter amount:");
         double amount = sc.nextDouble();
         System.out.println("Enter recipient's user ID:");
         int recipientId = sc.nextInt();
         for (User user : users) {
             if (user.getUserId() == recipientId) {
                
                 
                     Wallet recipientWallet = walletList.get(recipientId);
                     recipientWallet.setCurrntBal(recipientWallet.getCurrntBal() + amount);
                     System.out.println("Transaction successful!");
                 } 
                 return;
             }
         
         System.out.println("Recipient not found.");
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
                    Wallet senderWallet = walletList.get(RunPaymentsApp.currUserId);
                    if (senderWallet.getCurrntBal() >= amount) {
                        senderWallet.setCurrntBal(senderWallet.getCurrntBal() - amount);
                        Wallet recipientWallet = walletList.get(recipientId);
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
            long recipientbankAccountNumber = sc.nextLong();
            for (User user : users) {
                if (user.getUserId() == RunPaymentsApp.currUserId) {
                    List<BankAccount> userBankAccounts = bankAcctList;
                    for (BankAccount account : userBankAccounts) {
                        if (account.getBankAcctNumber()==recipientbankAccountNumber) {
                            System.out.println("Enter amount:");
                            double amount = sc.nextDouble();
                            if (amount <= 0) {
                                System.out.println("Invalid amount.");
                                return;
                            }
                            Wallet senderWallet = walletList.get(RunPaymentsApp.currUserId);
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

	public BankAccount AddBankAcct(long accNo, String acctBankName, AcctType accty, String acctIFSCCode, int acctPin) {
		// TODO Auto-generated method stub
		BankAccount ba = new BankAccount();
		ba.setBankAcctNumber(accNo);
		ba.setBankAcctBankName(acctBankName);
		ba.setBankAcctAcctType(accty);
		ba.setBankAcctIFSC(acctIFSCCode);
		ba.setBankAcctPin(acctPin);
		return ba;
	
	}

}

        
       


