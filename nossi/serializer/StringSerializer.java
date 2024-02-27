package nossi.serializer;

public class StringSerializer implements Serializer {

    @Override
    public String getName() {
        return "StringSerializer";
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }

    @Override
    public boolean match(Object obj) {
        return obj.getClass().equals(getType());
    }

    @Override
    public String serialize(Object obj) {
        return "\"" + (String)obj + "\"";
    }

}
