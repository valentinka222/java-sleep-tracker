package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;
import java.util.OptionalLong;

public class MinSessionDuration implements SleepAnalysis {
    private static final String DESCRIPTION = "Продолжительность минимальной " +
            "сессии сна (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, "файл с сессиями сна пуст");
        }
        OptionalLong durationOfMinSession = sessions.stream()
                .mapToLong(SleepingSession::durationInMinutes)
                .min();
        return new SleepAnalysisResult(DESCRIPTION, durationOfMinSession.getAsLong());

    }
}
