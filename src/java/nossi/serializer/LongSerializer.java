package nossi.serializer;

import static nossi.Util.resolveWrapperClass;

public class LongSerializer implements Serializer {

    @Override
    public String getName() {
        return "LongSerializer";
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }

    @Override
    public boolean match(Object obj) {
        return resolveWrapperClass(obj.getClass()).equals(getType());
    }

    @Override
    public String serialize(Object obj) {
        return String.valueOf((long)obj);
    }

}
