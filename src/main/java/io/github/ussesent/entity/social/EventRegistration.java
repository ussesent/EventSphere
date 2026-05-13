package io.github.ussesent.entity.social;

import io.github.ussesent.entity.auth.User;
import io.github.ussesent.entity.common.BaseEntity;
import io.github.ussesent.entity.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "event_registrations",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"event_id", "user_id"})
        },
        indexes = {
                @Index(name = "idx_event_id", columnList = "event_id"),
                @Index(name = "idx_user_id", columnList = "user_id")
        }
)
public class EventRegistration extends BaseEntity {

    @NotNull(message = "Событие обязательно")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User participant;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RegistrationStatus status;

}
