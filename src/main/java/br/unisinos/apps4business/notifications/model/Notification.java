package br.unisinos.apps4business.notifications.model;

import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    private String content;
    private NotificationStatus status;
    private LocalDate date;
    @ManyToOne
    private UserGroup userGroup;
    private NotificationType notificationType;


}
