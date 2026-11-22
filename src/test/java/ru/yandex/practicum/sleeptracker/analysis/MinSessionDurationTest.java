package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.analysis.SleepTestData.LIST_FROM_EXAMPLE;

public class MinSessionDurationTest {


    @Test
    @DisplayName("Минимальная сессия длиной в 50 минут")
    void fiftyMinutesMinSessionDuration() {
        SleepAnalysis minSessionDuration = new MinSessionDuration();
        assertEquals(50L, minSessionDuration.apply(LIST_FROM_EXAMPLE).result());
    }

    @Test
    @DisplayName("Пустой список с записями о сессиях сна")
    void emptyListOfSleepLog() {
        SleepAnalysis minSessionDuration = new MinSessionDuration();
        List<SleepingSession> zeroSessions = List.of();
        assertEquals("файл с сессиями сна пуст", minSessionDuration.apply(zeroSessions).result());
    }
}
