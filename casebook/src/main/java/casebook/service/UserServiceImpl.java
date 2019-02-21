package casebook.service;

import casebook.domain.entities.User;
import casebook.domain.models.service.UserServiceModel;
import casebook.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

        if (this.userRepository.save(user) == null) {
            return false;
        }
        return true;

    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        User user = this.userRepository.findUserByUsername(userServiceModel.getUsername());
        if (user == null || !user.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword()))) {
            throw new IllegalArgumentException("Cant find User with username " + userServiceModel.getUsername());
        }
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserById(String id) {
        User user = this.userRepository.findById(id);
        if (user == null) {
            return null;
        } else {
            return this.modelMapper.map(user, UserServiceModel.class);
        }
    }

}
