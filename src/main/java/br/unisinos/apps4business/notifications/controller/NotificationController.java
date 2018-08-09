package br.unisinos.apps4business.notifications.controller;

import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.model.UserGroup;
import br.unisinos.apps4business.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class NotificationController {
    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/usergroups", produces = "application/json")
    public List<UserGroup> fetchAllUserGroups() {
        return notificationService.fetchAllUserGroups();
    }

    @PostMapping(value = "/notifications", produces = "application/json")
    public Boolean createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @PatchMapping(value = "/notifications/{id}", produces = "application/json")
    public Boolean sendNotification(@PathVariable Long id) {
        return notificationService.sendNotification(id);
    }

    @GetMapping(value = "/notifications", produces = "application/json")
    public List<Notification> fetchAllNotifications() {
        return notificationService.fetchAllNotifications();
    }

    @DeleteMapping(value = "/notifications/{id}", produces = "application/json")
    public Boolean cancelNotification(@PathVariable Long id) {
        return notificationService.cancelNotification(id);
    }

    @GetMapping(value = "/notifications/filter", produces = "application/json")
    public List<Notification> filerNotifications(@RequestParam String content,
                                                 @RequestParam String operatorLogin,
                                                 @RequestParam String userGroup,
                                                 @RequestParam NotificationType notificationType,
                                                 @RequestParam NotificationStatus status) {
        return notificationService.filerNotifications(content,
                operatorLogin,
                userGroup,
                notificationType,
                status);
    }

}
