package com.redhat.gss;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBSample {
 // JDBC driver name and database URL
 static  String JDBC_DRIVER = "oracle.jdbc.OracleDriver";  
 static  String DB_URL = "jdbc:oracle:thin:@//lab-jboss-db.gsslab.pnq.redhat.com:1521/orcl";

 //  Database credentials
 static  String USER = "gsstest";
 static  String PASS = "test1234";
 static  String timeout = "100";
 public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
 Connection conn = null;
 Statement stmt = null;
 ResultSet rs = null;
 Properties config = new Properties();
	InputStream in =  Class.forName("com.redhat.gss.DBSample").getClassLoader().getResourceAsStream("config.properties");
	if(in != null) {
		config.load(in);
		in.close();
	}else {
		throw new FileNotFoundException("property file config.properties not found in the classpath");
	}
	JDBC_DRIVER = config.getProperty("jdbcDriver");
	DB_URL = config.getProperty("url");
	USER = config.getProperty("username");
	PASS = config.getProperty("password");
	timeout = config.getProperty("oracle.jdbc.ReadTimeout");
	
	System.out.println("jdbc driver " + JDBC_DRIVER );
	System.out.println("url " + DB_URL);
	System.out.println("username " + USER);
	System.out.println("password " + PASS);
	System.out.println("oracle.jdbc.ReadTimeout " + timeout);
	
 Properties pros = new Properties();
// pros.setProperty("connectionProperties", "oracle.jdbc.ReadTimeout=1000");
 pros.setProperty("user", USER);
 pros.setProperty("password", PASS);
 pros.setProperty("oracle.jdbc.ReadTimeout", timeout);	

 try{
    //STEP 2: Register JDBC driver
	 Class c = Class.forName(JDBC_DRIVER);

    Driver d = (Driver)c.newInstance();
    DriverManager.registerDriver(d);
    
    
    
    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
//    conn = DriverManager.getConnection(DB_URL,USER,PASS);
    conn = DriverManager.getConnection(DB_URL,pros);
    //STEP 4: Execute a query
    System.out.println("Creating statement...");
    stmt = conn.createStatement();
    stmt.executeUpdate("create table dbconn_test(id int)");
    
//    String sql = "CREATE TABLE REGISTRATION " +
//            "(id INTEGER not NULL, " +
//            " first VARCHAR(255), " + 
//            " last VARCHAR(255), " + 
//            " age INTEGER, " + 
//            " PRIMARY KEY ( id ))";
    
    
    System.out.println("Create table success");
    stmt.execute("insert into dbconn_test values(100)");
    System.out.println("Insert table success");
    String sql;
    sql = "SELECT id FROM dbconn_test";
    rs = stmt.executeQuery(sql);

    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       int id  = rs.getInt("id");

       //Display values
       System.out.println("ID: " + id);

    }    
    stmt.execute("drop table dbconn_test");
    System.out.println("Drop table success");
 
    //STEP 6: Clean-up environment
    rs.close();
    stmt.close();
    conn.close();
 }catch(SQLException se){
    //Handle errors for JDBC
    se.printStackTrace();
 }catch(Exception e){
    //Handle errors for Class.forName
    e.printStackTrace();
 }finally{
     if (rs != null) {
	        rs.close();
	      }
    //finally block used to close resources
    try{
       if(stmt!=null)
          stmt.close();
    }catch(SQLException se2){
    }// nothing we can do
    try{
       if(conn!=null)
          conn.close();
    }catch(SQLException se){
       se.printStackTrace();
    }//end finally try
 }//end try
 System.out.println("Goodbye!");
}//end main	
}
