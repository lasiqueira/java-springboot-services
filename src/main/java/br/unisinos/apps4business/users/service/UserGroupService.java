package br.unisinos.apps4business.users.service;

import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.repository.UserGroupRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
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
    public UserGroup updateUserGroup(Long id,UserGroup userGroup){
        if(userGroupRespository.existsById(id)){
            return userGroupRespository.save(userGroup);
        }else{
            throw new NotFoundException();
        }
    }

    public List<UserGroup> fetchAllUserGroups() {
        List<UserGroup> list = new ArrayList<>();
        userGroupRespository.findAll().forEach(userGroup -> list.add(userGroup));
        return list;
    }

}
