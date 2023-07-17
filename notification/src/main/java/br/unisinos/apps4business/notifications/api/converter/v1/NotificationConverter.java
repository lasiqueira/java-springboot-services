package br.unisinos.apps4business.notifications.api.converter.v1;

import br.unisinos.apps4business.notifications.api.dto.v1.NotificationRequestDTO;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.model.User;
import br.unisinos.apps4business.notifications.model.UserGroup;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {

    public Notification convertNotificationRequestDTOToNotification(NotificationRequestDTO notificationRequestDTO){
        Notification notification = new Notification();

        notification.setDate(notificationRequestDTO.getDate());
        notification.setContent(notificationRequestDTO.getContent());
        User user = new User();
        user.setId(notificationRequestDTO.getOperatorId());
        notification.setUser(user);
        notification.setNotificationType(notificationRequestDTO.getNotificationType());

        UserGroup userGroup = new UserGroup();
        userGroup.setId(notificationRequestDTO.getUserGroupId());
        notification.setUserGroup(userGroup);

        return notification;

    }
}
