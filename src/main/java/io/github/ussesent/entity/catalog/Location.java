package io.github.ussesent.entity.catalog;

import io.github.ussesent.entity.common.BaseEntity;
import io.github.ussesent.entity.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    @Column(name = "name")
    private String name;

    @NotNull(message = "Адрес не может быть пустым")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "latitude", nullable = false, precision = 10, scale = 7)
    private BigDecimal latitude; // широта

    @Column(name = "longitude", nullable = false, precision = 10, scale = 7)
    private BigDecimal longitude; // долгота

    @Column(name = "external_id")
    private String externalId; // внешний id от карт

    @Builder.Default
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

}
