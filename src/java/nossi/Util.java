package nossi;

public class Util {
    public static Class<?> resolveWrapperClass(Class<?> clazz) {
        if (clazz.equals(Integer.class)) return int.class;
        if (clazz.equals(Double.class)) return double.class;
        return clazz;
    }

    public static String normalize(String valueString) {
        StringBuilder sb = new StringBuilder();
        boolean escape = false;
        boolean inString = false;
        for (char c : valueString.toCharArray()) {
            if (inString) {
                if (escape) {
                    escape = false;
                    sb.append(c);
                } else {
                    if (c == '\\') {
                        escape = true;
                    } else if (isStringDelimiter(c)) {
                        inString = false;
                    } 
                    sb.append(c);
                }
            } else {
                if (isWhiteSpace(c)) continue;
                if (isStringDelimiter(c)) inString = true;
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static boolean isWhiteSpace(char c) {
        return c == '\n' || c == '\t' || c == ' ' || c == '\r';
    }

    public static boolean isStringDelimiter(char c) {
        return c == '"' || c == '\'';
    }

    public static boolean isArrayStartDelimiter(char c) {
        return c == '[';
    }

    public static boolean isArrayEndDelimiter(char c) {
        return c == ']';
    }

    public static boolean isArrayElementSeparator(char c) {
        return c == ',';
    }
}
