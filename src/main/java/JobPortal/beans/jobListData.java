package JobPortal.beans;

import java.util.List;

public class jobListData {

	public Applicant applicant;
	public List<Job> jobs;
	
	public String appliedJob;
	public Long appliedJobInt;
	public String applicantId;
	
	
	public String getApplicantId() {
		return applicantId;
	}


	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}


	public jobListData() {
		super();
	}


	public jobListData(Applicant applicant, List<Job> jobs) {
		super();
		this.applicant = applicant;
		this.jobs = jobs;
	}

	

	public Applicant getApplicant() {
		return applicant;
	}


	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}


	public List<Job> getJobs() {
		return jobs;
	}


	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}


	public String getAppliedJob() {
		return appliedJob;
	}


	public void setAppliedJob(String appliedJob) {
		this.appliedJob = appliedJob;
	}
	
	public void setAppliedJobInt(Long appliedJobInt) {
		this.appliedJobInt = appliedJobInt;
	}

	public Long getAppliedJobInt() {
		
		appliedJobInt = Long.parseLong(appliedJob);
		
		return appliedJobInt;
	}


	@Override
	public String toString() {
		return "jobListData [applicant=" + applicant + ", jobs=" + jobs + "]";
	}	
	
	
	
}
