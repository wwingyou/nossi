package nossi.deserializer;

import static nossi.Util.isArrayElementSeparator;
import static nossi.Util.isArrayEndDelimiter;
import static nossi.Util.isArrayStartDelimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IntArrayDeserializer implements Deserializer {
    static final Pattern MATCH = Pattern.compile("^\\[[0-9]+");

    @Override
    public String getName() {
        return "IntArrayDeserializer";
    }

    @Override
    public Class<?> getType() {
        return int[].class;
    }

    @Override
    public boolean match(String valueString) {
        return IntArrayDeserializer.MATCH.matcher(valueString).find();
    }

    @Override
    public Object deserialize(String valueString) {
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : valueString.toCharArray()) {
            if (isArrayStartDelimiter(c)) continue;
            if (isArrayElementSeparator(c) || isArrayEndDelimiter(c)) {
                list.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        return list.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }

}
