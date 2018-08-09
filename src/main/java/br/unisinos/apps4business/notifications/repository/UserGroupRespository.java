package br.unisinos.apps4business.notifications.repository;

import br.unisinos.apps4business.notifications.model.UserGroup;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupRespository extends CrudRepository<UserGroup, Long> {
}
