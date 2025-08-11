package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class sstatus
{
	public String status;
	public String remarks;
	
	sstatus(String status,String remarks)
	{
		this.status=status;
		this.remarks=remarks;
	}
}
@Path("/signout")
public class signout{
	
	sstatus slist;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public sstatus sout(user u)
	{
		
		String qry="";
		int i=0;
		ResultSet r;
		database d=new database();
		d.connect();
		
		qry="select count(*) from login_key where userid='"+u.getUserid()+"' and keyvalue='"+u.getKey()+"'";
		
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
			
			if(i==1)
			{
				qry="delete from login_key where userid='"+u.getUserid()+"' and keyvalue='"+u.getKey()+"'";
				i=d.executeDelete(qry);
				if(i!=0)
				{
					slist=new sstatus("OK","Sign out completed succesfully");
				}
				else
				{
					slist=new sstatus("ERROR","Sign out failed");
				}
			}
			else
			{
				slist=new sstatus("ERROR","Invalid Key");
			}
		}
		catch(Exception e)
		{
			slist=new sstatus("ERROR",e.toString());
		}
		
		d.close();
		return slist;
	}
}
