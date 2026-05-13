package io.github.ussesent.entity.social;

import io.github.ussesent.entity.auth.User;
import io.github.ussesent.entity.common.BaseEntity;
import io.github.ussesent.entity.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invitations")
public class Invitation extends BaseEntity {

    @NotNull(message = "Отправитель обязателен")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @NotNull(message = "Получатель обязателен")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @NotNull(message = "Событие обязательно")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @NotNull(message = "Статус приглашения обязателен")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InvitationStatus status;

    @Column(name = "message", length = 500)
    private String message;
}