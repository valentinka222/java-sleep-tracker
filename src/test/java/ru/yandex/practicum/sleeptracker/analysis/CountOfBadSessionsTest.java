package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.analysis.SleepTestData.LIST_FROM_EXAMPLE;

public class CountOfBadSessionsTest {

    @Test
    @DisplayName("Количество плохих сессий в пустом списке равно нулю")
    void zeroBadSessionsInEmptyList() {
        SleepAnalysis countOfBadSessions = new CountOfBadSessions();
        List<SleepingSession> zeroSessions = List.of();
        assertEquals(0L,
                countOfBadSessions.apply(zeroSessions).result());
    }

    @Test
    @DisplayName("Одна плохая сессия в списке из четырех сессий")
    void oneBadSession() {
        SleepAnalysis countOfBadSessions = new CountOfBadSessions();
        assertEquals(1L,
                countOfBadSessions.apply(LIST_FROM_EXAMPLE).result());
    }

}
