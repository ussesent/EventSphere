package io.github.ussesent.entity.system;

import io.github.ussesent.entity.auth.User;
import io.github.ussesent.entity.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

    // Кому предназначено уведомление
    @NotNull(message = "Получатель обязателен")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    // Текст уведомления (например, "Ваш проект 'Java Core' одобрен")
    @NotBlank(message = "Текст уведомления не может быть пустым")
    @Column(nullable = false)
    private String message;

    // Ссылка, куда перейдет юзер при клике (например, "/events/12")
    @Column(name = "link")
    private String link;

    // Статус прочтения
    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType type;
}
