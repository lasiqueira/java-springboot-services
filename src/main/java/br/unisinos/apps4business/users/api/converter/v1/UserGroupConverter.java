package br.unisinos.apps4business.users.api.converter.v1;

import br.unisinos.apps4business.users.api.dto.v1.UserGroupRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupResponseDTO;
import br.unisinos.apps4business.users.model.UserGroup;
import org.jvnet.hk2.annotations.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupConverter {

    public UserGroup convertRequestToEntity(UserGroupRequestDTO userGroupRequestDTO){
        UserGroup userGroup = new UserGroup();
        userGroup.setDescription(userGroupRequestDTO.getDescription());
        userGroup.setName(userGroupRequestDTO.getName());
        return userGroup;
    }

    public List<UserGroupResponseDTO> convertEntityListToResponseList(List<UserGroup> userGroups){
        List<UserGroupResponseDTO> userGroupResponseDTOList = new ArrayList<>();
        for(UserGroup userGroup: userGroups){
            userGroupResponseDTOList.add(convertEntityToResponse(userGroup));
        }
        return userGroupResponseDTOList;
    }
    public UserGroupResponseDTO convertEntityToResponse(UserGroup userGroup){
        UserGroupResponseDTO userGroupResponseDTO = new UserGroupResponseDTO();
        userGroupResponseDTO.setId(userGroup.getId());
        userGroupResponseDTO.setName(userGroup.getName());
        userGroupResponseDTO.setDescription(userGroup.getDescription());
        return userGroupResponseDTO;
    }
}
