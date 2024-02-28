package nossi.serializer;

import static nossi.Util.resolveWrapperClass;

public class IntSerializer implements Serializer {

    @Override
    public String getName() {
        return "IntSerializer";
    }

    @Override
    public Class<?> getType() {
        return int.class;
    }

    @Override
    public boolean match(Object obj) {
        return resolveWrapperClass(obj.getClass()).equals(getType());
    }

    @Override
    public String serialize(Object obj) {
        return String.valueOf((int)obj);
    }

}
