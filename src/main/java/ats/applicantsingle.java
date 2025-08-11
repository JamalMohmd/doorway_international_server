package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.*;
import basefile.*;

@Path("/applicantget")
public class applicantsingle {

	applicantdetails adet=new applicantdetails();
	List<doclist> dlist=new ArrayList<doclist>();
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public applicantdetails getdet(@QueryParam("code") String code, @QueryParam("userid") String userid, @QueryParam("key") String key)
	{
		String qry="";
		int i=0;
		ResultSet r;
		database d=new database();
		d.connect();
		
		qry="select count(*) from login_key where userid='"+userid+"' and keyvalue='"+key+"'";
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
			if(i==1)
			{
				qry="select code,fullname,dateofbirth,gender,mobilecode,mobileno,email,address1,address2,city,state,country,qualification,university,yearpassing,workexp,prevemployer,designation,comments,filepath,agency from applicant where code='"+code+"';";
				r=d.execute(qry);
				
				System.out.println(qry);
				
				while(r.next())
				{
					adet.setCode(r.getString(1));
					adet.setFullname(r.getString(2));
					adet.setDateofbirth(LocalDate.parse(r.getString(3)));
					adet.setGender(r.getString(4));
					adet.setMobilecode(r.getString(5));
					adet.setMobileno(r.getString(6));
					adet.setEmail(r.getString(7));
					adet.setAddress1(r.getString(8));
					adet.setAddress2(r.getString(9));
					adet.setCity(r.getString(10));
					adet.setState(r.getString(11));
					adet.setCountry(r.getString(12));
					adet.setQualification(r.getString(13));
					adet.setUniversity(r.getString(14));
					adet.setYearpassing(r.getString(15));
					adet.setWorkexp(r.getInt(16));
					adet.setPrevemp(r.getString(17));
					adet.setDesignation(r.getString(18));
					adet.setComments(r.getString(19));
					adet.setFilepath(r.getString(20));
					adet.setAgency(r.getString(21));
				}
				
				qry="select type from applicantdoc where code='"+code+"';";
				r=d.execute(qry);
				
				dlist.clear();
				while(r.next())
				{
					dlist.add(new doclist(r.getString(1)));
				}
				
				adet.setDlist(dlist);
			}
			else
			{
				adet.setCode("NA");
			}
		}
		catch(Exception e)
		{
			
		}
		
		d.close();
		return adet;
	}
}
