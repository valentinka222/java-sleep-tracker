package ru.yandex.practicum.sleeptracker.model;

public enum Chronotype {
    EARLY_BIRD("Жаворонок"),
    NIGHT_OWL("Сова"),
    PIGEON("Голубь");

    private final String label;

    Chronotype(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
