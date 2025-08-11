package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;
import basefile.*;

class empalldet
{
	public String code;
	public String name;
	public String person;
	public String indtype;
	
	empalldet(String code,String name,String person,String indtype)
	{
		this.code=code;
		this.name=name;
		this.person=person;
		this.indtype=indtype;
	}
}

@Path("/employerall")
public class employerall {
	
	List<empalldet> alist=new ArrayList<empalldet>();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<empalldet> disp(userkey u)
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
				qry="select EmployerCode,CompanyName,ContactPerson,IndustryType from employer";
				r=d.execute(qry);
				
				while(r.next())
				{
					alist.add(new empalldet(r.getString(1),r.getString(2),r.getString(3),r.getString(4)));
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
