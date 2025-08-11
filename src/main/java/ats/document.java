package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class docdet
{
	public String type;
	public String desc;
	
	docdet(String code,String desc)
	{
		this.type=code;
		this.desc=desc;
	}
}

@Path("/document")
public class document {
	
	List<docdet> glist=new ArrayList<docdet>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<docdet> getg()
	{
		glist.clear();
		String qry="select * from document";
		ResultSet r;
		database d=new database();
		d.connect();
		
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				glist.add(new docdet(r.getString(1),r.getString(2)));
			}
		}
		catch(Exception e)
		{
			glist.add(new docdet("ERROR",e.toString()));
		}
		d.close();
		return glist;
	}
}
