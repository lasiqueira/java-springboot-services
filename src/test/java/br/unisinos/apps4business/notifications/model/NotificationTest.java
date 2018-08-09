package br.unisinos.apps4business.notifications.model;

import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class NotificationTest {

    private static final String CONTENT = "test content";
    private final static Long ID= 1l;
    private NotificationStatus notificationStatus;
    private UserGroup userGroup;
    private Operator operator;
    private LocalDate localDate;
    private NotificationType notificationType;

    @Before
    public void setup(){
        notificationStatus = NotificationStatus.SENT;
        userGroup = new UserGroup();
        operator = new Operator();
        localDate = LocalDate.now();
        notificationType = NotificationType.NOTIFICATION;
    }
    @Test
    public void testNotificationEmptyConstructor(){
        Notification notification = new Notification();
        assertNotNull(notification);
    }
    @Test
    public void testNotificationConstructor(){
        Notification notification = new Notification(operator,CONTENT,NotificationStatus.SENT, localDate , userGroup, notificationType);
        assertNotNull(notification);
    }

    @Test
    public void testNotificationGettersAndSetters(){
        Notification notification = new Notification();
        notification.setContent(CONTENT);
        notification.setStatus(notificationStatus);
        notification.setUserGroup(userGroup);
        notification.setOperator(operator);
        notification.setId(ID);
        notification.setDate(localDate);
        notification.setNotificationType(notificationType);

        assertNotNull(notification);
        assertEquals(notification.getContent(), CONTENT);
        assertEquals(notification.getStatus().name(), notificationStatus.name());
        assertEquals(notification.getUserGroup(), userGroup);
        assertEquals(notification.getOperator(), operator);
        assertEquals(notification.getId(), ID);
        assertEquals(notification.getDate(),localDate);
    }

    @Test
    public void testNotificationStatusChange(){
        Notification notification = new Notification();
        notification.setContent(CONTENT);
        notification.setStatus(notificationStatus);
        notification.setUserGroup(userGroup);
        notification.setNotificationType(notificationType);

        notification.setStatus(NotificationStatus.CANCELED);
        assertNotNull(notification);
        assertNotEquals(notification.getStatus().name(), notificationStatus.name());
        assertEquals(notification.getStatus().name(), NotificationStatus.CANCELED.name());

    }

}
