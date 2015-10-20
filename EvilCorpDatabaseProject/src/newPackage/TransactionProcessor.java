package newPackage;
import java.util.*;

import newPackage.Account;
import newPackage.Transaction;
//import newPackage.TransactionHistory;


public class TransactionProcessor {
	private Account returnAccount;
	
	public Account getReturnAccount() {
		return returnAccount;
	}
	
	public void setReturnAccount(Account returnAccount) {
		this.returnAccount = returnAccount;
	}
	
	
	public Transaction[] sort(Transaction[] T,int length)
	{
		
		for(int i = 0; i< length ; i ++ )
		{
			for(int j = 0; j < i; j++)
			{
				if(T[j].getTransactionDate().compareTo(T[i].getTransactionDate())>0)
				{
					Transaction swapper = T[j];
					T[j] = T[i];
					T[i] = swapper;
				}
					
			}
			
		}
		
		return T;
		
	}
	public Transaction[] evilSort(Transaction[] Tr,int length)
	{
		
		for(int i = 0; i< length - 1 ; i++ )
		{
			for(int j = i+1; j <length; j++)
			{

				if( Tr[i].getAmount()> Tr[j].getAmount())
				{
					Transaction swapper = Tr[j];
					Tr[j] = Tr[i];
					Tr[i] = swapper;
				}
					
			}
			
		}
		
		return Tr;
		
	}
	
public TransactionProcessor(Account A, Transaction[] T,int transactionCounter)
{
	Scanner keyboard = new Scanner(System.in);
	
	System.out.println("Are we being Evil? Enter 1 for Yes and 2 for No ");
	int evil = keyboard.nextInt();
	Transaction[] sortedTransaction;
	double balance;
	int overdrawCounter =0;
	balance = A.getAccountBalance();
	int length = transactionCounter;
	if (evil == 1)
		{
		 sortedTransaction = evilSort(T, transactionCounter);
		}
		
	
	else
		{
		 sortedTransaction = sort(T,transactionCounter);
		}
	
		for(int i = 0; i<length; i++)
			{
				if(sortedTransaction[i].getChoice() ==1 ||sortedTransaction[i].getChoice() ==2||sortedTransaction[i].getChoice() ==4)
					{
						balance = balance - sortedTransaction[i].getAmount();
							if(balance<0)
								overdrawCounter++;
			
					}
		else
			balance = balance+sortedTransaction[i].getAmount();
		
		
			}
	
	balance = balance - overdrawCounter*35;
	System.out.println("Number of overDraws = " + overdrawCounter);
	A.setAccountBalance(balance);
	System.out.println("All transactions Processed - The current balance is "+ balance);
	
	setReturnAccount(A);
}




}
