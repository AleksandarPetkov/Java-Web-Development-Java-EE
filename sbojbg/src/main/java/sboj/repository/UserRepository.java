package sboj.repository;

import sboj.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findUserByUsername(String username);
}
