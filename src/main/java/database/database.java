package database;


import java.sql.*;
import java.io.*;

public class database {
	Connection connection = null;
	Statement statement;
	Statement statement1;
	ResultSet resultSet;
	
	public void connect()
	{

		BufferedReader read;
		String line;
		String ip=null;
		String user=null;
		String pass=null;
		String s[];
		
		try 
		{
			FileReader f=new FileReader("/sample.txt");
			read=new BufferedReader(f);
			while((line=read.readLine())!=null)
			{
				if(line.contains("db"))
				{
					s=line.split(":",2);
					ip="jdbc:mysql:";
					ip=ip.concat(s[1]);
				}
				else if(line.contains("user"))
				{
					s=line.split(":");
					user=s[1];
				}
				else if(line.contains("pass"))
				{
					s=line.split(":");
					pass=s[1];
				}
			}
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ip,user, pass);
			statement = connection.createStatement();
            
		}
		catch(Exception e)
		{
			System.out.println(e.toString()+"test");
		}
	}
	
	public ResultSet execute(String qry)
	{
	//	String s[][]=new String[100][100];
	//	int i=0;
		try
		{
			resultSet = statement.executeQuery(qry);
	/*		while (resultSet.next()) 
			{
				s[i][0] = resultSet.getString("customer_id");
				s[i][1] = resultSet.getString("customer_name").trim();
				i++;
			} */
		}
		catch(Exception e)
		{
			System.out.println(e.toString()+"test0");
		}
		return resultSet;
	}
	
	public int executeInsert(String qry)
	{
		int i=0;
		try {
			i=statement.executeUpdate(qry);
		}
		catch(Exception e) {
			System.out.println(e.toString()+"test1");
		}
		
		return i;
	}
	
	public int executeDelete(String qry)
	{
		int i=0;
		try {
			
			i=statement.executeUpdate(qry);
		}
		catch(Exception e) {
			System.out.println(e.toString()+"test2");
		}
		
		return i;
	}
	
	public int executeUpdate(String qry)
	{
		int i=0;
		try {
			i=statement.executeUpdate(qry);
		}
		catch(Exception e) {
			System.out.println(e.toString()+"test3");
		}
		
		return i;
	}
	public void close()
	{
		try {
	        connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString()+"test4");
		}
	}

}
