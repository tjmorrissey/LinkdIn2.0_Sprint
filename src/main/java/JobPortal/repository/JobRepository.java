package JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import JobPortal.beans.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

}
