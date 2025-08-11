package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class User_type{
	public String type;
	public String desc;

	User_type(String type, String desc)
	{
		this.type=type;
		this.desc=desc;
	}
}

@Path("/logintype")
public class Logintype extends Application{
	
	@Override
	public java.util.Set<Class<?>> getClasses() {
	    java.util.Set<Class<?>> s = new java.util.HashSet<>();
	    s.add(Logintype.class);     // Your resource class
	    s.add(CORSFilter.class);    // Register the CORS filter
	    return s;
	}

	private static List<User_type> userlist = new ArrayList<User_type>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User_type> ltype() {
    	database d=new database();
		ResultSet r;
		d.connect();
		r=d.execute("select * from user_type");
		
		try {

			userlist.clear();	
			while(r.next())
			{
				userlist.add(new User_type(r.getString(1),r.getString(2)));
			}
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		d.close();
        return userlist;
    }
}
