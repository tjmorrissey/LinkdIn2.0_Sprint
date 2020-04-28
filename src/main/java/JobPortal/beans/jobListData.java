package JobPortal.beans;

import java.util.List;

public class jobListData {

	public Applicant applicant;
	public List<Job> jobs;
	
	
	public jobListData(Applicant applicant, List<Job> jobs) {
		super();
		this.applicant = applicant;
		this.jobs = jobs;
	}


	@Override
	public String toString() {
		return "jobListData [applicant=" + applicant + ", jobs=" + jobs + "]";
	}	
	
	
	
}
