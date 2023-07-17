package br.unisinos.apps4business.notifications.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    public UserGroup(){};
    public UserGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
