package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class CountOfBadSessions implements SleepAnalysis {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        return new SleepAnalysisResult("Количество плохих сессий",
                sessions.stream().filter(s -> (s.status() == SleepQuality.BAD)).count());
    }
}
