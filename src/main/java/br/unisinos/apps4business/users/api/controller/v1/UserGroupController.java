package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.converter.v1.UserGroupConverter;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupResponseDTO;
import br.unisinos.apps4business.users.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/v1/usergroups")
public class UserGroupController {
    private UserGroupService userGroupService;
    private UserGroupConverter userGroupConverter;
    @Autowired
    public UserGroupController(UserGroupService userGroupService, UserGroupConverter userGroupConverter) {
        this.userGroupService = userGroupService;
        this.userGroupConverter = userGroupConverter;
    }

    @GetMapping(value = "", produces = "application/json")
    public List<UserGroupResponseDTO> fetchAllUserGroups() {
        return userGroupConverter.convertEntityListToResponseList(userGroupService.fetchAllUserGroups());
    }
    @PostMapping(value = "", produces = "application/json")
    public UserGroupResponseDTO createUserGroup(@Valid @RequestBody UserGroupRequestDTO userGroupRequest) {
        return userGroupConverter.convertEntityToResponse(userGroupService.createUserGroup(userGroupConverter.convertRequestToEntity(userGroupRequest)));
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public UserGroupResponseDTO updateUserGroup(@PathVariable Long id, @Valid @RequestBody UserGroupRequestDTO userGroupRequest) {
        return userGroupConverter.convertEntityToResponse(userGroupService.updateUserGroup(id, userGroupConverter.convertRequestToEntity(userGroupRequest)));
    }

}
