package io.github.ussesent.entity.catalog;

import io.github.ussesent.entity.common.BaseEntity;
import io.github.ussesent.entity.event.Event;
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
@Table(name = "categories")
public class Category extends BaseEntity {

    @NotNull(message = "Название категории не может быть пустым")
    @Column(name = "name", nullable = false, unique = true)
    private String name; // translation Key for i18n

    @NotNull(message = "Slug не может быть пустым")
    @Column(name = "slug", nullable = false, unique = true)
    private String slug; // for searching the database and using in URLs

    @Builder.Default
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

}
