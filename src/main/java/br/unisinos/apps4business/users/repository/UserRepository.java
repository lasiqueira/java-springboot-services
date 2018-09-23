package br.unisinos.apps4business.users.repository;

import br.unisinos.apps4business.users.enumerators.Role;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.model.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findByUserGroups(UserGroup userGroup);
    public List<User> findByRole(Role role);
    public List<User> findByName(String name);
    public List<User> findByLogin(String login);

}
