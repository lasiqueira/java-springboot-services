package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.dto.v1.UserGroupRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupResponseDTO;
import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.service.UserGroupService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/v1/usergroups")
public class UserGroupController {
    private UserGroupService userGroupService;
    private MapperFacade mapperFacade;
    @Autowired
    public UserGroupController(UserGroupService userGroupService, MapperFacade mapperFacade) {
        this.userGroupService = userGroupService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping(value = "", produces = "application/json")
    public List<UserGroupResponseDTO> fetchAllUserGroups() {
        return mapperFacade.mapAsList(userGroupService.fetchAllUserGroups(), UserGroupResponseDTO.class);
    }
    @PostMapping(value = "", produces = "application/json")
    public UserGroupResponseDTO createUserGroup(@Valid @RequestBody UserGroupRequestDTO userGroupRequest) {
        return mapperFacade.map(userGroupService.createUserGroup(mapperFacade.map(userGroupRequest, UserGroup.class)), UserGroupResponseDTO.class);
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public UserGroupResponseDTO updateUserGroup(@PathVariable Long id, @Valid @RequestBody UserGroupRequestDTO userGroupRequest) {
        return mapperFacade.map(userGroupService.updateUserGroup(id, mapperFacade.map(userGroupRequest, UserGroup.class)), UserGroupResponseDTO.class);
    }

}
