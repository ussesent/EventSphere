package io.github.ussesent.entity.system;

public enum NotificationType {
    INVITATION,   // Тебя зовут куда-то
    REGISTRATION, // Твою заявку приняли / отклонили (ИДЕАЛЬНО ДЛЯ ЭТОГО СЛУЧАЯ)
    WEATHER,      // Алерты по погоде
    SOCIAL,       // Лайки, комментарии, подписки
    SYSTEM        // Пароли, безопасность, апдейты системы
}