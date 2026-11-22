package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.analysis.SleepTestData.LIST_FROM_EXAMPLE;

public class AverageDurationTest {
    @Test
    @DisplayName("Средняя длина сессий равна 396 минутам")
    void threeNinetySixMinutesAverageDuration() {
        SleepAnalysis averageDuration = new AverageDuration();
        assertEquals(396L, averageDuration.apply(LIST_FROM_EXAMPLE).result());
    }

    @Test
    @DisplayName("Пустой список с записями о сессиях сна")
    void emptyListOfSleepLog() {
        SleepAnalysis averageDuration = new AverageDuration();
        List<SleepingSession> zeroSessions = List.of();
        assertEquals("файл с сессиями сна пуст", averageDuration.apply(zeroSessions).result());
    }
}
