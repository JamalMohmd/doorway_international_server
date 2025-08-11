package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class empupdstat
{
	public String status;
	public String remarks;
	
	empupdstat(String status,String remarks)
	{
		this.status=status;
		this.remarks=remarks;
	}
}

@Path("/employerupd")
public class employerupd {
	
	empupdstat upd;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public empupdstat rtst(employerupdate a)
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
				qry="update employer set CompanyName='"+a.getCname()+"',ContactPerson='"+a.getCperson()+"',ContactEmail='"+a.getEmail()+"',ContactPhone='"+a.getPhone()+"',IndustryType='"+a.getIndtype()+"',Address='"+a.getAddress()+"',City='"+a.getCity()+"',State='"+a.getState()+"',Country='"+a.getCountry()+"',Pincode='"+a.getPincode()+"',modifiedby='"+a.getUserid()+"',modifiedon=sysdate() where EmployerCode='"+a.getCode()+"' ";
			//	System.out.println(qry);
				
				i=d.executeUpdate(qry);
				if(i==0)
				{
					upd=new empupdstat("ERROR","Update failed");
				}
				else
				{
					upd=new empupdstat("OK","Update completed successfully");
				}
			}
			else
			{
				upd=new empupdstat("ERROR","Invalid Key");
			}
		}
		catch(Exception e)
		{
			upd=new empupdstat("ERROR",e.toString());
		}
		
		return upd;
	}

}
