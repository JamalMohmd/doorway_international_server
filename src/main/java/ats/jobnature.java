package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class naturelist
{
	public String location;
	public String desc;
	
	naturelist(String loc,String desc)
	{
		this.location=loc;
		this.desc=desc;
	}
}

@Path("/jobnature")
public class jobnature {
	
	List<naturelist> llist=new ArrayList<naturelist>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<naturelist> getl()
	{
		String qry="select * from jobnature";
		ResultSet r;
		
		llist.clear();
		database d=new database();
		d.connect();
		r=d.execute(qry);
		try 
		{
			while(r.next())
			{
				llist.add(new naturelist(r.getString(1),r.getString(2)));
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
