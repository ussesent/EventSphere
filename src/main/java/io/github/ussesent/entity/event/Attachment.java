package io.github.ussesent.entity.event;

import io.github.ussesent.entity.auth.User;
import io.github.ussesent.entity.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "attachments")
public class Attachment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id", nullable = false)
    private User uploader;

    @NotBlank(message = "Ключ хранилища не может быть пустым")
    @Column(name = "storage_key", nullable = false, unique = true)
    private String storageKey;

    @NotBlank(message = "Тип контента не может быть пустым")
    @Column(name = "content_type", nullable = false)
    private String contentType;

    @NotNull(message = "Размер файла обязателен")
    @PositiveOrZero(message = "Размер файла не может быть отрицательным")
    @Column(name = "size", nullable = false)
    private Long size;

}