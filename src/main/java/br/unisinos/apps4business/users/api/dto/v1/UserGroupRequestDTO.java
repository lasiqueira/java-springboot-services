package br.unisinos.apps4business.users.api.dto.v1;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class UserGroupRequestDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

}
