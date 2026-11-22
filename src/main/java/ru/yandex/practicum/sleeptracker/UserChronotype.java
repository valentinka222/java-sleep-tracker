package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserChronotype implements SleepAnalysis {
    private static final String DESCRIPTION = "Хронотип пользователя";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION,
                    "файл с сессиями сна пуст");
        }

        Map<Chronotype, Long> nightsByChronotype = sessions.stream()
                .filter(SleepingSession::isSleepNight)
                .map(UserChronotype::detectChronotype)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        return detectResult(nightsByChronotype);
    }

    private static Chronotype detectChronotype(SleepingSession session) {
        if ((session.start().getHour() < 6 || session.start().getHour() >= 23)
                && (session.finish().getHour() >= 9)) {
            return Chronotype.NIGHT_OWL;
        }
        if (session.start().getHour() <= 21 && session.start().getHour() >= 6
                && session.finish().getHour() <= 6) {
            return Chronotype.EARLY_BIRD;
        }
        return Chronotype.PIGEON;
    }

    private static SleepAnalysisResult detectResult(Map<Chronotype, Long> nightsByChronotype) {
        long nightOwl = nightsByChronotype.getOrDefault(Chronotype.NIGHT_OWL, 0L);
        long earlyBird = nightsByChronotype.getOrDefault(Chronotype.EARLY_BIRD, 0L);
        long pigeon = nightsByChronotype.getOrDefault(Chronotype.PIGEON, 0L);

        if (nightOwl > earlyBird && nightOwl > pigeon) {
            return new SleepAnalysisResult(DESCRIPTION,
                    Chronotype.NIGHT_OWL);
        }
        if (earlyBird > nightOwl && earlyBird > pigeon) {
            return new SleepAnalysisResult(DESCRIPTION,
                    Chronotype.EARLY_BIRD);
        }
        return new SleepAnalysisResult(DESCRIPTION,
                Chronotype.PIGEON);
    }
}
