package newPackage;
import java.util.*;
import java.util.Scanner;
import newPackage.Account;

public class Transaction {

	private int transactionID;
	private long accountNum;
	private double amount;
	private int choice;
	private String type;
	private int code;
	private GregorianCalendar transactionDate = new GregorianCalendar();
	
	
	
	public int getTransactionID() {
		return transactionID;
	}


	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	
	public long getAccountNum() {
		return accountNum;
	}


	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}
	
	
	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}
	

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	public GregorianCalendar getTransactionDate() {
		return transactionDate;
	}
	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public void setTransactionDate(GregorianCalendar transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public int getChoice() {
		
		return choice;
	}

	public String getChoicetype(int choice) {
		String returner;
		switch (choice) 
		{
		
			case 1 :
				returner= "CHECK";
							
			break;
			
			case 2 :returner ="DEBIT CARD";
			break;
			
			case 3 :returner= "DEPOSIT";
			break;
				
			case 4 : returner= "WITHDRAWAL";
			break;
			
			default : returner= null;	
		}		
			
		return returner;	
	}

	
	
	public void setChoice(int choice) {
		this.choice = choice;
	}

	public void NewTransaction(long accountnum)
	{
		Scanner keyboard = new Scanner(System.in);
		int escape = 1;
		setAccountNum(accountnum);
		System.out.println("Enter 1 for Check\nEnter2 for Debit Card\nEnter 3 for Deposit\nEnter 4 for Withdrawal");
		
		do{
			int userChoice = keyboard.nextInt();
			setChoice(userChoice);
			escape=1;
			if((getChoice()<0||getChoice()>4))
					{
				System.out.println("Invalid Transaction Choice! Try Again!");
				escape = 0;
					}
			setType(getChoicetype(userChoice));
		}while(escape==0);
		
		try
		{		
		System.out.println("Enter the amount");
		setAmount(keyboard.nextDouble());
		System.out.println("Enter the Transaction year (yyyy)");
		int yyyy = keyboard.nextInt();
		System.out.println("Enter the Transaction month (mm)");
		int mm = keyboard.nextInt();
		System.out.println("Enter the Transaction day (dd)");
		int dd = keyboard.nextInt();
		GregorianCalendar temporaryDate= new GregorianCalendar(yyyy,mm,dd);
		setTransactionDate(temporaryDate);
		} catch(InputMismatchException e){System.out.println("Invalid Datatypes Entered");}
	
		
	}
	
	public Transaction()
	{
		
		
		setAmount(0);
		setChoice(0);
		setCode(0);
		setAccountNum(0);
		setTransactionID(0);
		setTransactionDate(null);
			
	}


	


	
}
