package ats;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.ResultSet;

import database.*;
import basefile.*;

class msgstat
{
	public String status;
	public String remarks;
	
	msgstat(String status, String remarks)
	{
		this.status=status;
		this.remarks=remarks;
	}
}

@Path("/compose")
public class createmessage {

	msgstat m;
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public msgstat sendmsg(message msg)
	{
		String qry="";
		int i=0;
		String agency="";
		String receiver="";
		ResultSet r;
		database d=new database();
		d.connect();
		
		qry="select count(*) from login_key where userid='"+msg.getUserid()+"' and keyvalue='"+msg.getKey()+"'";
		r=d.execute(qry);
		
		try
		{
			while(r.next())
			{
				i=r.getInt(1);
			}
			if(i==1)
			{
				qry="select max(slno) from message where applicantcode='"+msg.getCode()+"'";
				r=d.execute(qry);
				
				while(r.next())
				{
					i=r.getInt(1);
				}
				i=i+1;
				
				qry="select agency from applicant where code='"+msg.getCode()+"'";
				r=d.execute(qry);
				while(r.next())
				{
					agency=r.getString(1);
				}
				if(agency.equals(msg.getUserid()))
				{
					receiver="admin";
				}
				else
				{
					receiver=agency;
				}
				
				qry="insert into message (applicantcode,slno,sender,receiver,content_type,content,eventdate,sentdate,status) values('"+msg.getCode()+"',"+i+",'"+msg.getUserid()+"','"+receiver+"','"+msg.getContent_type()+"','"+msg.getContent()+"','"+msg.getEventdate().toString()+"',sysdate(),'SENT');";
				i=d.executeInsert(qry);
				if(i==1)
				{
					m=new msgstat("OK","Message sent successfully");
				}
				else
				{
					m=new msgstat("ERROR","Message sending failed");
				}
			}
			else
			{
				m=new msgstat("ERROR","Invalid key");
			}
				
		}
		catch(Exception e)
		{
			m=new msgstat("ERROR",e.toString());
		}
		
		d.close();
		return m;
	}
}
