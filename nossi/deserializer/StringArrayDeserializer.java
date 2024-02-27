package nossi.deserializer;

import static nossi.Util.isArrayElementSeparator;
import static nossi.Util.isArrayEndDelimiter;
import static nossi.Util.isArrayStartDelimiter;
import static nossi.Util.isStringDelimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringArrayDeserializer implements Deserializer {
    static final Pattern MATCH = Pattern.compile("^\\[[\"']");

    @Override
    public String getName() {
        return "StringArrayDeserializer";
    }

    @Override
    public Class<?> getType() {
        return String[].class;
    }

    @Override
    public boolean match(String valueString) {
        return StringArrayDeserializer.MATCH.matcher(valueString).find();
    }

    @Override
    public Object deserialize(String valueString) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : valueString.toCharArray()) {
            if (isArrayStartDelimiter(c) || isStringDelimiter(c)) {
                continue;
            } else if (isArrayElementSeparator(c) || isArrayEndDelimiter(c)) {
                list.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        return list.stream()
            .toArray();
    }

}
