package basefile;

import java.time.LocalDateTime;

public class messagedata {
	String code;
	int slno;
	String sender;
	String receiver;
	String content_type;
	String content;
	LocalDateTime eventdate;
	LocalDateTime sentdate;
	LocalDateTime readdate;
	
	public messagedata()
	{
		
	}
	public messagedata(String code,int slno, String sender, String receiver, String content_type, String content, LocalDateTime e, LocalDateTime s, LocalDateTime r)
	{
		this.code=code;
		this.slno=slno;
		this.sender=sender;
		this.receiver=receiver;
		this.content_type=content_type;
		this.content=content;
		this.eventdate=e;
		this.sentdate=s;
		this.readdate=r;
	}
	public void setSlno(int slno)
	{
		this.slno=slno;
	}
	public int getSlno()
	{
		return this.slno;
	}
	public void setCode(String code)
	{
		this.code=code;
	}
	public String getCode()
	{
		return this.code;
	}
	public void setSender(String sender)
	{
		this.sender=sender;
	}
	public String getSender()
	{
		return this.sender;
	}
	public void setReceiver(String receiver)
	{
		this.receiver=receiver;
	}
	public String getReceiver()
	{
		return this.receiver;
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
	public void setSentdate(LocalDateTime d)
	{
		this.sentdate=d;
	}
	public LocalDateTime getSentdate()
	{
		return this.sentdate;
	}
	public void setReaddate(LocalDateTime d)
	{
		this.readdate=d;
	}
	public LocalDateTime getReaddate()
	{
		return this.readdate;
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
