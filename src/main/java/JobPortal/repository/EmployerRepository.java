package JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import JobPortal.beans.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long>{

}
