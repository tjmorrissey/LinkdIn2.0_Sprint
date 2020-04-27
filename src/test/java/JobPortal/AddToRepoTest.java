package JobPortal;

import org.springframework.beans.factory.annotation.Autowired;

import JobPortal.beans.Employer;
import JobPortal.repository.ApplicantRepository;
import JobPortal.repository.EmployerRepository;
import JobPortal.repository.JobRepository;

public class AddToRepoTest {
	
	@Autowired
	static ApplicantRepository appRepo;
	static EmployerRepository empRepo;
	static JobRepository jobRepo;

	public static void main(String[] args) {

		Employer dmacc = new Employer("DMACC");
		Employer casey = new Employer("Casey's General Store");
		
		empRepo.save(dmacc);
		empRepo.save(casey);
	}

}
