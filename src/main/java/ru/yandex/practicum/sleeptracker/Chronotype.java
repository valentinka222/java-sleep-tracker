package ru.yandex.practicum.sleeptracker;

public enum Chronotype {
    EARLY_BIRD("Жаворонок"),
    NIGHT_OWL("Сова"),
    PIGEON("Голубь");

    private final String label;

    Chronotype(String chronotype) {
        this.label = chronotype;
    }

    @Override
    public String toString() {
        return label;
    }
}
