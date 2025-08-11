package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class loclist
{
	public String location;
	public String desc;
	
	loclist(String loc,String desc)
	{
		this.location=loc;
		this.desc=desc;
	}
}

@Path("/location")
public class location {
	
	List<loclist> llist=new ArrayList<loclist>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<loclist> getl()
	{
		String qry="select * from joblocation";
		ResultSet r;
		
		llist.clear();
		database d=new database();
		d.connect();
		r=d.execute(qry);
		try 
		{
			while(r.next())
			{
				llist.add(new loclist(r.getString(1),r.getString(2)));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		d.close();
		return llist;
	}
	
}
