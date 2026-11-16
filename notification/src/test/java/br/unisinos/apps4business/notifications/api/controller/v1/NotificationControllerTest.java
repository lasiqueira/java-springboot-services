package br.unisinos.apps4business.notifications.api.controller.v1;

import br.unisinos.apps4business.notifications.api.converter.v1.NotificationConverter;
import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.model.UserGroup;
import br.unisinos.apps4business.notifications.service.NotificationService;
import br.unisinos.apps4business.notifications.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private NotificationService notificationService;
    @MockitoBean
    private NotificationConverter notificationConverter;

    private Notification notification;
    private List<Notification> notificationList;
    private UserGroup userGroup;
    private List<UserGroup> userGroupList;
    private static final String USER_LOGIN = "test login";
    private static final String NOTIFICATION_CONTENT = "test content";
    private static final String USER_GROUP_NAME = "test name";
    private static final Long ID = 1l;

    @BeforeEach
    public void setup(){
        notification = TestData.notification();
        notificationList = new ArrayList<>();
        notificationList.add(notification);

        userGroup = new UserGroup();
        userGroupList = new ArrayList<>();
        userGroupList.add(userGroup);
    }


    @Test
    public void testCreateNotification()throws Exception{
        Mockito.when(notificationService.createNotification(Mockito.any()))
                .thenReturn(Boolean.TRUE);

        mockMvc.perform(post("/v1/notifications")
                .contentType(APPLICATION_JSON)
                .content("""
                        {"operatorId": 1, "content": "test content", "notificationType": "ALERT", "userGroupId": 1}
                        """))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateNotificationRejectsBlankContent()throws Exception{
        mockMvc.perform(post("/v1/notifications")
                .contentType(APPLICATION_JSON)
                .content("""
                        {"operatorId": 1, "content": " ", "notificationType": "ALERT"}
                        """))
                .andExpect(status().isBadRequest());
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
