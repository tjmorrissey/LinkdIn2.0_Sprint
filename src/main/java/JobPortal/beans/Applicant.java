package JobPortal.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applicantId;
	private String username;
	private String name;
	private String address;
	private String educationLevel;
	
	@ManyToMany
	public List<Job> jobsAppliedFor = new ArrayList<Job>();

	public Applicant() {
		super();
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public List<Job> getJobsAppliedFor() {
		return jobsAppliedFor;
	}

	public void setJobsAppliedFor(List<Job> jobsAppliedFor) {
		this.jobsAppliedFor = jobsAppliedFor;
	}
	
	public void addJobAppliedFor(Job j) {
		jobsAppliedFor.add(j);
	}
	
	public void deleteJobAppliedFor(Job j) {
		jobsAppliedFor.remove(j);
	}
	
	public void addJobListAppliedFor(List<Job> jobs) {
		jobsAppliedFor.addAll(jobs);
	}

	public Applicant(String username, String name, String educationLevel, String address) {
		super();
		this.username = username;
		this.name = name;
		this.educationLevel = educationLevel;
		this.address = address;
	
	}

	@Override
	public String toString() {
		return "Applicant [applicantId=" + applicantId + ", username=" + username +", name=" + name + ", address=" + address + ", educationLevel="
				+ educationLevel + ", jobsAppliedFor=" + jobsAppliedFor + "]";
	}
	
	
		
	
	
	
}
