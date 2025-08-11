package basefile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class applicantdetails {
	String code;
	String fullname;
	LocalDate dateofbirth;
	String gender;
	String mobilecode;
	String mobileno;
	String email;
	String address1;
	String address2;
	String city;
	String state;
	String country;
	String qualification;
	String university;
	String yearpassing;
	int workexp;
	String prevemp;
	String designation;
	String comments;
	List<doclist> dlist=new ArrayList<doclist>();
	String filepath;
	String agency;
	String userid;
	String key;
	
	public applicantdetails()
	{
		
	}
	public void setCode(String code)
	{
		this.code=code;
	}
	public String getCode()
	{
		return this.code;
	}
	public void setGender(String gender)
	{
		this.gender=gender;	
	}
	public String getGender()
	{
		return this.gender;
	}
	public void setAgency(String agency)
	{
		this.agency=agency;
	}
	public String getAgency()
	{
		return this.agency;
	}
	public void setFilepath(String filepath)
	{
		this.filepath=filepath;
	}
	public String getFilepath()
	{
		return this.filepath;
	}
	public void setFullname(String fullname)
	{
		this.fullname=fullname;
	}
	public String getFullname()
	{
		return this.fullname;
	}
	public void setDateofbirth(LocalDate d)
	{
		this.dateofbirth=d;
	}
	public LocalDate getDateofbirth()
	{
		return this.dateofbirth;
	}
	public void setMobilecode(String mobilecode)
	{
		this.mobilecode=mobilecode;
	}
	public String getMobilecode()
	{
		return this.mobilecode;
	}
	public void setMobileno(String mobileno)
	{
		this.mobileno=mobileno;
	}
	public String getMobileno()
	{
		return this.mobileno;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public String getEmail()
	{
		return this.email;
	}
	public void setAddress1(String address1)
	{
		this.address1=address1;
	}
	public String getAddress1()
	{
		return this.address1;
	}
	public void setAddress2(String address2)
	{
		this.address2=address2;
	}
	public String getAddress2()
	{
		return this.address2;
	}
	public void setCity(String city)
	{
		this.city=city;
	}
	public String getCity()
	{
		return this.city;
	}
	public void setState(String state)
	{
		this.state=state;
	}
	public String getState()
	{
		return this.state;
	}
	public void setCountry(String country)
	{
		this.country=country;
	}
	public String getCountry()
	{
		return this.country;
	}
	public void setQualification(String qualification)
	{
		this.qualification=qualification;
	}
	public String getQualification()
	{
		return this.qualification;
	}
	public void setUniversity(String university)
	{
		this.university=university;
	}
	public String getUniversity()
	{
		return this.university;
	}
	public void setYearpassing(String yearpassing)
	{
		this.yearpassing=yearpassing;
	}
	public String getYearpassing()
	{
		return this.yearpassing;
	}
	public void setWorkexp(int workexp)
	{
		this.workexp=workexp;
	}
	public int getWorkexp()
	{
		return this.workexp;
	}
	public void setPrevemp(String prevemp)
	{
		this.prevemp=prevemp;
	}
	public String getPrevemp()
	{
		return this.prevemp;
	}
	public void setDesignation(String designation)
	{
		this.designation=designation;
	}
	public String getDesignation()
	{
		return this.designation;
	}
	public void setComments(String comments)
	{
		this.comments=comments;
	}
	public String getComments()
	{
		return this.comments;
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

	public void setDlist(List<doclist> d)
	{
		this.dlist=d;
	}
	public List<doclist> getDlist()
	{
		return this.dlist;
	}
}
