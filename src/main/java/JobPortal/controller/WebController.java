package JobPortal.controller;


import java.util.List;

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


@Controller
public class WebController {

	@Autowired
	ApplicantRepository appRepo;
	@Autowired
	EmployerRepository empRepo;
	@Autowired
	JobRepository jobRepo;

	
	public Applicant currentGlobalApp;
	public Employer currentGlobalEmp;
	
	@GetMapping({"/", "/index"})
	public String logOut(Model mode) {
		return "/index";
	}
	
	
	//-------APPLICANT--------------------
	
	@PostMapping("/appLogin")
	public String appLogin(@ModelAttribute("username") String username, Model model) {

		Applicant app = appRepo.findAppByUsername(username);
		
		currentGlobalApp = app;
		
		model.addAttribute("applicant", app);
		
		return "applicantHomePage";
	}

	@GetMapping("/addNewApp")
	public String addNewAppicant(Model model) {
		Applicant a = new Applicant();
		model.addAttribute("newApp", a);

		return "createNewApplicant";
	}
	
	public String failNewApplicant(Model model) {
		Applicant a = new Applicant();
		model.addAttribute("newApp", a);

		return "createNewApplicant";
	}

	@PostMapping("addNewApp")
	public String addNewApplicant(@ModelAttribute Applicant a, Model model) {
		
		List<Applicant> apps = appRepo.findAll();
		boolean pass = true;
		
		//check if username already exists
		for(Applicant app: apps) {
			if(app.getUsername().equals(a.getUsername())) {
				pass = false;
			}
		}
		
		if(!pass) {	
			//if username does exist already will route back to createNewApplicant page
			//will need to figure something out to send error codes/messages
			return failNewApplicant(model);
		}
		else {
		appRepo.save(a);
		String username = a.getUsername();
		return appLogin(username, model);
		}
	}
	

	@GetMapping("/editApp/{appId}")
	public String showUpdateApplicant(@PathVariable("appId") Long id, Model model) {
		
		Applicant c = appRepo.findById(id).orElse(null);
		model.addAttribute("newApp", c);
		return "createNewApplicant";
	}
	
	@GetMapping("/deleteJobFromApp/{jobId}")
	public String deleteJobAppliedFor(@PathVariable("jobId") Long jobId, Model model) {
		
		Applicant app = currentGlobalApp;
		Job j = jobRepo.findById(jobId).orElse(null);
		
		app.deleteJobAppliedFor(j);
		
		appRepo.save(app);
	
		return appLogin(app.getUsername(), model);
	}
	
	@GetMapping("/returnToApp")
	public String returnToApp(Model model) {

		Applicant app = currentGlobalApp;
		
		if(app.equals(null)) {
			return logOut(model);
		}
		
		return appLogin(app.getUsername(), model);
	}
	
	/*@GetMapping("/applyForJob/{jobId}") 	
	public String applyForJob(@PathVariable("jobId") Long jobId,  Model model) { 	
		
		Job b = jobRepo.findById(jobId).orElse(null);  		
		
		Applicant app = new Applicant();
		
		app.addJobAppliedFor(b);
		
		//model.addAttribute("test", app);
		
		//return "outputTestPage"; 
		
		model.addAttribute("applicant", app); 
		
		return "applyForJob"; 	
		}
	
		/*@PostMapping("/applyForJob") 	
		public String applyForJob(@ModelAttribute("applicant") Applicant app,  Model model) { 	
		
			
		model.addAttribute("test", app);
		
		return "outputTestPage";
			
		/*Applicant applicant = appRepo.findAppByUsername(app.getUsername());
		
		applicant.addJobListAppliedFor(applicant.getJobsAppliedFor());
			
		appRepo.save(applicant);
		
		return appLogin(applicant.getUsername(), model); 
		}*/
	
	
	
	/*@GetMapping("/applyForJob/{jobId}")
	public String applyForJob(@PathVariable("jobId") Long jobId, Model model) {
		
		jobListData data = new jobListData();
		data.jobs = jobRepo.findAll();
		data.applicant = currentGlobalApp;
	
		model.addAttribute("pageData", data); 
		return "jobList";
	}*/
	
	@GetMapping("/applyForJob/{jobId}")
	public String applyForJob(@PathVariable("jobId") Long jobId, Model model) {
		
		Applicant app = currentGlobalApp;
		
		List<Job> jobs = jobRepo.findAll();	
		Job j = null;
		
		for(Job job :jobs) {
			if(job.getJobId() == jobId) {
				j = job;
			}  }
		
		app.addJobAppliedFor(j);
		
		appRepo.save(app);
		
		return appLogin(app.getUsername(), model);
	}
	
	/*@GetMapping("/applyForJob/{appId}/{jobId}")
	public String applyForJob(@PathVariable("appId") Long appId, @PathVariable("jobId") Long jobId, Model model) {
		
		List<Job> jobs = jobRepo.findAll();	
		List<Applicant> apps = appRepo.findAll();	
		
		Job j = null;
		Applicant a = null;
		
		for(Job job :jobs) {
			if(job.getJobId().toString().equals(jobId)) {
				j = job;
			}  }
		
		for(Applicant app :apps) {
			if(app.getApplicantId().toString().equals(appId)) {
				a = app;
			}  }
		
			a.addJobAppliedFor(j);
			
			model.addAttribute("test", a);
			return "outputTestPage";
		
		//return appLogin(a.getUsername(), model);
	}*/
	

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
	public String showJobList(Model model) {
		
		Applicant app = currentGlobalApp;
		
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
	
	@GetMapping("/editJob/{jobId}")
	public String showUpdateJob(@PathVariable("jobId") Long id, Model model) {
		
		Job j = jobRepo.findById(id).orElse(null);
		model.addAttribute("newJob", j);
		return "createNewJob";
	}

	
	
	//-------EMPLOYER--------------------
	
	@PostMapping("/empLogin")
	public String empLogin(@ModelAttribute("company") String company, Model model) {

		Employer emp = empRepo.findEmpByCompany(company);
		currentGlobalEmp = emp;
		model.addAttribute("employer", emp);
		
		return "employerHomePage";
	}
	
	@GetMapping("/returnToEmp")
	public String returnToEmp(Model model) {

		Employer emp = currentGlobalEmp;
		
		if(emp.equals(null)) {
			return logOut(model);
		}
		
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
		
		List<Employer> emps = empRepo.findAll();
		boolean pass = true;
		
		//check if company name already exists
		for(Employer e: emps) {
			if(e.getCompany().equals(emp.getCompany())) {
				pass = false;
			}
		}
		
		if(!pass) {	
			//if company name does exist already will route back to createNewApplicant page
			//will need to figure something out to send error codes/messages
			return failNewEmployer(model);
		}
		else {
		empRepo.save(emp);
		
		String comp = emp.getCompany();

		// return to company profile, with list of their jobs
		return empLogin(comp, model);
		}
	}
	
	public String failNewEmployer(Model model) {
		Employer e = new Employer();
		model.addAttribute("newEmployer", e);

		return "createNewEmployer";
	}

}