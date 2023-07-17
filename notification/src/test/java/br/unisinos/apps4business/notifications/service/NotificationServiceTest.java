package br.unisinos.apps4business.notifications.service;

import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import br.unisinos.apps4business.notifications.enumerator.Role;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.model.User;
import br.unisinos.apps4business.notifications.model.UserGroup;
import br.unisinos.apps4business.notifications.repository.NotificationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;
    private static final Long ID =1l;
    private Notification notification;
    private UserGroup userGroup;
    private User operator;
    private LocalDate localDate;
    private NotificationType notificationType;
    private static final String USER_LOGIN = "test login";
    private static final String NOTIFICATION_CONTENT = "test content";
    private static final String USER_GROUP_NAME = "test name";
    private Iterable<Notification> allNotifications;
    private Iterable<UserGroup> allUserGroups;
    private List<Notification> notificationList;
    @MockBean
    private NotificationRepository notificationRepository;

    @Before
    public void setup(){
        localDate = LocalDate.now();
        operator = random(User.class);
        operator.setRole(Role.OPERATOR);

        userGroup = random(UserGroup.class);
        notificationType = NotificationType.NOTIFICATION;
        notification = random(Notification.class);
        allNotifications = new ArrayList<>();
        ((ArrayList<Notification>) allNotifications).add(notification);
        allUserGroups = new ArrayList<>();
        ((ArrayList<UserGroup>) allUserGroups).add(userGroup);
        notificationList = new ArrayList<>();
        notificationList.add(notification);
    }


    @Test
    public void testFetchingAllNotifications(){
        Mockito.when(notificationRepository.findAll())
                .thenReturn(allNotifications);

        List<Notification> notifications = notificationService.fetchAllNotifications();
        assertNotNull(notifications);
        assertFalse(notifications.isEmpty());
    }
    @Test
    public void testFindByUserGroup(){
        Mockito.when(notificationRepository.findByUserGroup(Mockito.any(UserGroup.class)))
                .thenReturn(notificationList);

        List<Notification> notifications = notificationService.findByUserGroup(userGroup);
        assertNotNull(notifications);
        assertFalse(notifications.isEmpty());
    }
    @Test
    public void testFindByOperator(){
        Mockito.when(notificationRepository.findByUser(Mockito.any(User.class)))
                .thenReturn(notificationList);

        List<Notification> notifications = notificationService.findByOperator(operator);
        assertNotNull(notifications);
        assertFalse(notifications.isEmpty());
    }
    @Test
    public void testFindByNotificationStatus(){
        Mockito.when(notificationRepository.findByStatus(Mockito.any(NotificationStatus.class)))
                .thenReturn(notificationList);

        List<Notification> notifications = notificationService.findByStatus(NotificationStatus.SCHEDULED);
        assertNotNull(notifications);
        assertFalse(notifications.isEmpty());
    }
    @Test
    public void testFindByDate(){
        Mockito.when(notificationRepository.findByDate(Mockito.any(LocalDate.class)))
                .thenReturn(notificationList);

        List<Notification> notifications = notificationService.findByDate(localDate);
        assertNotNull(notifications);
        assertFalse(notifications.isEmpty());
    }

    @Test
    public void testFindByNotificationType(){
        Mockito.when(notificationRepository.findByNotificationType(Mockito.any(NotificationType.class)))
                .thenReturn(notificationList);

        List<Notification> notifications = notificationService.findByNotificationType(notificationType);
        assertNotNull(notifications);
        assertFalse(notifications.isEmpty());
    }
    @Test
    public void testFilterNotifications(){
        Mockito.when(notificationRepository.findByAll(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(NotificationType.class), Mockito.any(NotificationStatus.class)))
                .thenReturn(notificationList);

        List<Notification> notifications = notificationService.filerNotifications(NOTIFICATION_CONTENT, USER_LOGIN, USER_GROUP_NAME, NotificationType.NOTIFICATION, NotificationStatus.SENT);
        assertNotNull(notifications);
        assertFalse(notifications.isEmpty());
    }

    @Test
    public void testCreateNotificationWithSendStatus(){
        Mockito.when(notificationRepository.save(Mockito.any(Notification.class)))
                .thenReturn(notification);
        notification.setStatus(null);
        notification.setDate(null);
        Boolean notificationCreatedAndSent = notificationService.createNotification(notification);
        assertTrue(notificationCreatedAndSent);
    }

    @Test
    public void testCreateNotificationWithScheduledStatus(){
        Mockito.when(notificationRepository.save(Mockito.any(Notification.class)))
                .thenReturn(notification);
        notification.setStatus(null);
        notification.setDate(localDate);
        Boolean notificationCreatedAndScheduled = notificationService.createNotification(notification);
        assertTrue(notificationCreatedAndScheduled);
    }
    @Test
    public void testSendingNotification(){
        Mockito.when(notificationRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(notification));
        Mockito.when(notificationRepository.save(Mockito.any(Notification.class)))
                .thenReturn(notification);
        notification.setStatus(null);
        Boolean notificationSent = notificationService.sendNotification(ID);
        assertTrue(notificationSent);
    }

    @Test
    public void testCancelingNotification(){
        Mockito.when(notificationRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(notification));
        Mockito.when(notificationRepository.save(Mockito.any(Notification.class)))
                .thenReturn(notification);
        notification.setStatus(null);
        Boolean notificationCanceled = notificationService.cancelNotification(ID);
        assertTrue(notificationCanceled);
    }
}
