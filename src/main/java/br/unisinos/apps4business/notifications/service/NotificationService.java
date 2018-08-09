package br.unisinos.apps4business.notifications.service;

import br.unisinos.apps4business.notifications.enumerator.NotificationStatus;
import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import br.unisinos.apps4business.notifications.model.Notification;
import br.unisinos.apps4business.notifications.model.Operator;
import br.unisinos.apps4business.notifications.model.UserGroup;
import br.unisinos.apps4business.notifications.repository.NotificationRepository;
import br.unisinos.apps4business.notifications.repository.UserGroupRespository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private NotificationRepository notificationRepository;
    private UserGroupRespository userGroupRespository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserGroupRespository userGroupRespository) {
        this.notificationRepository = notificationRepository;
        this.userGroupRespository = userGroupRespository;
    }

    public List<Notification> fetchAllNotifications(){
        List<Notification> list = new ArrayList<>();
        notificationRepository.findAll().forEach(notification -> list.add(notification));
        return list;
    }

    public List<Notification> findByUserGroup(UserGroup userGroup) {
        return notificationRepository.findByUserGroup(userGroup);
    }

    public List<Notification> findByStatus(NotificationStatus status){
        return notificationRepository.findByStatus(status);
    }
    public List<Notification> findByOperator(Operator operator){
        return notificationRepository.findByOperator(operator);
    }
    public List<Notification> findByDate(LocalDate localDate){
        return notificationRepository.findByDate(localDate);
    }

    public List<Notification> findByNotificationType(NotificationType notificationType) {
        return notificationRepository.findByNotificationType(notificationType);
    }

    public Boolean createNotification(Notification notification){
        if(notification.getDate() != null){
            return scheduleNotification(notification);
        }else{
            return sendNotification(notification);
        }
    }

    private Boolean sendNotification(Notification notification){
        notification.setStatus(NotificationStatus.SENT);
        notificationRepository.save(notification);
        return true;
    }
    public Boolean sendNotification(Long id){
        return notificationRepository.findById(id).map(notification -> {
            notification.setStatus(NotificationStatus.SENT);
            notificationRepository.save(notification);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }

    private Boolean scheduleNotification(Notification notification){
        notification.setStatus(NotificationStatus.SCHEDULED);
        notificationRepository.save(notification);
        return true;
    }
    public Boolean cancelNotification(Long id){
        return notificationRepository.findById(id).map(notification -> {
            notification.setStatus(NotificationStatus.CANCELED);
            notificationRepository.save(notification);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);

    }

    public List<UserGroup> fetchAllUserGroups() {
        List<UserGroup> list = new ArrayList<>();
        userGroupRespository.findAll().forEach(userGroup -> list.add(userGroup));
        return list;
    }


    public List<Notification> filerNotifications(String content, String operatorLogin, String userGroup, NotificationType notificationType, NotificationStatus status) {
        return notificationRepository.findByAll(content, operatorLogin, userGroup,  notificationType, status);
    }
}
