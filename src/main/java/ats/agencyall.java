package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;
import basefile.*;

class agencyalldet
{
	public String code;
	public String name;
	public String state;
	public String country;
	
	agencyalldet(String code,String name,String state,String country)
	{
		this.code=code;
		this.name=name;
		this.state=state;
		this.country=country;
	}
}

@Path("/agencyall")
public class agencyall {
	
	List<agencyalldet> alist=new ArrayList<agencyalldet>();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<agencyalldet> disp(userkey u)
	{
		String qry="";
		int i=0;
		ResultSet r;
		alist.clear();
		
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
				qry="select code,name,state,country from agency";
				r=d.execute(qry);
				
				while(r.next())
				{
					alist.add(new agencyalldet(r.getString(1),r.getString(2),r.getString(3),r.getString(4)));
				}
			}
			else {
				alist.clear();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		d.close();
		return alist;
	}

}
