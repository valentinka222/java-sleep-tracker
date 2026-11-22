package ru.yandex.practicum.sleeptracker;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public class SleeplessNights implements SleepAnalysis {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0L);
        }
        SleepingSession firstSession = sessions.getFirst();
        SleepingSession lastSession = sessions.getLast();
        long totalNights;

        if (firstSession.start().getHour() < 12) {
            totalNights = ChronoUnit.DAYS.between(firstSession.start().toLocalDate()
                    , lastSession.finish().toLocalDate()) + 1;
        } else {
            totalNights = ChronoUnit.DAYS.between(firstSession.start().toLocalDate()
                    , lastSession.finish().toLocalDate());
        }

        Predicate<SleepingSession> isSleepNight = (session ->
                (session.start().toLocalDate().isBefore(session.finish().toLocalDate()))
                        || (session.start().getHour() < 6)
        );

        long sleepNights = sessions.stream()
                .filter(isSleepNight)
                .map(s -> s.finish().toLocalDate())
                .distinct()
                .count();
        return new SleepAnalysisResult("Количество бессонных ночей",
                totalNights - sleepNights);
    }
}
