package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.yandex.practicum.sleeptracker.SleepTrackerApp.parseLine;

public class SleepTrackerAppTest {

    @Test
    @DisplayName("Строка верная, разбирается в правильную сессию, исключения не вызываются")
    void withoutException() {
        String normalSession = "01.10.25 22:15;02.10.25 08:00;GOOD";
        assertEquals(new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 15),
                        LocalDateTime.of(2025, 10, 2, 8, 0),
                        SleepQuality.GOOD
                ),
                parseLine(normalSession)
        );
    }

    @Test
    @DisplayName("Строка из двух полей вызовет IllegalArgumentException")
    void illegalArgumentExceptionCount() {
        String twoValues = "01.10.25 22:15;02.10.25 08:00";
        assertThrows(
                IllegalArgumentException.class,
                () -> parseLine(twoValues)
        );
    }

    @Test
    @DisplayName("Строка с неверным значением у поля status вызовет IllegalArgumentException")
    void illegalArgumentExceptionValue() {
        String excellent = "01.10.25 22:15;02.10.25 08:00;EXCELLENT";
        assertThrows(
                IllegalArgumentException.class,
                () -> parseLine(excellent)
        );
    }

    @Test
    @DisplayName("Строка с неверной датой вызовет IllegalArgumentException")
    void illegalArgumentExceptionDate() {
        String wrongDate = "01.10.25 22:15;02.13.25 08:00;GOOD";
        assertThrows(
                IllegalArgumentException.class,
                () -> parseLine(wrongDate)
        );
    }
}