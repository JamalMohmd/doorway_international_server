package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class applicantstat
{
	public String code;
	public String remarks;
	
	applicantstat(String code,String remarks)
	{
		this.code=code;
		this.remarks=remarks;
	}
}

@Path("/applicant")
public class applicant {
	
	applicantstat astat;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public applicantstat setint(applicant_data a)
	{
		String qry="";
		int i=0;
		String z="";
		ResultSet r;
		database d=new database();
		d.connect();
		
		qry="select count(*) from login_key where userid='"+a.getUserid()+"' and keyvalue='"+a.getKey()+"'";
		r=d.execute(qry);
		
		try {
			while(r.next())
			{
				i=r.getInt(1);
			}
			
			if(i==1)
			{
				qry="select count(*) from applicant";
				r=d.execute(qry);
				while(r.next())
				{
					i=r.getInt(1);
				}
				
				i=i+1;
				z="FILE".concat(String.format("%06d", i));
				qry="insert into applicant(code,fullname,dateofbirth,gender,mobilecode,mobileno,email,address1,address2,city,state,country,qualification,university,yearpassing,workexp,prevemployer,designation,comments,filepath,agency,createdby,createdon,status) values('"+z+"','"+a.getFullname()+"',date('"+a.getDateofbirth().toString()+"'),'"+a.getGender()+"','"+a.getMobilecode()+"','"+a.getMobileno()+"','"+a.getEmail()+"','"+a.getAddress1()+"','"+a.getAddress2()+"','"+a.getCity()+"','"+a.getState()+"','"+a.getCountry()+"','"+a.getQualification()+"','"+a.getUniversity()+"','"+a.getYearpassing()+"',"+a.getWorkexp()+",'"+a.getPrevemp()+"','"+a.getDesignation()+"','"+a.getComments()+"','"+a.getFilepath()+"','"+a.getAgency()+"','"+a.getUserid()+"',sysdate(),'DRAFT');";
				i=d.executeInsert(qry);
				if(i==0)
				{
					astat=new applicantstat("ERROR","File creation failed");
				}
				else
				{
					for(doclist dd : a.getDlist())
					{
						qry="insert into applicantdoc(code,type) values ('"+z+"','"+dd.getDoc()+"');";
						i=d.executeInsert(qry);
					}
					astat=new applicantstat(z,"File created successfully");
				}
			}
			else
			{
				astat=new applicantstat("ERROR","Invalid Key");
			}
			
		}
		catch(Exception e)
		{
			astat=new applicantstat("ERROR",e.toString());
		}
		
		d.close();
		return astat;
	}

}
