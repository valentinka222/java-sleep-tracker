package ru.yandex.practicum.sleeptracker.analysis;

public record SleepAnalysisResult(String description, Object result) {

    @Override
    public String toString() {
        return description + ": " + result;
    }
}
