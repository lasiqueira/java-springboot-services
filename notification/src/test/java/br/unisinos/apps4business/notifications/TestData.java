package br.unisinos.apps4business.notifications;

import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import br.unisinos.apps4business.notifications.enumerator.Role;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.model.User;
import br.unisinos.apps4business.notifications.model.UserGroup;

import java.time.LocalDate;

/** Test fixtures (replaces the abandoned random-beans library). */
public final class TestData {

    private TestData() {
    }

    public static User operator() {
        User user = new User();
        user.setId(1L);
        user.setRole(Role.OPERATOR);
        user.setLogin("operator");
        user.setName("Operator");
        user.setEmail("operator@test.com");
        return user;
    }

    public static UserGroup userGroup() {
        UserGroup userGroup = new UserGroup("group", "a test group");
        userGroup.setId(1L);
        return userGroup;
    }

    public static Notification notification() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setUser(operator());
        notification.setContent("test content");
        notification.setStatus(NotificationStatus.SENT);
        notification.setDate(LocalDate.now());
        notification.setUserGroup(userGroup());
        notification.setNotificationType(NotificationType.NOTIFICATION);
        return notification;
    }
}
