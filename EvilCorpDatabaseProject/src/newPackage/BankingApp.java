package newPackage;
import java.util.*;

import newPackage.Account;
import newPackage.Transaction;
import newPackage.TransactionHistory;
import newPackage.TransactionProcessor;
public class BankingApp {
	Scanner keyboard = new Scanner(System.in);
	/*static HashMap < Long, ArrayList<Transaction>> transmap = new HashMap<Long, ArrayList<Transaction>>();
	public void transactionHistoryStorer(Account A, Transaction T)
	{
		
	}*/
	
	

		


public static void main(String[] args)
{
	ArrayList<Account> accounts = new ArrayList<Account>();
	Scanner keyboard = new Scanner(System.in);
	Transaction[] allTransactions = new Transaction[10];
	ArrayList<TransactionHistory> history = new ArrayList<TransactionHistory>();
	int accEscape = 1,tEscape=1,alltEscape=1,transactionCounter=0,accountBuilderEscape=1,addTransactionIndex=0;
	int i =0, l = 0,closeAcc,cont;
	int accountCounter=0;
	long closeAccNum;
	boolean accountNumberMatch=false,accountRemovedFlag;
	do
	{
		
		System.out.println("Create new account");
		do
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
			
			}while(accountBuilderEscape==0);
		System.out.println("Press 1 to add more Accounts ! \nPress 0 to if done adding accounts");
		accEscape = keyboard.nextInt();
		
	}while(accEscape!=0);
	
	do
	{
		
		int accountIndex=-1;
		System.out.println("Name\tAccount Number\tAccount Balance");
		for(int k = 0; k<accounts.size();k++)
		{
			System.out.println(accounts.get(k).getName()+"\t"+accounts.get(k).getAccountNumber()+"\t\t"+accounts.get(k).getAccountBalance());
			
		}
		
		do
		{
			System.out.println("Enter the account number associated with the Transactions to be entered");
			long accnumber = keyboard.nextLong();
			for(int j = 0; j< accounts.size(); j++)
			{
			Account finder = accounts.get(j);
			if(finder.getAccountNumber()==accnumber)
				accountIndex=j;
			}
		if (accountIndex==-1)
		System.out.println("Account not found! Try again!");
		}while(accountIndex == -1);
		
		addTransactionIndex=0;
		do
		{
			
			
			System.out.println("Enter Transaction no  " + (addTransactionIndex+1) + " MAX TEN!");
			Transaction transact = new Transaction();
			allTransactions[addTransactionIndex]=transact;
			
			
			TransactionHistory temp = new TransactionHistory(accounts.get(accountIndex),allTransactions[addTransactionIndex]);
			history.add(temp);
			
			System.out.println("Press 1 to add more transactions to this account ! \nPress 0 to if done adding transactions");
			tEscape = keyboard.nextInt();
			transactionCounter++;
			
			addTransactionIndex++;
		}while(tEscape!=0&&i<10);
	
		
	//Account transactionProces = new Account();
	TransactionProcessor transactionProcess = new TransactionProcessor(accounts.get(accountIndex),allTransactions,transactionCounter);
	accounts.remove(accountIndex);
	accounts.add(transactionProcess.getReturnAccount());
	transactionCounter=0;
	Transaction clear = new Transaction(2);
	
	for(i=0;i<addTransactionIndex;i++)
		allTransactions[i]=clear;
	
		
	System.out.println("Press 1 to enter Transactions for a different account and 0 to Exit");
	alltEscape = keyboard.nextInt();
	}while(alltEscape!=0);
	
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
}
