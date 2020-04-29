package JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import JobPortal.beans.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long>{

	@Query("SELECT t FROM Applicant t where t.username = :username")
	Applicant findAppByUsername(@Param("username") String username);
}
