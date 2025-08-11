package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.*;

class applfinal
{
	public int count;
	public List<applistall> alist=new ArrayList<applistall>();
	
	public applfinal(int count,List<applistall> a)
	{
		this.count=count;
		this.alist=a;
	}
	
}

class applistall
{
	public String file;
	public String fullname;
	public LocalDate createdon;
	public String status;
	public int msgcount;
	
	applistall()
	{
		
	}
	applistall(String file,String fullname,LocalDate createdon, String status, int msgcount)
	{
		this.file=file;
		this.fullname=fullname;
		this.createdon=createdon;
		this.status=status;
		this.msgcount=msgcount;
	}
	
}

@Path("/applicantall")
public class applicantall {
	
	applfinal f;
	List<applistall> alist=new ArrayList<applistall>();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public applfinal getapplist(@QueryParam("userid") String userid, @QueryParam("key") String key, @QueryParam("agency") String agency, @QueryParam("records") int records, @QueryParam("page") int page)
	{
		String qry="";
		int i=0;
		int temp=0;
		int count=0;
		database d=new database();
		ResultSet r;
		d.connect();
		alist.clear();
		
		qry="select count(*) from login_key where userid='"+userid+"' and keyvalue='"+key+"'";
		System.out.println(qry);
		r=d.execute(qry);
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
			if(i==1)
			{
				temp=records * ( page - 1);
				qry="select code,fullname,date(createdon),status,msgcount(code,'"+userid+"') mcount from applicant where agency='"+agency+"' limit "+records+" offset "+temp;
				System.out.println(qry);
				r=d.execute(qry);
				while(r.next())
				{
					alist.add(new applistall(r.getString(1),r.getString(2),LocalDate.parse(r.getString(3)),r.getString(4),r.getInt(5)));
				}
				
				qry="select count(*) from applicant where agency='"+agency+"'";
				r=d.execute(qry);
				
				while(r.next())
				{
					count=r.getInt(1);
				}
			}
			else
			{
				alist.add(new applistall("","Invalid key",LocalDate.now(),"Error",0));
			}
		}
		catch(Exception e)
		{
			alist.add(new applistall("",e.toString(),LocalDate.now(),"Error",0));
		}
		
		f=new applfinal(count,alist);
		
		d.close();
		return f;
	}
}
