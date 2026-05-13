package io.github.ussesent.entity.event;

import io.github.ussesent.entity.auth.User;
import io.github.ussesent.entity.catalog.Category;
import io.github.ussesent.entity.catalog.Location;
import io.github.ussesent.entity.common.BaseEntity;
import io.github.ussesent.entity.social.Comment;
import io.github.ussesent.entity.social.EventRegistration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class Event extends BaseEntity {

    @NotNull(message = "Владелец обязателен")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "banner_attachment_id")
    private Attachment banner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "place_details", length = 255)
    private String placeDetails;

    @NotNull(message = "Название не может быть пустым")
    @Column(name = "title", nullable = false)
    private String title;

    // TODO: определить более подходящий length
    @Column(name = "description", length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventStatus status;

    @NotNull(message = "Тип доступа обязателен")
    @Enumerated(EnumType.STRING)
    @Column(name = "access_type", nullable = false)
    private AccessType accessType;

    @NotNull(message = "Время начала обязательно")
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания обязательно")
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventRegistration> registrations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void addRegistration(EventRegistration registration) {
        registrations.add(registration);
        registration.setEvent(this);
    }

    public void removeRegistration(EventRegistration registration) {
        registrations.remove(registration);
        registration.setEvent(null);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setEvent(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setEvent(null);
    }
}
