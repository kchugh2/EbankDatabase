package newPackage;
import java.sql.SQLException;
import java.util.*;

import newPackage.Account;
import newPackage.Transaction;
import newPackage.databaseMethods;
import newPackage.TransactionProcessor;
public class BankingApp {
	Scanner keyboard = new Scanner(System.in);
	/*static HashMap < Long, ArrayList<Transaction>> transmap = new HashMap<Long, ArrayList<Transaction>>();
	public void transactionHistoryStorer(Account A, Transaction T)
	{
		
	}*/
	
	
	//static databaseMethods database;
	

		


public static void main(String[] args) throws SQLException
{
	databaseMethods database = new databaseMethods();
	//ArrayList<Account> accounts = new ArrayList<Account>();
	Scanner keyboard = new Scanner(System.in);
	Transaction[] allTransactions = new Transaction[10];
	//ArrayList<TransactionHistory> history = new ArrayList<TransactionHistory>();
	int accEscape = 1,tEscape=1,alltEscape=1,transactionCounter=0,accountBuilderEscape=1,addTransactionIndex=0;
	int i =0, l = 0,closeAcc,cont;
	int accountCounter=0,transactionsWanted=1;
	long closeAccNum;
	long accnumber = 0 ;
	boolean accountNumberMatch=false,accountRemovedFlag;
	
	System.out.println("Press 1 to create new account! \nPress 0 to continue without creating a new account");
	accEscape = keyboard.nextInt();
	while(accEscape!=0)
	{
		
		System.out.println("Create new account");
		Account acc = new Account(2);
		
		database.createAccount(acc);
		
		/*do
			{
			Account a = new Account(2); 
			for (l=0; l <accountCounter; l++)
					{
					if (accounts.get(l).getAccountNumber()==a.getAccountNumber())
							accountNumberMatch = true;
					else
						accountNumberMatch =false; 
					
					}
			if(accountNumberMatch==false)
			{
				accounts.add(a);
				accountCounter++;
				accountBuilderEscape =1;
			}
			else
			{
				System.out.println("New Accounts must have unique Account Number! Match Found! Re-enter Details!\n");
				accountBuilderEscape = 0;
			}
			
			}while(accountBuilderEscape==0);*/
		
		System.out.println("Press 1 to add more Accounts ! \nPress 0 to if done adding accounts");
		accEscape = keyboard.nextInt();
		
	}
	System.out.println("Press 1 to perform transactions today. \nPress 0 to escape");
	transactionsWanted = keyboard.nextInt();
	while(transactionsWanted!=0&&alltEscape!=0)
	{
		
		do
		{
				
		Account transactionAccount = new Account();
		
			
		do
			{
			System.out.println("enter an account number associated with the transactions");
			accnumber = keyboard.nextInt();
			transactionAccount = database.pullAccounts(accnumber);
			if (transactionAccount.getAccountNumber()==0)
				System.out.println("Account Not Found Reenter Account Detals");
			
			}while(transactionAccount.getAccountNumber()==0);
			
		addTransactionIndex=0;
		
		do
		{
			
			
			System.out.println("Enter Transaction no  " +(addTransactionIndex+1) + " MAX TEN!");
			Transaction transact = new Transaction();
			transact.NewTransaction(accnumber);
			allTransactions[addTransactionIndex]=transact;
			
			database.pushTransaction(transact);
			
		
			System.out.println("Press 1 to add more transactions to this account ! \nPress 0 to if done adding transactions");
			tEscape = keyboard.nextInt();
			transactionCounter++;
			
			addTransactionIndex++;
		}while(tEscape!=0&&i<10);
	
		
	//Account transactionProces = new Account();
	TransactionProcessor transactionProcess = new TransactionProcessor(transactionAccount, allTransactions ,transactionCounter);
	
	database.pushAccount(transactionProcess.getReturnAccount());
	transactionCounter=0;
	Transaction clear = new Transaction();
	
	for(i=0;i<addTransactionIndex;i++)
		allTransactions[i]=clear;
	
		
	System.out.println("Press 1 to enter Transactions for a different account and 0 to Exit");
	alltEscape = keyboard.nextInt();
	
		
		}while(alltEscape!=0);
	}
	/*
	 
	 do{
		cont=1;
		System.out.println("Name\tAccount Number\tAccount Balance");
		for (i = 0 ; i<accounts.size(); i++)
		{
			if(accounts.get(i).getAccountBalance()==0)
			{
				System.out.println(accounts.get(i).getName()+"\t"+accounts.get(i).getAccountNumber()+"\t\t"+accounts.get(i).getAccountBalance());	
			}
		}
		System.out.println("Do you wish to close accounts today ? The above accounts have a balance of 0 and can be closed. \nPress 1 to close accounts \nPress 2 to continue without closing");
		closeAcc = keyboard.nextInt();
		if(closeAcc==1)
		{
			System.out.println("Enter the account number to be closed");
			closeAccNum = keyboard.nextLong();
			accountRemovedFlag=false;
			for (i = 0 ; i<accounts.size(); i++)
			{
				if(accounts.get(i).getAccountBalance()==0&&accounts.get(i).getAccountNumber()==closeAccNum)
				{
					accountCounter--;
					accounts.remove(i);
					accountRemovedFlag=true;
				}
				
				
			}
			
			if(accountRemovedFlag)
			{
				System.out.println("Account Number "+closeAccNum+" has been terminated");
				System.out.println("Press 1 to close more accounts\nPress any other number if you are done closing accounts");
				cont=keyboard.nextInt();
			}
			
			else
				System.out.println("Entered account number cannot be terminated due to one of the following reasons \n1.Account does not exist\n2.Account balance is not 0. Try again!");
			
			
		}
		
	}while(closeAcc==1&&cont==1);
	
	
		
		/*
	System.out.println("Transaction History");

	TransactionHistory H;
	Account A;
	Transaction T;
	
	for(int i1 = 0; i1<history.size(); i1++)
	{
		H=history.get(i1);
		A=H.getAcc();
		T=H.getTrans();
		//System.out.println(T.getChoicetype());
		System.out.println("\nAcc no: "+A.getAccountNumber()+"\tTransaction amount "+T.getAmount()+"\tTransaction Type "+T.getChoicetype());
	
	}


}
*/
	Account checkAccount = new Account();
	long sender=-1, receiver =-1;
	int transfercont=1;
	
	System.out.println("Press 1 to make a transfer ? Press 0 to escape");
	transfercont = keyboard.nextInt();
	while(transfercont!=0)
	{
	
		if (transfercont == 0)
			break;
	
	
			do
			{

			keyboard.nextLine();
			System.out.println("Enter Account number to transfer FROM");
			sender = keyboard.nextLong();
			keyboard.nextLine();
			checkAccount = database.pullAccounts(sender);
			//System.out.println(checkAccount.getAccountNumber());
			if (checkAccount.getAccountNumber()==0)
			System.out.println("Account Not Found Reenter Account Details");		
			}   while(checkAccount.getAccountNumber()==0);
			
			do
			{
				keyboard.nextLine();
				System.out.println("Enter Account number to transfer TO");
				receiver = keyboard.nextLong();
				keyboard.nextLine();
				checkAccount = database.pullAccounts(receiver);
				if (checkAccount.getAccountNumber()==0)
				System.out.println("Account Not Found Reenter Account Detals");
	
			}while(checkAccount.getAccountNumber()==0);
		
	
	
	TransactionProcessor  tp = new TransactionProcessor();
	System.out.println("Enter the amount to be transferred");
	double amount = keyboard.nextDouble();
	keyboard.nextLine();
	tp.transfer(sender,receiver , amount);
	
	System.out.println("Press 1 to make more transfers \n Press 0 to escape");
	transfercont = keyboard.nextInt();
	}
	
	int tcont =0;
	do{
	System.out.println("Transaction History");
	System.out.println("Press 1 to view all transactions\tPress 2 to view transactions associated with a certain account\nPRess 0 to escape ");
	int tchoice= keyboard.nextInt();
	if(tchoice == 1 )
		{
		database.pullAllTransactions(); 
		tcont =1;
		}
	else if (tchoice == 2)
		{
		System.out.println("Enter the account number associated with transaction history to be viewed ");
		int acc = keyboard.nextInt();
		database.pullTransaction(acc);
		tcont=1;
		}
	else if (tchoice ==0)
		break;
	
	else 
		System.out.println("Invalid input ");
	}while(tcont==0)	;
	
	
	
}
}

