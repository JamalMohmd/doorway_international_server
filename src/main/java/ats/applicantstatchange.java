package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class outstat
{
	public String status;
	public String remarks;
	
	public outstat(String status, String remarks)
	{
		this.status=status;
		this.remarks=remarks;
	}
}

@Path("/applicantstatupd")
public class applicantstatchange {
	
	outstat o;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public outstat getupd(applicantstatcode a)
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
				qry="update applicant set status='"+a.getStatus()+"' where code='"+a.getCode()+"';";
				i=d.executeUpdate(qry);
				if(i==0)
				{
					o=new outstat("ERROR","Status update failed");
				}
				else
				{
					o=new outstat("OK","Status changed successfully");
				}
			}
			else
			{
				o=new outstat("ERROR","Invalid key");
			}
		}
		catch(Exception e)
		{
			o=new outstat("ERROR",e.toString());
		}
		
		d.close();
		return o;
	}
}
