package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

@Path("/agencyget")
public class agencysingle {
	
	agencydetails adet=new agencydetails();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public agencydetails retagency(agencycode a)
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
				qry="select code,name,address1,address2,city,state,country,pincode,email,mobilecode,mobileno from agency where code='"+a.getCode()+"'";
				r=d.execute(qry);
				
				while(r.next())
				{
					adet.setCode(r.getString(1));
					adet.setName(r.getString(2));
					adet.setAddr1(r.getString(3));
					adet.setAddr2(r.getString(4));
					adet.setCity(r.getString(5));
					adet.setState(r.getString(6));
					adet.setCountry(r.getString(7));
					adet.setPincode(r.getString(8));
					adet.setEmail(r.getString(9));
					adet.setMobcode(r.getString(10));
					adet.setMobile(r.getString(11));
				}
			}
			else
			{
				adet.setCode("NA");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		d.close();
		return adet;
	}
	

}
