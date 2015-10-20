package newPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import newPackage.Account;

public class databaseMethods {
	private Account acc = new Account();
	public Account pullAccounts(int account_number)throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql =("select * from accounts where a_number = "+(account_number));

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
        String name = a.getName();
        double bal = a.getAccountBalance();
        String soc = a.getSsn();
        
        String sql =("INSERT INTO ACCOUNT(a_number, a_name, a_ssn, a_balance) VALUES("+num+","+name+","+ ","+soc+","+bal+")");
       
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
	
	
	private Transaction tran = new Transaction();
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
        	tran.setTransactionID(result.getString("t_ID"));
            tran.setType(result.getString("t_type"));
            tran.setAmount(result.getDouble("t_amount"));
            tran.setTransactionDate(result.getDate("t_date"));
        }
		return tran;
	}

}
