package br.unisinos.apps4business.notifications.api.dto.v1;

import br.unisinos.apps4business.notifications.enumerator.NotificationType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
public class NotificationRequestDTO {
    @NotNull
    private Long operatorId;
    @NotBlank
    private String content;
    private LocalDate date;
    private Long userGroupId;
    @NotNull
    private NotificationType notificationType;
}
