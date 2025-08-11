package ats;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/ats")  // Your API base path
public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(signin.class);
        classes.add(signout.class);
        classes.add(agency.class);
        classes.add(changepass.class);
        classes.add(country.class);
        classes.add(employer.class);
        classes.add(employerall.class);
        classes.add(employersingle.class);
        classes.add(employerupd.class);
        classes.add(agencyall.class);
        classes.add(agencysingle.class);
        classes.add(agencyupd.class);
        classes.add(city.class);
        classes.add(state.class);
        classes.add(location.class);
        classes.add(jobstatus.class);
        classes.add(industrytype.class);
        classes.add(job.class);
        classes.add(joball.class);
        classes.add(jobclose.class);
        classes.add(jobsingle.class);
        classes.add(jobupd.class);
        classes.add(jobnature.class);
        classes.add(gender.class);        
        classes.add(document.class);
        classes.add(applicant.class);
        classes.add(applicantall.class);
        classes.add(applicantalladmin.class);
        classes.add(applicantsingle.class);
        classes.add(applicantupd.class);
        classes.add(applicantstatchange.class);
        classes.add(createmessage.class);
        classes.add(readmessage.class);      
        classes.add(contenttype.class);
        classes.add(CORSFilter.class); // the global CORS filter
        return classes;
    }
}
