package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.converter.v1.Converter;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupResponseDTO;
import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/usergroups")
public class UserGroupController {
    private final UserGroupService userGroupService;
    private final Converter converter;
    @Autowired
    public UserGroupController(UserGroupService userGroupService, Converter converter) {
        this.userGroupService = userGroupService;
        this.converter = converter;
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<UserGroupResponseDTO>> fetchAllUserGroups() {
        return new ResponseEntity<>(converter.convertUserGroupListToUserGroupResponseDTOList(userGroupService.fetchAllUserGroups()), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<UserGroupResponseDTO> createUserGroup(@Valid @RequestBody UserGroupRequestDTO userGroupRequest) {
        UserGroup userGroup = converter.convertUserGroupRequestDTOToUserGroup(userGroupRequest);
        return new ResponseEntity<>(converter.convertUserGroupToUserGroupResponseDTO(userGroupService.createUserGroup(userGroup)), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserGroupResponseDTO> updateUserGroup(@PathVariable Long id, @Valid @RequestBody UserGroupRequestDTO userGroupRequest) {
        UserGroup userGroup = converter.convertUserGroupRequestDTOToUserGroup(userGroupRequest);
        return new ResponseEntity<>(converter.convertUserGroupToUserGroupResponseDTO(userGroupService.updateUserGroup(id, userGroup)), HttpStatus.OK);
    }

}
