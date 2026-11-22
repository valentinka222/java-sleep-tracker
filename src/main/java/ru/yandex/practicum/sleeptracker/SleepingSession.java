package ru.yandex.practicum.sleeptracker;


import java.time.Duration;
import java.time.LocalDateTime;

public record SleepingSession(LocalDateTime start, LocalDateTime finish, SleepQuality status) {

    public long durationInMinutes() {
        return Duration.between(start, finish).toMinutes();
    }

    @Override
    public String toString() {
        return "Сессия сна: " +
                "начало = " + start +
                ", конец = " + finish +
                ", качество сна = '" + status;
    }
}
