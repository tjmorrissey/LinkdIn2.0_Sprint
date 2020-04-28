package JobPortal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import JobPortal.beans.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

	@Query("SELECT t FROM Job t where t.jobId = :jobId")
	Job findById(@Param("jobId") int jobInt);

}
