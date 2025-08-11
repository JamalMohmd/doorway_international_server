package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

@Path("/jobstatupd")
public class jobclose {

	jobstat j;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public jobstat getj(jobcode job)
	{
		String qry="";
		int i=0;
		ResultSet r;
		
		database d=new database();
		d.connect();
		
		qry="select count(*) from login_key where userid='"+job.getUserid()+"' and keyvalue='"+job.getKey()+"'";
		r=d.execute(qry);
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
			if(i==1)
			{
				qry="update job set status='"+job.getStatus()+"' where code='"+job.getCode()+"'";
				
				i=d.executeInsert(qry);
				if(i==0)
				{
					j=new jobstat("ERROR","Job status update failed");
				}
				else
				{
					j=new jobstat("OK","Job status updated successfully");
				}
			}
			else
			{
				j=new jobstat("ERROR","Invalid key");
			}
		}
		catch(Exception e)
		{
			j=new jobstat("ERROR",e.toString());
		}
		d.close();
		return j;
	}
}
