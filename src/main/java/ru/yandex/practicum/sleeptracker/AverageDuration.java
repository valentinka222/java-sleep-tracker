package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalDouble;

public class AverageDuration implements SleepAnalysis {
    private static final String DESCRIPTION = "Средняя продолжительность сессий (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, "файл с сессиями сна пуст");
        }

        OptionalDouble averageDurationOfSessions = sessions.stream()
                .mapToLong(SleepingSession::durationInMinutes)
                .average();
        return new SleepAnalysisResult(DESCRIPTION,
                Math.round(averageDurationOfSessions.getAsDouble()));
    }
}
