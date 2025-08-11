package ats;

import basefile.*;
import database.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.security.*;

class loginstatus
{
	public String status;
	public String remarks;
	public String type;
	public String initial;
	public String key;
	public List<menulist> mlist=new ArrayList<menulist>();
	
	loginstatus(String status,String remarks,String type,String initial,String key, List<menulist> mlist1)
	{
		this.status=status;
		this.remarks=remarks;
		this.type=type;
		this.initial=initial;
		this.key=key;
		this.mlist=mlist1;
	}
	
	
	public static class menulist{
		public String option;
		public String path;
		public String label;
		public String showInSidebar;
		public String icon;
		
		menulist()
		{
			
		}
		
		menulist(String option,String path,String label,String showinsidebar,String icon)
		{
			this.option=option;
			this.path=path;
			this.label=label;
			this.showInSidebar=showinsidebar;
			this.icon=icon;
		}
	}

}



@Path("/signin")
public class signin {
	
	loginstatus loginstat;
	private static final SecureRandom rand=new SecureRandom();
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int length = 25;
    List<loginstatus.menulist> mlist1=new ArrayList<loginstatus.menulist>();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public loginstatus userlogincheck(login_data l) {

    	String type="";
    	String initial="";
    	int z=0;
    	int i=0;
    	database d=new database();
		ResultSet r;
		d.connect();
		
		String qry="select count(*) from user where userid='"+l.getUser()+"' and password='"+l.getPass()+"'";
	
		r=d.execute(qry);
		
		try {

			while(r.next())
			{
				i=r.getInt(1);
			}
			
			if(i==1)
			{
				qry="select usertype,initial from user where userid='"+l.getUser()+"' and password='"+l.getPass()+"'";
				r=d.execute(qry);
				while(r.next())
				{
					type=r.getString(1);
					initial=r.getString(2);
				}
				
				StringBuilder sb = new StringBuilder(length);
		        for (int j = 0; j < length; j++) {
		            int index = rand.nextInt(characters.length());
		            sb.append(characters.charAt(index));
		        }
		        
		        qry="select count(*) from login_key where userid='"+l.getUser()+"'";
		        r=d.execute(qry);
		        
		        if(r.next())
		        {
		        	z=r.getInt(1);
		        }
		        if(z==1)
		        {
		        	mlist1.add(new loginstatus.menulist("NA","NA","NA","NA","NA"));
		        	loginstat=new loginstatus("FAIL","Aleady account active","NA","NA","NA",mlist1);
		        }
		        else
		        {
		        	if(!initial.equals("X"))
		        	{
		        		qry="insert into login_key values('"+l.getUser()+"','"+sb.toString()+"',sysdate())";
		        		d.executeInsert(qry);
		        	}
		        	
		        	qry="select activity,path,label,showInSidebar,icon from menu where type='"+type+"'";
		        	r=d.execute(qry);
		        	while(r.next())
		        	{
		        		mlist1.add(new loginstatus.menulist(r.getString(1),r.getString(2),r.getString(3),r.getString(4),r.getString(5)));
		        	}
		        	
		        	loginstat=new loginstatus("OK","SUCCESS",type,initial,sb.toString(),mlist1);
		        } 
			}
			else
			{
				mlist1.add(new loginstatus.menulist("NA","NA","NA","NA","NA"));
				loginstat=new loginstatus("FAIL","INVALID USER/PASSWORD","NA","NA","NA",mlist1);
			}
			
		}
		catch(Exception e) {
			loginstat=new loginstatus("FAIL",e.toString(),"NA","NA","NA",mlist1);
		}
		
		d.close();
        return loginstat;
    }
}
