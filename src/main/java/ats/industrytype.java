package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class industry
{
	public String type;
	public String desc;
	
	industry(String type, String desc)
	{
		this.type=type;
		this.desc=desc;
	}
}

@Path("/industrytype")
public class industrytype{
	
	private static final List<industry> ilist=new ArrayList<industry>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<industry> itype()
	{
		
		ilist.clear();
		
		ResultSet r;
		String qry;
		database d=new database();
		d.connect();
		
		qry="select * from industrytype";
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				ilist.add(new industry(r.getString(1),r.getString(2)));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		return ilist;
	}
}
