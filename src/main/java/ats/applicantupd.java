package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;


@Path("/applicantupd")
public class applicantupd {
	
	updstat u;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public updstat getval(applicantdetails a)
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
				qry="update applicant set fullname='"+a.getFullname()+"',dateofbirth=date('"+a.getDateofbirth()+"'),gender='"+a.getGender()+"',mobilecode='"+a.getMobilecode()+"',mobileno='"+a.getMobileno()+"',email='"+a.getEmail()+"',address1='"+a.getAddress1()+"',address2='"+a.getAddress2()+"',city='"+a.getCity()+"',state='"+a.getState()+"',country='"+a.getCountry()+"',qualification='"+a.getQualification()+"',university='"+a.getUniversity()+"',yearpassing='"+a.getYearpassing()+"',workexp="+a.getWorkexp()+",prevemployer='"+a.getPrevemp()+"',designation='"+a.getDesignation()+"',comments='"+a.getComments()+"',filepath='"+a.getFilepath()+"',agency='"+a.getAgency()+"',modifiedby='"+a.getUserid()+"',modifiedon=sysdate() where code='"+a.getCode()+"';";
				System.out.println(qry);
				i=d.executeUpdate(qry);
				if(i==0)
				{
					u=new updstat("ERROR","Update failed");
				}
				else
				{
					qry="delete from applicantdoc where code='"+a.getCode()+"'";
					i=d.executeDelete(qry);
					
					for(doclist dd : a.getDlist())
					{
						qry="insert into applicantdoc(code,type) values ('"+a.getCode()+"','"+dd.getDoc()+"');";
						i=d.executeInsert(qry);
					}
					u=new updstat("OK","Updated successfully");
				}
			}
			else
			{
				u=new updstat("ERROR","Invalid key");
			}
			
		}
		catch(Exception e)
		{
			u=new updstat("ERROR",e.toString());
		}
		d.close();
		return u;
	}
}
