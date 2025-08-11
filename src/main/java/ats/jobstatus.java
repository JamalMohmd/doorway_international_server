package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class statuslist
{
	public String status;
	public String desc;
	
	statuslist(String status,String desc)
	{
		this.status=status;
		this.desc=desc;
	}
}

@Path("/jobstatus")
public class jobstatus {
	
	List<statuslist> slist=new ArrayList<statuslist>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<statuslist> getl()
	{
		slist.clear();
		String qry="select * from jobstatus";
		ResultSet r;
		database d=new database();
		d.connect();
		
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				slist.add(new statuslist(r.getString(1),r.getString(2)));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		d.close();
		return slist;
	}

}
