package nossi.deserializer;

import java.util.regex.Pattern;

public class DoubleDeserializer implements Deserializer {
    static final Pattern MATCH = Pattern.compile("^[1-9]+.[0-9]+$");

    @Override
    public String getName() {
        return "DoubleDeserializer";
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }

    @Override
    public boolean match(String valueString) {
        return DoubleDeserializer.MATCH.matcher(valueString).find();
    }

    @Override
    public Object deserialize(String valueString) {
        return Double.parseDouble(valueString);
    }

}
