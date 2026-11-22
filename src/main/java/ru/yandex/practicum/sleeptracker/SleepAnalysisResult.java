package ru.yandex.practicum.sleeptracker;

public record SleepAnalysisResult(String description, Object result) {

    @Override
    public String toString() {
        return description + ": " + result;
    }
}
