package nossi.deserializer;

public interface Deserializer {
    String getName();
    Class<?> getType();
    boolean match(String valueString);
    Object deserialize(String valueString);
}
