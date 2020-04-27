package JobPortal.controller;


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
import JobPortal.repository.ApplicantRepository;
import JobPortal.repository.EmployerRepository;
import JobPortal.repository.JobRepository;


@Controller
public class WebController {

	@Autowired
	ApplicantRepository appRepo;
	@Autowired
	EmployerRepository empRepo;
	@Autowired
	JobRepository jobRepo;

	@GetMapping("/homePage")
	public String goToIndex() {
		return "addNewApp";
	}

	
	//-------APPLICANT--------------------
	
	@GetMapping("/appLogin")
	public String appLogin(@PathVariable("username") String username, Model model) {

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

	//-------JOB--------------------
	
	@GetMapping("/addNewJob")
	public String addNewJob(Model model) {
		Job j = new Job();
		model.addAttribute("newJob", j);

		// will need to send employer id from the Employer Home Page, allowing for
		// company to auto fill.
		// Employer comp = model.getAttribute("comp");
		// model.addAttribute("company", comp);

		return "createNewJob";
	}

	@PostMapping("addNewJob")
	public String addNewJob(@ModelAttribute Job j, Model model) {
		
		jobRepo.save(j);

		// return to applicant profile, with list of their jobs
		return goToIndex();
	}

	@GetMapping("/showJobList")
	public String showJobList(Model model) {

		model.addAttribute("jobs", jobRepo.findAll());

		return "jobList";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateApplicant(@PathVariable("applicantId") long id, Model model) {
		Applicant c = appRepo.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + c.toString());
		model.addAttribute("newApplicant", c);
		return "editAppProfile";
	}

	@GetMapping("/deleteApp/{id}")
	public String deleteApplicant(@PathVariable("applicantId") long id, Model model) {
		Applicant c = appRepo.findById(id).orElse(null);
		appRepo.delete(c);
		return "applicantHomePage";
	}
	
	@GetMapping("/deleteJobFromApp/{id}")
	public String deleteJobAppliedFor(@PathVariable("jobId") long jobId, @PathVariable("applicantId") long appId, Model model) {
		Applicant c = appRepo.findById(appId).orElse(null);
		Job j = jobRepo.findById(jobId).orElse(null);
		c.getJobsAppliedFor().remove(j);
		return "applicantHomePage";
	}
	
	
	//-------EMPLOYER--------------------
	
	@GetMapping("/empLogin")
	public String empLogin(@PathVariable("company") String company, Model model) {

		Employer emp = empRepo.findAppByCompany(company);

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