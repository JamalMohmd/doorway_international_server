package basefile;

import java.time.LocalDateTime;

public class message {
	String code;
	String content_type;
	String content;
	LocalDateTime eventdate;
	String agency;
	String userid;
	String key;
	
	public void setAgency(String agency)
	{
		this.agency=agency;
	}
	public String getAgency()
	{
		return this.agency;
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}
	public String getUserid()
	{
		return this.userid;
	}
	public void setKey(String key)
	{
		this.key=key;
	}
	public String getKey()
	{
		return this.key;
	}
	public void setCode(String code)
	{
		this.code=code;
	}
	public String getCode()
	{
		return this.code;
	}
	public void setContent_type(String type)
	{
		this.content_type=type;
	}
	public String getContent_type()
	{
		return this.content_type;
	}
	public void setContent(String type)
	{
		this.content=type;
	}
	public String getContent()
	{
		return this.content;
	}
	public void setEventdate(LocalDateTime d)
	{
		this.eventdate=d;
	}
	public LocalDateTime getEventdate()
	{
		return this.eventdate;
	}
}
