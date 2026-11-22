package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalLong;

public class MaxSessionDuration implements SleepAnalysis {
    private static final String DESCRIPTION = "Продолжительность максимальной " +
            "сессии сна (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, "файл с сессиями сна пуст");
        }
        OptionalLong durationOfMaxSession = sessions.stream()
                .mapToLong(SleepingSession::durationInMinutes)
                .max();
        return new SleepAnalysisResult(DESCRIPTION, durationOfMaxSession.getAsLong());
    }
}
