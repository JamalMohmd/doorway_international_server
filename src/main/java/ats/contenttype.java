package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import database.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

class ctype
{
	public String type;
	public String description;
	public String datereq;
	
	ctype(String type, String desc, String datereq)
	{
		this.type=type;
		this.description=desc;
		this.datereq=datereq;
	}
}

@Path("/content")
public class contenttype {
	
	List<ctype> clist=new ArrayList<ctype>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ctype> getl()
	{
		String qry="";
		ResultSet r;
		database d=new database();
		d.connect();
		
		qry="select * from contenttype";
		r=d.execute(qry);
		clist.clear();
		
		try
		{
			while(r.next())
			{
				clist.add(new ctype(r.getString(1),r.getString(2),r.getString(3)));
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		d.close();
		return clist;
	}
	

}
