package sboj.repository;

import sboj.domain.entities.JobApplication;

public interface JobApplicationRepository extends GenericRepository<JobApplication, String> {
    void delete(String id);
}
