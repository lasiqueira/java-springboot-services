package br.unisinos.apps4business.users.api.dto.v1;

import br.unisinos.apps4business.users.enumerators.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Long id;
    private Role role;
    private String login;
    private String name;
    private String email;
    private List<UserGroupDTO> userGroups;

}
