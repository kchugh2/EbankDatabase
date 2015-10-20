package newPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import newPackage.Account;

import java.util.Properties;


public class databaseMethods {
	private Account acc = new Account();
	private Transaction tran = new Transaction();
	
	public Account pullAccounts(long accnumber)throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql =("select * from accounts where a_number = "+(accnumber));
        System.out.println(sql);
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
      
        while(result.next()){
            acc.setName(result.getString("a_name"));
            acc.setAccountNumber(result.getInt("a_number"));
            acc.setAccountBalance(result.getDouble("a_balance"));
            acc.setSsn(result.getString("a_ssn"));
        }
		return acc;
	}
	
	
	public void createAccount(Account a) throws SQLException{
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost";
        
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
        
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);
        
        //long num = a.getAccountNumber();
        String name = a.getName();
        double bal = a.getAccountBalance();
        String soc = a.getSsn();
        System.out.println("INSERT INTO ACCOUNTS(a_number, a_name, a_ssn, a_balance) VALUES("+"seq_account.nextval"+", '"+name+"','"+soc+"',"+bal+")");
        String sql =("INSERT INTO ACCOUNTS(a_number, a_name, a_ssn, a_balance) VALUES("+"seq_account.nextval"+", '"+name+"','"+soc+"',"+bal+")");
       
        /*while(result.next()){
            acc.setName(result.getString("a_name"));
            acc.setAccountNumber(result.getInt("a_number"));
            acc.setAccountBalance(result.getDouble("a_balance"));
            acc.setSsn(result.getString("a_ssn"));
        }*/
        
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
        

        preStatement.executeQuery();
	}
	
	
	public void pushAccount(Account a) throws SQLException{
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost";
        
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
        
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);
        
        long num = a.getAccountNumber();
        double bal = a.getAccountBalance();
        
        String sql =("UPDATE ACCOUNTS SET a_balance = "+bal+"where a_number =" +num);
       
        /*while(result.next()){
            acc.setName(result.getString("a_name"));
            acc.setAccountNumber(result.getInt("a_number"));
            acc.setAccountBalance(result.getDouble("a_balance"));
            acc.setSsn(result.getString("a_ssn"));
        }*/
        
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
        

        preStatement.executeQuery();
	}
	
	
	
	public Transaction pullTransaction(int transaction_number, int account_number)throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql =("select * from transactions where t_id = "+(transaction_number) +"AND t_account =" + (account_number));

        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
        
        
        while(result.next()){
            tran.setAccountNum(result.getLong("t_account"));
        	tran.setTransactionID(result.getInt("t_ID"));
            tran.setCode(result.getInt("t_type"));
            tran.setAmount(result.getDouble("t_amount"));
            Date tdate = result.getDate("t_date");
            GregorianCalendar formatDate = new GregorianCalendar(tdate.getYear(),tdate.getMonth(),tdate.getDate());
            tran.setTransactionDate(formatDate);
            tran.setType(getDescription(tran.getCode()));
        }
		return tran;
	}
	
	public String getDescription(int code)throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        String description = "Error Description not found ";
        
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql =("select td_description from transaction_description where td_type = "+(code));

        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
      
        while(result.next()){
        	description = result.getString("td_description");
        }
		return description;
	}
	
	public int getCode(String description)throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        int code=0;
        
        do{
        	
        
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql =("select td_type from transaction_description where td_description = '"+(description)+"'");

        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
      
        while(result.next()){
        	code = result.getInt("td_description");
        }
        if(code == 0)
        	System.out.println("Error in transaction type please restart application");
        }while(code!=0);
		return code;
	}
	

	public void pushTransaction(Transaction t) throws SQLException{
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost";
        
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
        
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);
        
        long num = t.getAccountNum();
        //int id = t.getTransactionID();
        double amt = t.getAmount();
        String description = t.getType();
        int type = getCode(description);
        long account = t.getAccountNum();
        Calendar d = t.getTransactionDate();
        
        Date day=d.getTime();
       
        
        String sql =("INSERT INTO TRANSACTIONS(t_amount, a_type, t_id, t_account, t_date) VALUES("+amt+","+type+","+","+"seq_transaction.nextval"+","+account+","+day+")");
       
        /*while(result.next()){
            acc.setName(result.getString("a_name"));
            acc.setAccountNumber(result.getInt("a_number"));
            acc.setAccountBalance(result.getDouble("a_balance"));
            acc.setSsn(result.getString("a_ssn"));
        }*/
        
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
        

        preStatement.executeQuery();
	}
	
	public static void main (String[] args) {
		databaseMethods amethod = new databaseMethods();
		Transaction tranx = new Transaction();
		tranx.NewTransaction(1);
		try {
			amethod.pushTransaction(tranx);;
			System.out.println("Tansaction amount: " + tranx.getAmount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public void displayTransactions()throws SQLException{
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost";
        
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
        
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);
      
        String sql =("SELECT * FROM TRANSACTIONS");
       
        /*while(result.next()){
            acc.setName(result.getString("a_name"));
            acc.setAccountNumber(result.getInt("a_number"));
            acc.setAccountBalance(result.getDouble("a_balance"));
            acc.setSsn(result.getString("a_ssn"));
        }*/
        
       /* //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
        
        ResultSet result = preStatement.executeQuery();
        
        while(result.next()){
            //System.out.println(result.getString(1)+"\t"+result.getString(2));
        }
        //System.out.println("done");
        int t;
        int num = 10;
        String soc = "112358";
        String name = "Kunal";
        double bal = 100.03;
        
        		
        String blah =("INSERT INTO ACCOUNT(a_number, a_name, a_ssn, a_balance) VALUES("+num+","+name+","+soc+","+bal+")");
        System.out.println(blah);
	}*/
}
