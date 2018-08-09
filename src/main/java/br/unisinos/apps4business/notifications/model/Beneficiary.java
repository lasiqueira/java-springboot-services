package br.unisinos.apps4business.notifications.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Beneficiary extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToMany
    private List<Notification> notifications;

    @ManyToMany
    private List<UserGroup> userGroups;


    public Beneficiary() {}

    public Beneficiary(String login, String name, String email, List<UserGroup> userGroups, List<Notification> notifications) {
        super(login, name, email);
        this.userGroups = userGroups;
        this.notifications = notifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
