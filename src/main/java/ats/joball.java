package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;
import basefile.*;

class jall
{
	public String code;
	public String title;
	public String employer;
	public String status;
	
	jall(String code,String title,String employer,String status)
	{
		this.code=code;
		this.title=title;
		this.employer=employer;
		this.status=status;
	}
}

@Path("/joball")
public class joball {
	
	List<jall> jlist=new ArrayList<jall>();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<jall> getl(userkey u)
	{
		
		String qry="";
		ResultSet r;
		int i=0;
		jlist.clear();
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
				qry="select code,title,employer,status from job";
				r=d.execute(qry);
				
				while(r.next())
				{
					jlist.add(new jall(r.getString(1),r.getString(2),r.getString(3),r.getString(4)));
				}
			}
			else {
				jlist.clear();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		d.close();
		return jlist;
	}

}
