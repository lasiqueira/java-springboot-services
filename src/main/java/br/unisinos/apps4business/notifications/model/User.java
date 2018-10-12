package br.unisinos.apps4business.notifications.model;


import br.unisinos.apps4business.notifications.enumerator.Role;
import lombok.Data;

import javax.persistence.*;

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


}
