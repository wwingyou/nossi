package nossi;

import org.fusesource.jansi.*;

public class UI {
    public final static String RED = "\033[31m";
    public final static String GREEN = "\033[32m";
    public final static String NC = "\033[0m";

    private static UI instance;
    private UI() {
        AnsiConsole.systemInstall();
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
        System.out.println(UI.RED + line + UI.NC);
    }

    public void correct(String line) {
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a(line).reset());
    }

    public void compose(String saturatedLine) {
        System.out.println(saturatedLine);
    }
}
