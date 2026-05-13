package io.github.ussesent.entity.event;

import io.github.ussesent.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "weather_cache", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"city", "target_time"})
})
public class WeatherCache extends BaseEntity {

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "target_time", nullable = false)
    private LocalDateTime targetTime;

    @Column(name = "temperature", nullable = false)
    private Integer temperature;

    @Column(name = "description")
    private String description;

}
