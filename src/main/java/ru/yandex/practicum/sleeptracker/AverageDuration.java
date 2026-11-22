package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalDouble;

public class AverageDuration implements SleepAnalysis {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Средняя продолжительность " +
                    "сессий(в минутах)", "файл с сессиями сна пуст");
        }

        OptionalDouble averageDurationOfSessions = sessions.stream()
                .mapToLong(SleepingSession::durationInMinutes)
                .average();
        return new SleepAnalysisResult("Средняя продолжительность " +
                "сессий(в минутах)", Math.round(averageDurationOfSessions.getAsDouble()));
    }
}
