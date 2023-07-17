package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.converter.v1.Converter;
import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final Converter converter;

    @Autowired
    public UserController(UserService userService, Converter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        User user = converter.convertUserRequestDTOToUser(userRequestDTO);
        return new ResponseEntity<>(converter.convertUserToUserResponseDTO(userService.createUser(user)), HttpStatus.CREATED);
    }
    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        User user = converter.convertUserRequestDTOToUser(userRequestDTO);
        return new ResponseEntity<>(converter.convertUserToUserResponseDTO(userService.updateUser(id,user)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(converter.convertUserToUserResponseDTO(userService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<UserResponseDTO>> fetchAllUsers() {
        return new ResponseEntity<>(converter.convertUserListToUserResponseDTOList(userService.fetchAllUsers()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
