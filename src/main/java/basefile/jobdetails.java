package basefile;

import java.time.LocalDate;

public class jobdetails {
	String code;
	String title;
	String employer;
	String jobnature;
	String joblocation;
	String indtype;
	String description;
	String qualification;
	String experience;
	String skills;
	int age;
	String status;
	LocalDate posteddate;
	LocalDate deadline;
	int vacancy;
	
	public jobdetails()
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
	public void setTitle(String title)
	{
		this.title=title;
	}
	public String getTitle()
	{
		return this.title;
	}
	public void setEmployer(String employer)
	{
		this.employer=employer;
	}
	public String getEmployer()
	{
		return this.employer;
	}
	public void setJobnature(String jobnature)
	{
		this.jobnature=jobnature;
	}
	public String getJobnature()
	{
		return this.jobnature;
	}
	public void setJoblocation(String joblocation)
	{
		this.joblocation=joblocation;
	}
	public String getJoblocation()
	{
		return this.joblocation;
	}
	public void setIndtype(String indtype)
	{
		this.indtype=indtype;
	}
	public String getIndtype()
	{
		return this.indtype;
	}
	public void setDescription(String desc)
	{
		this.description=desc;
	}
	public String getDescription()
	{
		return this.description;
	}
	public void setQualification(String qual)
	{
		this.qualification=qual;
	}
	public String getQualification()
	{
		return this.qualification;
	}
	public void setExperience(String exp)
	{
		this.experience=exp;
	}
	public String getExperience()
	{
		return this.experience;
	}
	public void setSkills(String skill)
	{
		this.skills=skill;
	}
	public String getSkills()
	{
		return this.skills;
	}
	public void setAge(int age)
	{
		this.age=age;
	}
	public int getAge()
	{
		return this.age;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
	public String getStatus()
	{
		return this.status;
	}
	public void setVacancy(int vac)
	{
		this.vacancy=vac;
	}
	public int getVacancy()
	{
		return this.vacancy;
	}
	public void setPosteddate(LocalDate d)
	{
		this.posteddate=d;
	}
	public LocalDate getPosteddate()
	{
		return this.posteddate;
	}
	public void setDeadline(LocalDate d)
	{
		this.deadline=d;
	}
	public LocalDate getDeadline()
	{
		return this.deadline;
	}	
}
