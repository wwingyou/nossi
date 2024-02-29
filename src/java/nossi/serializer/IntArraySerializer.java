package nossi.serializer;

public class IntArraySerializer implements Serializer {

    @Override
    public String getName() {
        return "IntArraySerializer";
    }

    @Override
    public Class<?> getType() {
        return int[].class;
    }

    @Override
    public boolean match(Object obj) {
        return obj.getClass().equals(getType());
    }

    @Override
    public String serialize(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int[] arr = (int[])obj;
        for (int e : arr) {
            sb.append(e);
            sb.append(",");
        }
        sb.delete(sb.length()-1, sb.length());
        sb.append(']');
        return sb.toString();
    }

}
