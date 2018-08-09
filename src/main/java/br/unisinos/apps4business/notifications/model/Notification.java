package br.unisinos.apps4business.notifications.model;

import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Operator operator;
    private String content;
    private NotificationStatus status;
    private LocalDate date;
    @ManyToOne
    private UserGroup userGroup;
    private NotificationType notificationType;
    public Notification(){};

    public Notification(Operator operator, String content, NotificationStatus status, LocalDate date, UserGroup userGroup, NotificationType notificationType) {
        this.operator = operator;
        this.content = content;
        this.status = status;
        this.date = date;
        this.userGroup = userGroup;
        this.notificationType = notificationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(operator, that.operator) &&
                Objects.equals(content, that.content) &&
                status == that.status &&
                Objects.equals(date, that.date) &&
                Objects.equals(userGroup, that.userGroup) &&
                notificationType == that.notificationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, content, status, date, userGroup, notificationType);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", operator=" + operator +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", userGroup=" + userGroup +
                ", notificationType=" + notificationType +
                '}';
    }
}
