package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class city_type{
	public String ccode;
	public String scode;
	public String city;
	public String desc;

	city_type(String ccode,String scode, String city, String desc)
	{
		this.ccode=ccode;
		this.scode=scode;
		this.city=city;
		this.desc=desc;
	}
}


@Path("/city")
public class city {

	private static List<city_type> userlist = new ArrayList<city_type>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<city_type> citytype() {
    	database d=new database();
		ResultSet r;
		d.connect();
		r=d.execute("select * from city");
		
		try {

			userlist.clear();	
			while(r.next())
			{
				userlist.add(new city_type(r.getString(1),r.getString(2),r.getString(3),r.getString(4)));
			}
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		d.close();
        return userlist;
    }
}
