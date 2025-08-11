package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class empstatus{
	public String code;
	public String remarks;
	empstatus(String code,String remarks)
	{
		this.code=code;
		this.remarks=remarks;
	}
}

@Path("/employer")
public class employer{
	
	empstatus empstat;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public empstatus stype(employerdata e)
	{
		
		String qry="";
		int i=0;
		String z="";
		ResultSet r;
		database d=new database();
		d.connect();
		
		qry="select count(*) from login_key where userid='"+e.getUserid()+"' and keyvalue='"+e.getKey()+"'";
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
			if(i==1)
			{
				qry="select count(*) from employer";
				r=d.execute(qry);
				
				while(r.next())
				{
					i=r.getInt(1);
				}
				i=i+1;
				z="E".concat(String.format("%09d", i));
				
				qry="insert into ats.employer (EmployerCode,CompanyName,ContactPerson,ContactEmail,ContactPhone,IndustryType,Address,City,State,Country,Pincode,RegistrationDate,Status,userid) values('";
				qry=qry.concat(z).concat("','").concat(e.getCname()).concat("','").concat(e.getCperson()).concat("','").concat(e.getEmail()).concat("','").concat(e.getPhone()).concat("','").concat(e.getIndtype()).concat("','").concat(e.getAddress()).concat("','").concat(e.getCity()).concat("','").concat(e.getState()).concat("','").concat(e.getCountry()).concat("','").concat(e.getPincode()).concat("',sysdate(),'X','").concat(e.getUserid()).concat("')");
								
				i=d.executeInsert(qry);
				
				if(i==0)
				{
					empstat=new empstatus("NA","Employer not created");
				}
				else
				{
					empstat=new empstatus(z,"Employer created successfully");
				}
			}
			else
			{
				empstat=new empstatus("ERROR","Invalid Key");
			}
			
		}
		catch(Exception ee)
		{
			empstat=new empstatus("ERROR",ee.toString());
		}
		return empstat;
	}

}
