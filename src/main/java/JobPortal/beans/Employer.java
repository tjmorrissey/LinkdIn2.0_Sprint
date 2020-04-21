package JobPortal.beans;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employerId;
	private String company;
	
	@OneToMany
	private Set<Job> jobsAppliedFor;
	

	public Employer() {
		// TODO Auto-generated constructor stub
	}


	public Long getEmployerId() {
		return employerId;
	}


	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}



	public Employer(String company) {
		super();
		this.company = company;
		
	}


	@Override
	public String toString() {
		return "Employer [employerId=" + employerId + ", company=" + company + "]";
	}

	

	

}
