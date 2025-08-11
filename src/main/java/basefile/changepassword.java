package basefile;

public class changepassword {
	String userid;
	String password;
	String newpass;
	
	public void setUserid(String userid)
	{
		this.userid=userid;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public void setNewpass(String newpass)
	{
		this.newpass=newpass;
	}
	public String getUserid()
	{
		return this.userid;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getNewpass()
	{
		return this.newpass;
	}
}
