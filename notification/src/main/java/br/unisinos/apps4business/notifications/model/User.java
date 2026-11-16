package br.unisinos.apps4business.notifications.model;


import br.unisinos.apps4business.notifications.enumerator.Role;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // "user" is a reserved word in H2 2.x
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
