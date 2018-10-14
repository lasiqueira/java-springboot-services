package br.unisinos.apps4business.users.api.dto.v1;

import br.unisinos.apps4business.users.enumerators.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserRequestDTO {
    @NotNull
    private Role role;
    @NotEmpty
    private String login;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    private List<UserGroupDTO> userGroups;

}
