package br.unisinos.apps4business.users.api.converter.v1;

import br.unisinos.apps4business.users.api.dto.v1.UserGroupDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.model.UserGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConverter {

    public User convertRequestToEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setLogin(userRequestDTO.getLogin());
        user.setRole(userRequestDTO.getRole());
        user.setName(userRequestDTO.getName());
        user.setUserGroups(convertUserGroupsDTOToUserGroups(userRequestDTO.getUserGroups()));
        return user;
    }

    public UserResponseDTO convertEntityToResponse (User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setLogin(user.getLogin());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setUserGroups(convertUserGroupsToUserGroupsDTO(user.getUserGroups()));
        return userResponseDTO;
    }

    public List<UserResponseDTO> convertEntityListToResponseList (List<User> users){
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for(User user: users){
            userResponseDTOList.add(convertEntityToResponse(user));
        }
        return userResponseDTOList;
    }

    private List<UserGroup> convertUserGroupsDTOToUserGroups(List<UserGroupDTO> userGroupDTOList){
        List<UserGroup> userGroups = new ArrayList<>();
        for(UserGroupDTO userGroupDTO:userGroupDTOList){
            UserGroup userGroup= new UserGroup();
            userGroup.setName(userGroupDTO.getName());
            userGroup.setId(userGroupDTO.getId());
            userGroup.setDescription(userGroupDTO.getDescription());
            userGroups.add(userGroup);
        }
        return userGroups;
    }
    private List<UserGroupDTO> convertUserGroupsToUserGroupsDTO(List<UserGroup> UserGroupDTO){
        List<UserGroupDTO> userGroupDTOList = new ArrayList<>();
        for(UserGroup userGroup:UserGroupDTO){
            UserGroupDTO userGroupDTO= new UserGroupDTO();
            userGroupDTO.setName(userGroup.getName());
            userGroupDTO.setId(userGroup.getId());
            userGroupDTO.setDescription(userGroup.getDescription());
            userGroupDTOList.add(userGroupDTO);
        }
        return userGroupDTOList;
    }
}
