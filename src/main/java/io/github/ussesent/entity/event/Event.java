package io.github.ussesent.entity.event;

import io.github.ussesent.entity.auth.User;
import io.github.ussesent.entity.catalog.Category;
import io.github.ussesent.entity.catalog.Location;
import io.github.ussesent.entity.common.BaseEntity;
import io.github.ussesent.entity.social.EventRegistration;
import jakarta.persistence.*;
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

    @Column(name = "title", nullable = false)
    private String title;

    // TODO: определить более подходящий length
    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventRegistration> registrations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "access_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus status;

}
