package nossi.serializer;

public interface Serializer {
    String getName();
    Class<?> getType();
    boolean match(Object obj);
    String serialize(Object obj);
}
