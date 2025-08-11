package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class genderdet
{
	public String code;
	public String desc;
	
	genderdet(String code,String desc)
	{
		this.code=code;
		this.desc=desc;
	}
}

@Path("/gender")
public class gender {
	
	List<genderdet> glist=new ArrayList<genderdet>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<genderdet> getg()
	{
		glist.clear();
		String qry="select * from gender";
		ResultSet r;
		database d=new database();
		d.connect();
		
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				glist.add(new genderdet(r.getString(1),r.getString(2)));
			}
		}
		catch(Exception e)
		{
			glist.add(new genderdet("ERROR",e.toString()));
		}
		d.close();
		return glist;
	}
}
