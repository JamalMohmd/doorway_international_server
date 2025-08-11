package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class jstat
{
	public String code;
	public String remarks;
	
	jstat(String code,String remarks)
	{
		this.code=code;
		this.remarks=remarks;
	}
}

@Path("/job")
public class job {

	
	jstat j;
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public jstat processjob(jobdata job)
	{
		String qry="";
		int i=0;
		String z="";
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
				
				qry="select count(*) from job";
				r=d.execute(qry);
				
				while(r.next())
				{
					i=r.getInt(1);
				}
				i=i+1;
				z="JOB".concat(String.format("%07d", i));
				
				qry="insert into job (code,title,employer,jobnature,joblocation,indtype,description,qualification,experience,skills,age,status,posteddate,deadlinedate,vacancies,createdby,createdon) values('";
				qry=qry.concat(z).concat("','").concat(job.getTitle()).concat("','");
				qry=qry.concat(job.getEmployer()).concat("','").concat(job.getJobnature()).concat("','").concat(job.getJoblocation()).concat("','").concat(job.getIndtype()).concat("','").concat(job.getDescription()).concat("','").concat(job.getQualification()).concat("','").concat(job.getExperience()).concat("','").concat(job.getSkills()).concat("',");
			    qry=qry.concat(String.valueOf(job.getAge())).concat(",'").concat(job.getStatus()).concat("',date('").concat(job.getPosteddate().toString()).concat("'),date('").concat(job.getDeadline().toString()).concat("'),'").concat(String.valueOf(job.getVacancy())).concat("','").concat(job.getUserid()).concat("',sysdate())");

				i=d.executeInsert(qry);
				if(i==0)
				{
					j=new jstat("ERROR","Job creation failed");
				}
				else
				{
					j=new jstat(z,"Job created successfully");
				}
				
			}
			else
			{
				j=new jstat("Error","Inalid Key");
			}
		}
		catch(Exception e)
		{
			j=new jstat("Error",e.toString());
		}
		
		d.close();
		return j;
	}
}
