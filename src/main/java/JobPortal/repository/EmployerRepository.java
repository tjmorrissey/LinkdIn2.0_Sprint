package JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import JobPortal.beans.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
	
	@Query("SELECT t FROM Employer t where t.company = :company")
	Employer findEmpByCompany(@Param("company") String company);
	
}
