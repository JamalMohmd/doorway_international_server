package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class updstat
{
	public String status;
	public String remarks;
	
	updstat(String status,String remarks)
	{
		this.status=status;
		this.remarks=remarks;
	}
}

@Path("/agencyupd")
public class agencyupd {
	
	updstat upd;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public updstat rtst(agencyupdate a)
	{
		String qry="";
		int i=0;
		ResultSet r;
		database d=new database();
		d.connect();
		qry="select count(*) from login_key where userid='"+a.getUserid()+"' and keyvalue='"+a.getKey()+"'";
		r=d.execute(qry);
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
			
			if(i==1)
			{
				qry="update ats.agency set name='"+a.getName()+"',address1='"+a.getAddr1()+"',address2='"+a.getAddr2()+"',city='"+a.getCity()+"',state='"+a.getState()+"',country='"+a.getCountry()+"',pincode='"+a.getPincode()+"',email='"+a.getEmail()+"',mobilecode='"+a.getMobcode()+"',mobileno='"+a.getMobile()+"',modifiedby='"+a.getUserid()+"',modifiedon=sysdate() where code='"+a.getCode()+"'";
				
				i=d.executeUpdate(qry);
				if(i==0)
				{
					upd=new updstat("ERROR","Update failed");
				}
				else
				{
					upd=new updstat("OK","Update completed successfully");
				}
			}
			else
			{
				upd=new updstat("ERROR","Invalid Key");
			}
		}
		catch(Exception e)
		{
			upd=new updstat("ERROR",e.toString());
		}
		
		return upd;
	}

}
