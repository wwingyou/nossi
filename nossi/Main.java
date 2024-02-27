package nossi;

import static nossi.Util.normalize;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import nossi.deserializer.DoubleDeserializer;
import nossi.deserializer.Int2DArrayDeserializer;
import nossi.deserializer.IntArrayDeserializer;
import nossi.deserializer.IntDeserializer;
import nossi.deserializer.StringArrayDeserializer;
import nossi.deserializer.StringDeserializer;
import nossi.serializer.IntArraySerializer;
import nossi.serializer.IntSerializer;
import nossi.serializer.StringSerializer;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException, IllegalArgumentException, IOException {
        Converter converter = Converter.getInstance();
        converter.applyDeserializer(new IntDeserializer());
        converter.applyDeserializer(new StringDeserializer());
        converter.applyDeserializer(new IntArrayDeserializer());
        converter.applyDeserializer(new StringArrayDeserializer());
        converter.applyDeserializer(new DoubleDeserializer());
        converter.applyDeserializer(new Int2DArrayDeserializer());

        converter.applySerializer(new IntSerializer());
        converter.applySerializer(new StringSerializer());
        converter.applySerializer(new IntArraySerializer());

        BufferedReader sr = new BufferedReader(new InputStreamReader(System.in));
        final Pattern VAR_MATCH = Pattern.compile("^[a-zA-Z_-]+=(.*)$");

        final List<Object> argumentList = new ArrayList<>();
        sr.lines().forEach(line -> {
            Matcher matcher = VAR_MATCH.matcher(normalize(line));
            if (matcher.find()) {
                Object arg = converter.deserialize(matcher.group(1));
                argumentList.add(arg);
            }
        });
        Class<?>[] typeArray = argumentList.stream()
            .map(arg -> arg.getClass())
            .toArray(n -> new Class<?>[n]);
        Object[] argArray = argumentList.toArray();
        // 현재 작업 디렉토리 가져오기
        String currentDir = System.getProperty("user.dir");
        // URLClassLoader를 사용하여 클래스 로드
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(currentDir).toURI().toURL()});
        // 클래스 로드
        Class<?> solutionClass = classLoader.loadClass("Solution");
        classLoader.close();

        Method solveMethod = solutionClass.getMethod("solve", typeArray);
        Object result = solveMethod.invoke(solutionClass.getDeclaredConstructor().newInstance(), argArray);
        System.out.println(converter.serialize(result));
    }
}
