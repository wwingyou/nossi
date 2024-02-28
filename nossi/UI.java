package nossi;

import org.fusesource.jansi.*;

public class UI {
    public final static String RED = "\033[31m";
    public final static String GREEN = "\033[32m";
    public final static String NC = "\033[0m";
    public final static int STRING_LIMIT = 100;

    private static UI instance;
    private UI() {
    }

    private String getLimitedString(String line) {
        if (line.length() > STRING_LIMIT) {
            return line.substring(0, STRING_LIMIT) + "...";
        }
        return line;
    }

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }
        return instance;
    }

    public void printLine(String line) {
        System.out.println(getLimitedString(line));
    }

    public void wrong(String line) {
        line = getLimitedString(line);
        AnsiConsole.systemInstall();
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a(line).reset());
        AnsiConsole.systemUninstall();
    }

    public void correct(String line) {
        line = getLimitedString(line);
        AnsiConsole.systemInstall();
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a(line).reset());
        AnsiConsole.systemUninstall();
    }
}
