package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class country_type{
	public String code;
	public String desc;

	country_type(String code, String desc)
	{
		this.code=code;
		this.desc=desc;
	}
}


@Path("/country")
public class country {

	private static List<country_type> userlist = new ArrayList<country_type>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<country_type> ctype() {
    	database d=new database();
		ResultSet r;
		d.connect();
		r=d.execute("select * from country");
		
		try {

			userlist.clear();	
			while(r.next())
			{
				userlist.add(new country_type(r.getString(1),r.getString(2)));
			}
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		d.close();
        return userlist;
    }
}
