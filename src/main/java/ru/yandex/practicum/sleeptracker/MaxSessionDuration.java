package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalLong;

public class MaxSessionDuration implements SleepAnalysis {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Продолжительность максимальной сессии " +
                    "сна(в минутах)", "файл с сессиями сна пуст");
        }
        OptionalLong durationOfMaxSession = sessions.stream()
                .mapToLong(SleepingSession::durationInMinutes)
                .max();
        return new SleepAnalysisResult("Продолжительность максимальной сессии " +
                "сна(в минутах)", durationOfMaxSession.getAsLong());
    }
}
