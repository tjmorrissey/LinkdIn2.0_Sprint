package JobPortal.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	private Set<Job> jobsAppliedFor;

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

	public Set<Job> getJobsAppliedFor() {
		return jobsAppliedFor;
	}

	public void setJobsAppliedFor(Set<Job> jobsAppliedFor) {
		this.jobsAppliedFor = jobsAppliedFor;
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
		return "Applicant [applicantId=" + applicantId + ", name=" + name + ", address=" + address + ", educationLevel="
				+ educationLevel; // + ", jobsAppliedFor=" + jobsAppliedFor + "]";
	}
	
	
		
	
	
	
}
