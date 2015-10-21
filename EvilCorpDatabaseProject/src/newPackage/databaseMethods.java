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
        //System.out.println(sql);
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
        if (result.isBeforeFirst()==true)
        {
            
        //result.beforeFirst();
        while(result.next()){
            acc.setName(result.getString("a_name"));
            acc.setAccountNumber(result.getInt("a_number"));
            acc.setAccountBalance(result.getDouble("a_balance"));
            acc.setSsn(result.getString("a_ssn"));
            System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getDouble(4)); 
        }
        }
        else
        {
        	
            	Account ab = new Account();
            	acc=ab;
            	
            
        }
        conn.close();
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
        //System.out.println("INSERT INTO ACCOUNTS(a_number, a_name, a_ssn, a_balance) VALUES("+"seq_account.nextval"+", '"+name+"','"+soc+"',"+bal+")");
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
        conn.close();
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
        conn.close();
	}
	
	
	
	public void pullTransaction(int account_number)throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql =("select * from transactions, transaction_description where t_account =" + (account_number)+" AND transactions.t_type = transaction_description.td_type");

        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
        
        
        while(result.next()){
            
        	System.out.println(result.getInt(1)+"\t"+result.getInt(2)+"\t"+result.getInt(3)+"\t"+result.getInt(4)+"\t"+result.getDate(5)+"\t"+result.getInt(6)+"\t"+result.getString(7));
        	/*tran.setAccountNum(result.getLong("t_account"));
        	tran.setTransactionID(result.getInt("t_ID"));
            tran.setCode(result.getInt("t_type"));
            tran.setAmount(result.getDouble("t_amount"));
            Date tdate = result.getDate("t_date");
            GregorianCalendar formatDate = new GregorianCalendar(tdate.getYear(),tdate.getMonth(),tdate.getDate());
            tran.setTransactionDate(formatDate);
            tran.setType(getDescription(tran.getCode()));*/
        }
        conn.close();
		
		
	}
	
	public void pullAllTransactions()throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);

        String sql =("select * from transactions, transaction_description where transactions.t_type = transaction_description.td_type");

        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        ResultSet result = preStatement.executeQuery();
        
        
        while(result.next()){
            
        	System.out.println(result.getInt(1)+"\t"+result.getInt(2)+"\t"+result.getInt(3)+"\t"+result.getInt(4)+"\t"+result.getDate(5)+"\t"+result.getInt(6)+"\t"+result.getString(7));
        	/*tran.setAccountNum(result.getLong("t_account"));
        	tran.setTransactionID(result.getInt("t_ID"));
            tran.setCode(result.getInt("t_type"));
            tran.setAmount(result.getDouble("t_amount"));
            Date tdate = result.getDate("t_date");
            GregorianCalendar formatDate = new GregorianCalendar(tdate.getYear(),tdate.getMonth(),tdate.getDate());
            tran.setTransactionDate(formatDate);
            tran.setType(getDescription(tran.getCode()));*/
        }
        conn.close();
		
		
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
        conn.close();
        while(result.next()){
        	description = result.getString(1);
        }
        conn.close();
		return description;
	}
	
	public int getCode(String description)throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        int code=0;
        
       
        	
        
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
        	code = result.getInt(1);
        }
        if(code == 0)
        	System.out.println("Error in transaction type please restart application");
        
        
        conn.close();
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
        String sql = "";
        long num = t.getAccountNum();
        int id = t.getTransactionID();
        double amt = t.getAmount();
        String description = t.getChoicetype();
        int type = getCode(description);
        long account = t.getAccountNum();
        Calendar d = t.getTransactionDate();
        
        String day=t.getStrDate();
        sql =("INSERT INTO TRANSACTIONS(t_amount, t_type, t_id, t_account, t_date) VALUES("+amt+","+type+","+"seq_transaction.nextval"+","+account+",'"+day+"')");
        //}catch (Exception e){System.out.println(e.getMessage());}
        
        
        //System.out.println(sql);
        /*while(result.next()){
            acc.setName(result.getString("a_name"));
            acc.setAccountNumber(result.getInt("a_number"));
            acc.setAccountBalance(result.getDouble("a_balance"));
            acc.setSsn(result.getString("a_ssn"));
        }*/
        
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
        

        preStatement.executeQuery();
        //conn.close();
	}
	
	public static void main (String[] args) {
		databaseMethods amethod = new databaseMethods();
		//Transaction tranx = new Transaction();
		//tranx.NewTransaction(1);
		try {
			amethod.pullTransaction(1);
			//System.out.println("Transaction amount: " + tranx.getAmount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
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
