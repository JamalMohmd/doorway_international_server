package ats;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.time.LocalDate;

import database.*;
import basefile.*;

@Path("/jobget")
public class jobsingle {
	
	jobdetails job=new jobdetails();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public jobdetails getl(jobcode j)
	{
		
		String qry="";
		int i=0;
		database d=new database();
		ResultSet r;
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
				qry="select code,title,employer,jobnature,joblocation,indtype,description,qualification,experience,skills,age,status,posteddate,deadlinedate,vacancies from job where code='"+j.getCode()+"'";
				r=d.execute(qry);
				
				while(r.next())
				{
					job.setCode(r.getString(1));
					job.setTitle(r.getString(2));
					job.setEmployer(r.getString(3));
					job.setJobnature(r.getString(4));
					job.setJoblocation(r.getString(5));
					job.setIndtype(r.getString(6));
					job.setDescription(r.getString(7));
					job.setQualification(r.getString(8));
					job.setExperience(r.getString(9));
					job.setSkills(r.getString(10));
					job.setAge(r.getInt(11));
					job.setStatus(r.getString(12));
					job.setPosteddate(LocalDate.parse(r.getString(13)));
					job.setDeadline(LocalDate.parse(r.getString(14)));
					job.setVacancy(r.getInt(15));
				}
			}
			else
			{
				job.setCode("NA");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		d.close();
		return job;
	}

}
