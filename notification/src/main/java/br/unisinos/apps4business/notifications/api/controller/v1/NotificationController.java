package br.unisinos.apps4business.notifications.api.controller.v1;

import br.unisinos.apps4business.notifications.api.converter.v1.NotificationConverter;
import br.unisinos.apps4business.notifications.api.dto.v1.NotificationRequestDTO;
import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/notifications")
public class NotificationController {
    private NotificationService notificationService;
    private NotificationConverter notificationConverter;

    public NotificationController(NotificationService notificationService, NotificationConverter notificationConverter) {
        this.notificationService = notificationService;
        this.notificationConverter = notificationConverter;
    }

    @PostMapping(value = "", produces = "application/json")
    public Boolean createNotification(@RequestBody @Valid NotificationRequestDTO request) {
        return notificationService.createNotification(notificationConverter.convertNotificationRequestDTOToNotification(request));
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public Boolean sendNotification(@PathVariable Long id) {
        return notificationService.sendNotification(id);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Notification> fetchAllNotifications() {
        return notificationService.fetchAllNotifications();
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public Boolean cancelNotification(@PathVariable Long id) {
        return notificationService.cancelNotification(id);
    }

    @GetMapping(value = "/filter", produces = "application/json")
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
