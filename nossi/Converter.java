package nossi;

import java.util.ArrayList;
import java.util.List;

import nossi.deserializer.Deserializer;
import nossi.serializer.Serializer;

public class Converter {
    private static Converter instance;

    private List<Deserializer> deserializers;
    private List<Serializer> serializers;

    private Converter() {
        deserializers = new ArrayList<>();
        serializers = new ArrayList<>();
    }

    public static Converter getInstance() {
        if (instance == null) {
            instance = new Converter();
        }
        return instance;
    }

    public int autoload() {
        // TODO: autoload serializer, deserializer
        return 0;
    }

    public boolean applySerializer(Serializer serializer) {
        serializers.add(serializer);
        return true;
    }

    public boolean applyDeserializer(Deserializer deserializer) {
        deserializers.add(deserializer);
        return true;
    }

    public String serialize(Object obj) {
        for (Serializer s : serializers) {
            if (s.match(obj)) {
                return s.serialize(obj);
            }
        }
        return null;
    }

    public Object deserialize(String str) {
        for (Deserializer d : deserializers) {
            if (d.match(str)) {
                return d.deserialize(str);
            }
        }
        return null;
    }
}
