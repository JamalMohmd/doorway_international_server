package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class agencystatus{
	public String code;
	public String remarks;
	agencystatus(String code,String remarks)
	{
		this.code=code;
		this.remarks=remarks;
	}
}

@Path("/agency")
public class agency {
	
	agencystatus agencystat;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public agencystatus agency_insert(agency_data a)
	{
		String qry="";
		String z="";
		int i=0;
    	database d=new database();
		ResultSet r;
		ResultSet r1;
		d.connect();
		
		qry="select count(*) from login_key where userid='"+a.getUserid()+"' and keyvalue='"+a.getKey()+"'";
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
		}
		catch(Exception e)
		{
			agencystat=new agencystatus("ERROR",e.toString());
		}
		if(i==1)
		{
		qry="select count(*) from agency";
		r=d.execute(qry);
		try {
			while(r.next())
			{
				i=r.getInt(1);
			}
			i=i+1;
			z="A".concat(String.format("%09d", i));
			qry="insert into agency (code,name,address1,address2,city,state,country,pincode,email,mobilecode,mobileno,createdby,createdon) values('";
			qry=qry.concat(z).concat("','").concat(a.getName()).concat("','").concat(a.getAddr1()).concat("','").concat(a.getAddr2()).concat("','").concat(a.getCity()).concat("','").concat(a.getState()).concat("','").concat(a.getCountry()).concat("','").concat(a.getPincode()).concat("','").concat(a.getEmail()).concat("','").concat(a.getMobcode()).concat("','").concat(a.getMobile()).concat("','").concat(a.getUserid()).concat("',sysdate())");

    		d.executeInsert(qry);	
			
			qry="select count(*) from agency where code='"+z+"'";
			
			r1=d.execute(qry);
			i=0;
			while(r1.next())
			{
				i=r1.getInt(1);
			}
			if(i==1)
			{
				qry="insert into user values('";
				qry=qry.concat(z).concat("','first@123','AGENCY','X')");
				d.executeInsert(qry);
				
				agencystat=new agencystatus(z,"Agency created successfully");
			}
			else
			{
				agencystat=new agencystatus("ERROR","Agency not created");
			}
			
		}
		catch(Exception e)
		{
			agencystat=new agencystatus("ERROR",e.toString());
		}
	}
	else
	{
		agencystat=new agencystatus("ERROR","Invalid Key");
	}
				
		d.close();
	
		return agencystat;
	}
}
