package io.github.ussesent.entity.auth;

import io.github.ussesent.entity.common.BaseEntity;
import io.github.ussesent.entity.event.Attachment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @NotNull(message = "Пользователь обязателен")
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "avatar_attachment_id")
    private Attachment avatar;

    @NotNull(message = "Отображаемое имя не может быть пустым")
    @Column(name = "display_name", nullable = false)
    private String displayName;

    // TODO: определить более подходящий length
    @Column(name = "bio", length = 500)
    private String bio;

}
