package nossi.serializer;

public class IntSerializer implements Serializer {

    @Override
    public String getName() {
        return "IntSerializer";
    }

    @Override
    public Class<?> getType() {
        return Integer.class;
    }

    @Override
    public boolean match(Object obj) {
        return obj.getClass().equals(getType());
    }

    @Override
    public String serialize(Object obj) {
        return String.valueOf((int)obj);
    }

}
