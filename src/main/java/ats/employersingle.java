package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

@Path("/employerget")
public class employersingle {
	
	employerdetails adet=new employerdetails();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public employerdetails retagency(employercode a)
	{
		String qry="";
		int i=0;
		database d=new database();
		ResultSet r;
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
				qry="select EmployerCode,CompanyName,ContactPerson,ContactEmail,ContactPhone,IndustryType,Address,City,State,Country,Pincode from employer where EmployerCode='"+a.getCode()+"'";
				r=d.execute(qry);
				
				while(r.next())
				{
					adet.setCode(r.getString(1));
					adet.setCname(r.getString(2));
					adet.setCperson(r.getString(3));
					adet.setEmail(r.getString(4));
					adet.setPhone(r.getString(5));
					adet.setIndtype(r.getString(6));
					adet.setAddress(r.getString(7));
					adet.setCity(r.getString(8));
					adet.setState(r.getString(9));
					adet.setCountry(r.getString(10));
					adet.setPincode(r.getString(11));
				}
			}
			else
			{
				adet.setCode("Invalid key");
			}
			
		}
		catch(Exception e)
		{
			adet.setCode(e.toString());
		}
		
		d.close();
		return adet;
	}
	

}
