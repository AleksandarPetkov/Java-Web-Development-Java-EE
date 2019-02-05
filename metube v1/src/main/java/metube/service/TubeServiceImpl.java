package metube.service;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.repository.TubeRepository;
import metube.util.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;

    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.save(tube);
    }

    @Override
    public List<TubeServiceModel> findAllTubes() {
        List<Tube> tubes = this.tubeRepository.findAll();
        List<TubeServiceModel> tubeServiceModels = new ArrayList<>();

        for (Tube tube : tubes) {
            TubeServiceModel tubeModel = this.modelMapper.map(tube, TubeServiceModel.class);
            tubeServiceModels.add(tubeModel);
        }
        return tubeServiceModels;
    }
}
