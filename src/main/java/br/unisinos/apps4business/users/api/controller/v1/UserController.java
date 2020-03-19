package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) {
        return new ResponseEntity<>(mapperFacade.map(userService.createUser(mapperFacade.map(user, User.class)), UserResponseDTO.class), HttpStatus.CREATED);
    }
    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO user) {
        return new ResponseEntity<>(mapperFacade.map(userService.updateUser(id, mapperFacade.map(user, User.class)), UserResponseDTO.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(mapperFacade.map(userService.findById(id), UserResponseDTO.class), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<UserResponseDTO>> fetchAllUsers() {
        return new ResponseEntity<>(mapperFacade.mapAsList(userService.fetchAllUsers(), UserResponseDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
