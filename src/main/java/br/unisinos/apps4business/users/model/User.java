package br.unisinos.apps4business.users.model;

import br.unisinos.apps4business.users.enumerators.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Role role;
    private String login;
    private String name;
    private String email;
    @ManyToMany
    private List<UserGroup> userGroups;

}
