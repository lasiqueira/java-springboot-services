package br.unisinos.apps4business.users.api.controller.v1.v1;

import br.unisinos.apps4business.users.api.converter.v1.UserConverter;
import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping(value = "/", produces = "application/json")
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO user) {
        return userConverter.convertEntityToResponse(userService.createUser(userConverter.convertRequestToEntity(user)));
    }
    @PatchMapping(value = "/{id}", produces = "application/json")
    public UserResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO user) {
        return userConverter.convertEntityToResponse(userService.updateUser(id, userConverter.convertRequestToEntity(user)));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userConverter.convertEntityToResponse(userService.findById(id));
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<UserResponseDTO> fetchAllUsers() {
        return userConverter.convertEntityListToResponseList(userService.fetchAllUsers());
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }



}
