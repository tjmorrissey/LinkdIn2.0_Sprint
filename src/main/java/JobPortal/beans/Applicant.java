package JobPortal.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<Job> jobsAppliedFor;

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
	
	public void addJobAppliedFor(Job j) {
		this.jobsAppliedFor.add(j);
		j.getJobApplicants().add(this);
	}
	
	public void deleteJobAppliedFor(Job j) {
		this.jobsAppliedFor.remove(j);
		j.getJobApplicants().remove(this);
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
