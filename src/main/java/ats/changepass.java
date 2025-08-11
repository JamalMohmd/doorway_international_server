package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class changestatus{
	public String status;
	public String remarks;
	
	changestatus(String status,String remarks)
	{
		this.status=status;
		this.remarks=remarks;
	}
}
@Path("/changepassword")
public class changepass{
	
	changestatus slist;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public changestatus change(changepassword c)
	{
		database d=new database();
		String qry="";
		int i=0;
		ResultSet r;
		d.connect();
				
		try 
		{

				qry="select count(*) from user where userid='"+c.getUserid()+"' and password='"+c.getPassword()+"'";
				r=d.execute(qry);
				
				while(r.next())		
				{
					i=r.getInt(1);
				}
				
				if(i==1)
				{
					qry="update user set password='"+c.getNewpass()+"',initial=' ' where userid='"+c.getUserid()+"' and password='"+c.getPassword()+"'";
					
					i=d.executeUpdate(qry);
					
					qry="select count(*) from user where userid='"+c.getUserid()+"' and password='"+c.getNewpass()+"'";
					r=d.execute(qry);
					
					while(r.next())
					{
						i=r.getInt(1);
					}
					
					if(i==1)
					{
		/*				qry="delete from login_key where userid='"+c.getUserid()+"' and keyvalue='"+c.getKey()+"'";
						System.out.println(qry);
						i=d.executeDelete(qry);  */
						slist=new changestatus("OK","Password updated successfully");
					}
					else
					{
						slist=new changestatus("ERROR","Password updated failed");
					}
				}
				else
				{
					slist=new changestatus("ERROR","Password is incorrect");
				}
			
		}
		catch(Exception e)
		{
			slist=new changestatus("ERROR",e.toString());
		}
		
		return slist;
	}
}
