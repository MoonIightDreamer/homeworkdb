package repository;

import model.User;

public interface UserRepository {
    //false if already exists
    boolean create(User user);

    User get(Integer id);

    //false if not found
    boolean delete(Integer id);
}
