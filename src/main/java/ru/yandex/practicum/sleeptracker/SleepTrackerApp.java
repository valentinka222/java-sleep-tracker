package ru.yandex.practicum.sleeptracker;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class SleepTrackerApp {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yy HH:mm");
    private static final List<SleepAnalysis> LIST_OF_FUNCTIONS = getFunctions();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите путь до файла");
            return;
        }
        List<SleepingSession> sessions;
        try {
            sessions = getSessions(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует:\n" + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода-вывода:\n" + e.getMessage());
            return;
        } catch (UncheckedIOException e) {
            System.out.println("Чтение файла сорвалось:\n" + e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Неправильно оформлены поля файла:\n" + e.getMessage());
            return;
        }

        System.out.println("Анализ сна по файлу: " + args[0] + "\n");
        LIST_OF_FUNCTIONS.forEach(f -> System.out.println(f.apply(sessions)));
    }

    public static List<SleepingSession> getSessions(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines()
                    .filter(s -> !s.isBlank())
                    .map(SleepTrackerApp::parseLine)
                    .toList();
        }
    }

    public static SleepingSession parseLine(String s) {
        String[] line = s.split(";");
        if (line.length != 3) {
            throw new IllegalArgumentException("Неверное количество полей в строке: " + s);
        }
        try {
            return new SleepingSession(
                    LocalDateTime.parse(line[0], FORMATTER),
                    LocalDateTime.parse(line[1], FORMATTER),
                    SleepQuality.valueOf(line[2])
            );
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверное значение строки " + s + ": "
                    + e.getMessage(), e);
        }
    }

    private static List<SleepAnalysis> getFunctions() {
        SleepAnalysis countOfSessions = new CountOfSessions();
        SleepAnalysis minSessionDuration = new MinSessionDuration();
        SleepAnalysis maxSessionDuration = new MaxSessionDuration();
        SleepAnalysis averageDuration = new AverageDuration();
        SleepAnalysis countOfBadSessions = new CountOfBadSessions();
        SleepAnalysis sleeplessNights = new SleeplessNights();
        SleepAnalysis userChronotype = new UserChronotype();

        return List.of(countOfSessions, minSessionDuration, maxSessionDuration,
                averageDuration, countOfBadSessions, sleeplessNights, userChronotype);
    }
}
