package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class SleeplessNights implements SleepAnalysis {
    private static final String DESCRIPTION = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0L);
        }
        SleepingSession firstSession = sessions.getFirst();
        SleepingSession lastSession = sessions.getLast();
        long totalNights;

        if (firstSession.start().getHour() < 12) {
            totalNights = ChronoUnit.DAYS.between(firstSession.start().toLocalDate(),
                    lastSession.finish().toLocalDate()) + 1;
        } else {
            totalNights = ChronoUnit.DAYS.between(firstSession.start().toLocalDate(),
                    lastSession.finish().toLocalDate());
        }

        long sleepNights = sessions.stream()
                .filter(SleepingSession::isSleepNight)
                .map(s -> s.finish().toLocalDate())
                .distinct()
                .count();
        return new SleepAnalysisResult(DESCRIPTION,
                totalNights - sleepNights);
    }
}
