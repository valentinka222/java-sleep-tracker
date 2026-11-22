package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.analysis.SleepTestData.LIST_FROM_EXAMPLE;

public class MaxSessionDurationTest {

    @Test
    @DisplayName("Максимальная сессия длиной в 585 минут")
    void fiveEightyFiveMinutesMaxSessionDuration() {
        SleepAnalysis maxSessionDuration = new MaxSessionDuration();
        assertEquals(585L, maxSessionDuration.apply(LIST_FROM_EXAMPLE).result());
    }

    @Test
    @DisplayName("Пустой список с записями о сессиях сна")
    void emptyListOfSleepLog() {
        SleepAnalysis maxSessionDuration = new MaxSessionDuration();
        List<SleepingSession> zeroSessions = List.of();
        assertEquals("файл с сессиями сна пуст", maxSessionDuration.apply(zeroSessions).result());
    }
}
