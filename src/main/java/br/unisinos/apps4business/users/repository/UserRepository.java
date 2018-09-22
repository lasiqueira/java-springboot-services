package br.unisinos.apps4business.users.repository;

import br.unisinos.apps4business.users.enumerators.Role;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.model.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findByUserGroup(UserGroup userGroup);
    public List<User> findByRole(Role role);
    public List<User> findByName(String name);
    public List<User> findByLogin(String login);

}
