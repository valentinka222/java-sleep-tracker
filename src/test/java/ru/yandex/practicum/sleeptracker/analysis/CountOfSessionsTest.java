package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.analysis.SleepTestData.LIST_FROM_EXAMPLE;

public class CountOfSessionsTest {

    @Test
    @DisplayName("Количество сессий, равное нулю")
    void countOfSessionsEqualsZero() {
        SleepAnalysis countOfSessions = new CountOfSessions();
        List<SleepingSession> zeroSessions = List.of();
        assertEquals(0,
                countOfSessions.apply(zeroSessions).result());
    }

    @Test
    @DisplayName("Количество сессий, равное четырем")
    void countOfSessionsEqualsFour() {
        SleepAnalysis countOfSessions = new CountOfSessions();

        assertEquals(4,
                countOfSessions.apply(LIST_FROM_EXAMPLE).result());
    }
}
