package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface SleepAnalysis extends Function<List<SleepingSession>, SleepAnalysisResult> {
}
