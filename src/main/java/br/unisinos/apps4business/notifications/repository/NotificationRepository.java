package br.unisinos.apps4business.notifications.repository;

import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.model.Operator;
import br.unisinos.apps4business.notifications.model.UserGroup;
import jdk.internal.jline.internal.Nullable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

    public List<Notification> findByUserGroup(UserGroup userGroup);
    public List<Notification> findByStatus(NotificationStatus status);
    public List<Notification> findByOperator(Operator operator);
    public List<Notification> findByDate(LocalDate date);
    public List<Notification> findByNotificationType(NotificationType notificationType);
    @Query("select n from notification where n.content like %?1 and n.operator.login like %?2 and n.userGroup.name like %?3 and n.notificationType = ?4 and n.status = ?5")
    public List<Notification> findByAll(@Nullable String content,
                                        @Nullable String operatorLogin,
                                        @Nullable String userGroup,
                                        @Nullable NotificationType notificationType,
                                        @Nullable NotificationStatus status
                                        );
}
