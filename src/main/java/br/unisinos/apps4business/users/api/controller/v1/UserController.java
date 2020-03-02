package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.converter.v1.UserConverter;
import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;
    private MapperFacade mapperFacade;

    @Autowired
    public UserController(UserService userService, MapperFacade mapperFacade) {
        this.userService = userService;
        this.mapperFacade = mapperFacade;
    }

    @PostMapping(value = "", produces = "application/json")
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO user) {
        return mapperFacade.map(userService.createUser(mapperFacade.map(user, User.class)), UserResponseDTO.class);
    }
    @PatchMapping(value = "/{id}", produces = "application/json")
    public UserResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO user) {
        return mapperFacade.map(userService.updateUser(id, mapperFacade.map(user, User.class)), UserResponseDTO.class);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return mapperFacade.map(userService.findById(id), UserResponseDTO.class);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<UserResponseDTO> fetchAllUsers() {
        return mapperFacade.mapAsList(userService.fetchAllUsers(), UserResponseDTO.class);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
