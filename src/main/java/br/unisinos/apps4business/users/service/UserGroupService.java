package br.unisinos.apps4business.users.service;

import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.repository.UserGroupRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserGroupService {
    private UserGroupRespository userGroupRespository;

    @Autowired
    public UserGroupService(UserGroupRespository userGroupRespository) {
        this.userGroupRespository = userGroupRespository;
    }

    public UserGroup createUserGroup(UserGroup userGroup){
        return userGroupRespository.save(userGroup);
    }

    public List<UserGroup> fetchAllUserGroups() {
        List<UserGroup> list = new ArrayList<>();
        userGroupRespository.findAll().forEach(userGroup -> list.add(userGroup));
        return list;
    }

}
