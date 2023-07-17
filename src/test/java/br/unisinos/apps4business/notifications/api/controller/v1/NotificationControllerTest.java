package br.unisinos.apps4business.notifications.api.controller.v1;

import br.unisinos.apps4business.notifications.api.converter.v1.NotificationConverter;
import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.model.UserGroup;
import br.unisinos.apps4business.notifications.service.NotificationService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NotificationService notificationService;
    @MockBean
    private NotificationConverter notificationConverter;

    private Notification notification;
    private List<Notification> notificationList;
    private UserGroup userGroup;
    private List<UserGroup> userGroupList;
    private static final String USER_LOGIN = "test login";
    private static final String NOTIFICATION_CONTENT = "test content";
    private static final String USER_GROUP_NAME = "test name";
    private static final Long ID = 1l;

    @Before
    public void setup(){
        notification = random(Notification.class);
        notificationList = new ArrayList<>();
        notificationList.add(notification);

        userGroup = new UserGroup();
        userGroupList = new ArrayList<>();
        userGroupList.add(userGroup);
    }


    @Test
    @Ignore
    public void testCreateNotification()throws Exception{
        //TODO fix test
        Mockito.when(notificationService.createNotification(Mockito.any(Notification.class)))
                .thenReturn(Boolean.TRUE);
        mockMvc.perform(post("/v1/notifications")
                .contentType(APPLICATION_JSON)
                .content(notification.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testSendNotification()throws Exception{
        Mockito.when(notificationService.sendNotification(Mockito.anyLong()))
                .thenReturn(Boolean.TRUE);
        mockMvc.perform(patch("/v1/notifications/{id}", ID)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFetchAllNotifications()throws Exception{
        Mockito.when(notificationService.fetchAllNotifications())
                .thenReturn(notificationList);
        mockMvc.perform(get("/v1/notifications")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testCancelNotification()throws Exception{
        Mockito.when(notificationService.cancelNotification(Mockito.anyLong()))
                .thenReturn(Boolean.TRUE);
        mockMvc.perform(delete("/v1/notifications/{id}", ID)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFilerNotifications()throws Exception{
        Mockito.when(notificationService.filerNotifications(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(NotificationType.class), Mockito.any(NotificationStatus.class)))
                .thenReturn(notificationList);

        mockMvc.perform(get("/v1/notifications/filter")
                .param("content", NOTIFICATION_CONTENT)
                .param("operatorLogin", USER_LOGIN)
                .param("userGroup", USER_GROUP_NAME)
                .param("notificationType", NotificationType.NOTIFICATION.name())
                .param("status", NotificationStatus.SCHEDULED.name())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
