package nossi.deserializer;

import java.util.regex.Pattern;

public class IntDeserializer implements Deserializer {
    static final Pattern MATCH = Pattern.compile("^[0-9]+$");

    @Override
    public String getName() {
        return "IntDeserializer";
    }

    @Override
    public Class<?> getType() {
        return int.class;
    }

    @Override
    public boolean match(String valueString) {
        return IntDeserializer.MATCH.matcher(valueString).find();
    }

    @Override
    public Object deserialize(String valueString) {
        return Integer.parseInt(valueString);
    }
}
