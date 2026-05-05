package io.github.ussesent.entity.auth;

import io.github.ussesent.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    // TODO: определить более подходящий length
    @Column(name = "bio", length = 500)
    private String bio;

    @Column(name = "avatar_storage_key")
    private String avatarStorageKey;
}
