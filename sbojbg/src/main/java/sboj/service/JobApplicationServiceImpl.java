package sboj.service;

import org.modelmapper.ModelMapper;
import sboj.domain.entities.JobApplication;
import sboj.domain.model.JobAppServiceModel;
import sboj.repository.JobApplicationRepository;

import javax.inject.Inject;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addJob(JobAppServiceModel serviceModel) {
        this.jobApplicationRepository.save(this.modelMapper.map(serviceModel, JobApplication.class));
    }
}
