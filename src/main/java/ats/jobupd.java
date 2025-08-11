package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

@Path("/jobupdate")
public class jobupd {
	jobstat job;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public jobstat updl(jobupdate j)
	{
		
		String qry="";
		int i=0;
		ResultSet r;
		database d=new database();
		d.connect();
		qry="select count(*) from login_key where userid='"+j.getUserid()+"' and keyvalue='"+j.getKey()+"'";
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
			if(i==1)
			{
				qry="update job set title='"+j.getTitle()+"',employer='"+j.getEmployer()+"',jobnature='"+j.getJobnature()+"',joblocation='"+j.getJoblocation()+"',indtype='"+j.getIndtype()+"',description='"+j.getDescription()+"',qualification='"+j.getQualification()+"',experience='"+j.getExperience()+"',skills='"+j.getSkills()+"',age="+String.valueOf(j.getAge())+",status='"+j.getStatus()+"',posteddate=date('"+j.getPosteddate()+"'),deadlinedate=date('"+j.getDeadline()+"'),vacancies="+String.valueOf(j.getVacancy())+",modifiedby='"+j.getUserid()+"',modifiedon=sysdate() where code='"+j.getCode()+"';";
				
				i=d.executeUpdate(qry);
				if(i==0)
				{
					job=new jobstat("ERROR","Job update failed");
				}
				else
				{
					job=new jobstat("OK","Job updated successfully");
				}
			}
			else
			{
				job=new jobstat("ERROR","Invalid key");
			}
		}
		catch(Exception e)
		{
			job=new jobstat("ERROR",e.toString());
		}
		
		d.close();
		return job;
	}
}
