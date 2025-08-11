package ats;

/*
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;
import basefile.*;

class menulist{
	public String type;
	public String option;
	public String path;
	public String label;
	public String showInSidebar;
	public String icon;
	
	
	menulist(String type,String option,String path,String label,String showinsidebar,String icon)
	{
		this.type=type;
		this.option=option;
		this.path=path;
		this.label=label;
		this.showInSidebar=showinsidebar;
		this.icon=icon;
	}
}

@Path("/menu")
public class menu extends Application{
	
	private static final List<menulist> mlist=new ArrayList<menulist>();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<menulist> getmenu(usertype u)
	{
		
		mlist.clear();
		ResultSet r;
		String qry="";
		database d=new database();
		d.connect();
		
		qry="select type,activity,path,label,showInSidebar,icon from menu where type='"+u.getType()+"'";
		
		System.out.println(qry);
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				mlist.add(new menulist(u.getType(),r.getString(2),r.getString(3),r.getString(4),r.getString(5),r.getString(6)));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		d.close();
		return mlist;
	}
} */
