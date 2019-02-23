package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.binding.JobCreateBindingModel;
import sboj.domain.model.JobAppServiceModel;
import sboj.service.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobAppAddJobBean {

    private JobCreateBindingModel jobModel;

    private JobApplicationService jobApplicationService;

    private ModelMapper modelMapper;

    public JobAppAddJobBean() {
    }

    @Inject
    public JobAppAddJobBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
        this.jobModel = new JobCreateBindingModel();
    }

    public void addJob() throws IOException {
        this.jobApplicationService.addJob(this.modelMapper.map(jobModel, JobAppServiceModel.class));

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/home");
    }

    public JobCreateBindingModel getJobModel() {
        return jobModel;
    }

    public void setJobModel(JobCreateBindingModel jobModel) {
        this.jobModel = jobModel;
    }
}
