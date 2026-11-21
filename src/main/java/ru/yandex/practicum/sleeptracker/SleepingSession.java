package ru.yandex.practicum.sleeptracker;


import java.time.LocalDateTime;

public record SleepingSession(LocalDateTime start, LocalDateTime finish, SleepQuality status) {

    @Override
    public String toString() {
        return "Сессия сна: " +
                "начало = " + start +
                ", конец = " + finish +
                ", качество сна = '" + status + "'\n";
    }
}
