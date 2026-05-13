package io.github.ussesent.entity.auth;

import io.github.ussesent.entity.common.BaseEntity;
import io.github.ussesent.entity.event.Event;
import io.github.ussesent.entity.social.Comment;
import io.github.ussesent.entity.social.EventRegistration;
import io.github.ussesent.entity.social.Invitation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull(message = "Профиль обязателен")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    @NotNull(message = "Имя пользователя не может быть пустым")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull(message = "Пароль не может быть пустым")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "Email не может быть пустым")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @NotNull(message = "Роль обязательна")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Builder.Default
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> ownedEvents = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventRegistration> registrations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invitation> sentInvitations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invitation> receivedInvitations = new ArrayList<>();

    public void addOwnedEvent(Event event) {
        ownedEvents.add(event);
        event.setOwner(this);
    }

    public void removeOwnedEvent(Event event) {
        ownedEvents.remove(event);
        event.setOwner(null);
    }

    public void addRegistration(EventRegistration registration) {
        registrations.add(registration);
        registration.setParticipant(this);
    }

    public void removeRegistration(EventRegistration registration) {
        registrations.remove(registration);
        registration.setParticipant(null);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setAuthor(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setAuthor(null);
    }
}
