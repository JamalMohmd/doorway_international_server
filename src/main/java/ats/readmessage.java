package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import database.*;
import basefile.*;

@Path("/readmessage")
public class readmessage {
	List<messagedata> m=new ArrayList<messagedata>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<messagedata> getrms(@QueryParam("userid") String userid, @QueryParam("key") String key, @QueryParam("code") String code)
	{
		m.clear();
		String qry="";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		int i=0;
		database d=new database();
		ResultSet r;
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
				qry="update message set status='READ',readdate=sysdate() where applicantcode='"+code+"' and receiver='"+userid+"' and status='SENT'";
				i=d.executeUpdate(qry);
				
				qry="select applicantcode,slno,sender,receiver,content_type,content,eventdate,sentdate,readdate from message where applicantcode='"+code+"' order by slno;";
				r=d.execute(qry);
				
				while(r.next())
				{
					m.add(new messagedata(r.getString(1),r.getInt(2),r.getString(3),r.getString(4),r.getString(5),r.getString(6),(r.getString(7)!=null && !r.getString(7).isEmpty())?LocalDateTime.parse(r.getString(7),formatter):null,LocalDateTime.parse(r.getString(8),formatter),(r.getString(9)!=null && !r.getString(9).isEmpty())?LocalDateTime.parse(r.getString(9),formatter):null));
				}  
			}
			else
			{
				m.add(new messagedata("ERROR",0,"","","","",LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now()));
			}
		}
		catch(Exception e)
		{
			m.add(new messagedata("ERROR",0,"","","",e.toString(),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now()));
		}
		
		d.close();
		return m;
	}

}
