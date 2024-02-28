package nossi;

import org.fusesource.jansi.*;

public class UI {
    public final static String RED = "\033[31m";
    public final static String GREEN = "\033[32m";
    public final static String NC = "\033[0m";

    private static UI instance;
    private UI() {
    }

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }
        return instance;
    }

    public void printLine(String line) {
        System.out.println(line);
    }

    public void wrong(String line) {
        AnsiConsole.systemInstall();
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a(line).reset());
        AnsiConsole.systemUninstall();
    }

    public void correct(String line) {
        AnsiConsole.systemInstall();
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a(line).reset());
        AnsiConsole.systemUninstall();
    }
}
