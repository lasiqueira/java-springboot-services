package br.unisinos.apps4business.users.api.converter.v1;

import br.unisinos.apps4business.users.api.dto.v1.*;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.model.UserGroup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {
    public UserResponseDTO convertUserToUserResponseDTO(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getRole(),
                user.getLogin(),
                user.getName(),
                user.getEmail(),
                convertUserGroupListToUserGroupResponseDTOList(user.getUserGroups()));
    }
    public List<UserGroupDTO> convertUserGroupListToUserGroupResponseDTOList(List<UserGroup> userGroups){
        return userGroups.stream()
                .map(userGroup -> new UserGroupDTO(userGroup.getId(), userGroup.getName(), userGroup.getDescription()))
                .collect(Collectors.toList());

    }

    public User convertUserRequestDTOToUser(UserRequestDTO userRequestDTO){
       return new User(
               null,
               userRequestDTO.role(),
               userRequestDTO.login(),
               userRequestDTO.name(),
               userRequestDTO.email(),
               convertUserGroupResponseDTOListToUserGroupList(userRequestDTO.userGroups()));
    }

    public List<UserGroup> convertUserGroupResponseDTOListToUserGroupList(List<UserGroupDTO> userGroups) {
        return userGroups.stream()
                .map(userGroupDTO -> new UserGroup(userGroupDTO.id(), userGroupDTO.name(), userGroupDTO.description()))
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> convertUserListToUserResponseDTOList(List<User> users){
        return users.stream()
                .map(this::convertUserToUserResponseDTO)
                .collect(Collectors.toList());
    }

    public UserGroup convertUserGroupRequestDTOToUserGroup(UserGroupRequestDTO userGroupRequestDTO){
        return new UserGroup(null, userGroupRequestDTO.name(), userGroupRequestDTO.description());
    }

    public UserGroupDTO convertUserGroupToUserGroupResponseDTO(UserGroup userGroup){
        return new UserGroupDTO(userGroup.getId(), userGroup.getName(), userGroup.getDescription());
    }


}
