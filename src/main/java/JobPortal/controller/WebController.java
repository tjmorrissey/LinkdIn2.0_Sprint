package JobPortal.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import JobPortal.beans.Applicant;
import JobPortal.beans.Employer;
import JobPortal.beans.Job;
import JobPortal.beans.jobListData;
import JobPortal.repository.ApplicantRepository;
import JobPortal.repository.EmployerRepository;
import JobPortal.repository.JobRepository;
import lombok.var;


@Controller
public class WebController {

	@Autowired
	ApplicantRepository appRepo;
	@Autowired
	EmployerRepository empRepo;
	@Autowired
	JobRepository jobRepo;

	
	//-------APPLICANT--------------------
	
	@PostMapping("/appLogin")
	public String appLogin(@ModelAttribute("username") String username, Model model) {

		Applicant app = appRepo.findAppByUsername(username);
		model.addAttribute("applicant", app);
		
		return "applicantHomePage";
	}

	@GetMapping("/addNewApp")
	public String addNewAppicant(Model model) {
		Applicant a = new Applicant();
		model.addAttribute("newApp", a);

		return "createNewApplicant";
	}

	@PostMapping("addNewApp")
	public String addNewApplicant(@ModelAttribute Applicant a, Model model) {
		appRepo.save(a);

		String username = a.getUsername();

		return appLogin(username, model);
	}
	

	@GetMapping("/editApp/{appId}")
	public String showUpdateApplicant(@PathVariable("appId") Long id, Model model) {
		
		Applicant c = appRepo.findById(id).orElse(null);
		model.addAttribute("newApp", c);
		return "createNewApplicant";
	}
	
	@GetMapping("/deleteJobFromApp/{id}")
	public String deleteJobAppliedFor(@PathVariable("jobId") Long jobId, @PathVariable("applicantId") Long appId, Model model) {
		Applicant c = appRepo.findById(appId).orElse(null);
		Job j = jobRepo.findById(jobId).orElse(null);
		c.getJobsAppliedFor().remove(j);
		return "applicantHomePage";
	}
	
	@PostMapping("/applyForJob/{jobId}")
	public String applyForJob(@PathVariable("jobId") Long jobId, @ModelAttribute("username") String username, Model model) {
		
		Applicant app = appRepo.findAppByUsername(username);
		List<Job> jobs = jobRepo.findAll();	
		Job j = null;
		
		for(Job job :jobs) {
			if(job.getJobId() == jobId) {
				j = job;
			}  }
		
		app.addJobAppliedFor(j);
		
		model.addAttribute("applicant", app);
		return "applicantHomePage";
	}
	

	//-------JOB--------------------
	
	@GetMapping("/addNewJob/{employerId}")
	public String addNewJob(@PathVariable("employerId") String company, Model model) {
		Job j = new Job();
		// will need to send employer id from the Employer Home Page, allowing for
		// company to auto fill.

		Employer comp = empRepo.findEmpByCompany(company);
		
		model.addAttribute("newJob", j);
		
		j.setEmployer(comp);
		
		return "createNewJob";
	}

	@PostMapping("addNewJob")
	public String addNewJob(@ModelAttribute Job j, Model model) {
		
		jobRepo.save(j);
		
		Employer emp = j.getEmployer();
		String comp = emp.getCompany();

		// return to applicant profile, with list of their jobs
		return empLogin(comp, model);
	}

	@PostMapping("/showJobList")
	public String showJobList(@ModelAttribute("username") String username, Model model) {
		
		Applicant app = appRepo.findAppByUsername(username);
		
		jobListData data = new jobListData(app, jobRepo.findAll());
		
		model.addAttribute("pageData", data);

		return "jobList";
	}
	
	
	@GetMapping("/showJobInfo/{jobId}")
	public String showJobInfo(@PathVariable("jobId") int jobId, Model model) {
		
		//had to go round about way, as was getting issues with Optional, and did not have time to solve
		List<Job> jobs = jobRepo.findAll();	
		Job j = null;
		
		for(Job job :jobs) {
			if(job.getJobId() == jobId) {
				j = job;
			}  }
		model.addAttribute("job", j);
		return "jobPage";
	}

	
	
	//-------EMPLOYER--------------------
	
	@PostMapping("/empLogin")
	public String empLogin(@ModelAttribute("company") String company, Model model) {

		Employer emp = empRepo.findEmpByCompany(company);
		model.addAttribute("employer", emp);
		
		return "employerHomePage";
	}
	
	@GetMapping("/createNewEmployer")
	public String addNewCompany(Model model) {
		Employer emp = new Employer();
		
		model.addAttribute("newEmployer", emp);
		
		return "createNewEmployer";
	}
	
	@PostMapping("createNewEmployer")
	public String addNewCompany(@ModelAttribute Employer emp, Model model) {
		
		empRepo.save(emp);
		
		String comp = emp.getCompany();

		// return to company profile, with list of their jobs
		return empLogin(comp, model);
	}

}