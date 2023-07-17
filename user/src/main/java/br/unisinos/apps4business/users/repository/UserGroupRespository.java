package br.unisinos.apps4business.users.repository;

import br.unisinos.apps4business.users.model.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRespository extends CrudRepository<UserGroup, Long> {


}
