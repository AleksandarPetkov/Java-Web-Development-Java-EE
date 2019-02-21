package casebook.repository;

import casebook.domain.entities.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User, String>{

    User findUserByUsername(String username);

}
