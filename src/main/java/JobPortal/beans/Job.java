package JobPortal.beans;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	private String title;
	@Column(columnDefinition="VARCHAR(2000)")
	private String jobdesc;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employerId")
	private Employer employer;
	
	
	@ManyToMany(mappedBy = "jobsAppliedFor")

	private Set<Applicant> jobApplicants;

	public Job() {
		// TODO Auto-generated constructor stub
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public String getJobdesc() {
		return jobdesc;
	}

	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}
	
	public Set<Applicant> getJobApplicants() {
		return jobApplicants;
	}

	public void setJobApplicants(Set<Applicant> jobApplicants) {
		this.jobApplicants = jobApplicants;
	}

	public Job(String title, Employer employer, String jobdesc) {
		super();
	
		this.title = title;
		//this.employer = employer;
		this.jobdesc = jobdesc;
	
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", title=" + title + ", employerId=" + /*employer +*/ ", jobdesc=" + jobdesc
				+ ", jobsAppliedFor="  + jobApplicants + "]";
	}

	

}