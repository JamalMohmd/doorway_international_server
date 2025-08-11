package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.*;

class state_type{
	public String ccode;
	public String scode;
	public String desc;

	state_type(String ccode,String scode, String desc)
	{
		this.ccode=ccode;
		this.scode=scode;
		this.desc=desc;
	}
}


@Path("/state")
public class state {

	List<state_type> userlist = new ArrayList<state_type>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<state_type> stype() {
    	database d=new database();
		ResultSet r;
		d.connect();
		r=d.execute("select * from state");
		
		try {

			userlist.clear();	
			while(r.next())
			{
				userlist.add(new state_type(r.getString(1),r.getString(2),r.getString(3)));
			}
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		d.close();
        return userlist;
    }
}
