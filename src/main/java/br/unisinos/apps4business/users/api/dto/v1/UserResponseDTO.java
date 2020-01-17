package br.unisinos.apps4business.users.api.dto.v1;

import br.unisinos.apps4business.users.enumerators.Role;

import java.util.List;
import java.util.Objects;

public class UserResponseDTO {

    private Long id;
    private Role role;
    private String login;
    private String name;
    private String email;
    private List<UserGroupDTO> userGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserGroupDTO> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroupDTO> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO that = (UserResponseDTO) o;
        return Objects.equals(id, that.id) &&
                role == that.role &&
                Objects.equals(login, that.login) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(userGroups, that.userGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, login, name, email, userGroups);
    }
}
