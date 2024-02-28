package nossi.deserializer;

import java.util.regex.Pattern;

public class StringDeserializer implements Deserializer {
    static final Pattern MATCH = Pattern.compile("^\"[a-zA-Z]+\"$");

    @Override
    public String getName() {
        return "StringDeserializer";
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }

    @Override
    public boolean match(String valueString) {
        return StringDeserializer.MATCH.matcher(valueString).find();
    }

    @Override
    public Object deserialize(String valueString) {
        return valueString.substring(1, valueString.length() - 1);
    }

}
