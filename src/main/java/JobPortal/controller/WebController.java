package JobPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import JobPortal.beans.Applicant;
import JobPortal.repository.ApplicantRepository;
import JobPortal.repository.EmployerRepository;
import JobPortal.repository.JobRepository;

@Controller
public class WebController {

	@Autowired
	ApplicantRepository appRepo;
	EmployerRepository empRepo;
	JobRepository jobRepo;
	
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
		
		return "index";
		
		//return appLogin(username, model);
	}
	
	
	
}
