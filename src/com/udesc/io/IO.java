package com.udesc.io;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.udesc.Option;

public class IO {
    public static void clear() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static void divider(Integer count) {
        System.out.println(("-").repeat(count) + "\n");
    }

    public static void divider() {
        divider(50);
    }

    public static void present(Option option) {
        System.out.println(String.format(
            "%s\n", option.getName().toUpperCase()
        ));
    }

    public static String ask(String question) {
        return System.console().readLine(String.format("%s: ", question));
    }

    public static LocalDateTime askDate(String question) {
        try {
            final var pattern = "dd/MM/yyyy HH:mm";

            return LocalDateTime.parse(
                ask(String.format("%s (%s)", question, pattern)),
                DateTimeFormatter.ofPattern(pattern)
            );

        } catch(DateTimeParseException e) {
            error("Data inválida. Tente novamente.");

            return askDate(question);
        }
    }

    public static LocalTime askTime(String question) {
        try {
            final var pattern = "HH:mm";

            return LocalTime.parse(
                ask(String.format("%s (%s)", question, pattern)),
                DateTimeFormatter.ofPattern(pattern)
            );

        } catch(DateTimeParseException e) {
            error("Tempo inválido. Tente novamente.");

            return askTime(question);
        }
    }

    public static boolean confirm(String question) {
        return List.of("S", "SIM").contains(
            ask(String.format("%s (S/N)", question)).toUpperCase()
        );
    }

    public static Option chooseOption() {
        try {
            for (var i = 0; i < Option.values().length; i++) {
                System.out.println(String.format(
                    "%s) %s", String.format("%2d", (i + 1)), Option.at(i).getName()
                ));
            }

            System.out.println();

            final var option = Option.at(Integer.parseInt(ask("Opcão")) - 1);
            System.out.println();

            return option;

        } catch(NumberFormatException|IndexOutOfBoundsException e) {
            error("Opção inválida. Tente novamente.");

            return chooseOption();
        }
    }

    public static void list(List<? extends Viewable> list, String name) {
        System.out.println(String.format("📋 %s:\n", name));

        int i = 0;

        for (Viewable item : list) {
            System.out.println(String.format("%s) %s", i, item.briefView()));

            i++;
        }

        System.out.println();
    }

    public static <T extends Viewable> T chooseFromList(List<T> list, String name) {
        try {
            list(list, name);

            final var index = Integer.parseInt(ask("Opcão"));
            System.out.println();
    
            return list.get(index);

        } catch(NumberFormatException|IndexOutOfBoundsException e) {
            error("Opção inválida. Tente novamente.");

            return chooseFromList(list, name);
        }
    }

    public static void pause() {
        System.console().readLine("Pressione ENTER para continuar ...");
        clear();
    }

    public static void view(Viewable viewable) {
        System.out.println(viewable.view());
    }

    public static void info(String message) {
        System.out.println(String.format("\nℹ️  %s\n", message));
    }

    public static void error(String message) {
        System.err.println(String.format("\n🚫 %s\n", message));
    }
}
