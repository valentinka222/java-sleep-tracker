package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class CountOfSessions implements SleepAnalysis {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        return new SleepAnalysisResult("Количество сессий", sessions.size());
    }
}
