package nossi;

import static nossi.Util.normalize;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
import nossi.serializer.LongSerializer;
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
        converter.applySerializer(new LongSerializer());
        converter.applySerializer(new StringSerializer());
        converter.applySerializer(new IntArraySerializer());

        // 현재 작업 디렉토리 가져오기
        String currentDir = System.getProperty("user.dir");
        // URLClassLoader를 사용하여 클래스 로드
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(currentDir).toURI().toURL()});
        // 클래스 로드
        Class<?> solutionClass = classLoader.loadClass("Solution");
        classLoader.close();

        BufferedReader sr = new BufferedReader(new InputStreamReader(System.in));
        UI ui = UI.getInstance();
        final Pattern TESTCASE_MATCH = Pattern.compile("^testcase([0-9]+)");
        final Pattern VAR_MATCH = Pattern.compile("^[a-zA-Z_-]+=(.*)$");
        final Pattern RESULT_MATCH = Pattern.compile("^result=(.+)");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<Object> argumentList = new ArrayList<>();
        String testIndex = "0";
        Matcher matcher;

        int correct = 0;
        int wrong = 0;

        for (String line : sr.lines().toArray(String[]::new)) {
            String normalized = normalize(line);
            matcher = TESTCASE_MATCH.matcher(normalized);
            if (matcher.find()) {
                testIndex = matcher.group(1);
                continue;
            }
            matcher = RESULT_MATCH.matcher(normalized);
            if (matcher.find()) {
                ui.printLine("-- testcase " + testIndex + " --");
                String result = matcher.group(1);
                Runner runner = new Runner("solve", solutionClass, argumentList);
                Future<Object> futureResult = executor.submit(runner::call);
                ui.printLine("출력");
                try {
                    String output = converter.serialize(futureResult.get(10, TimeUnit.SECONDS));
                    if (output.equals(result)) {
                        correct++;
                        ui.correct(output);
                    } else {
                        wrong++;
                        ui.wrong(output);
                    }
                } catch (TimeoutException e) {
                    ui.wrong("Time Limit Exceed");
                    wrong++;
                } catch (Exception e) {
                    ui.wrong("Runtime Error");
                    ui.wrong(e.getMessage());
                    e.printStackTrace();
                    wrong++;
                }
                ui.printLine("정답");
                ui.printLine(normalize(result));
                ui.printLine("");

                argumentList.clear();
                continue;
            }
            matcher = VAR_MATCH.matcher(normalized);
            if (matcher.find()) {
                Object arg = converter.deserialize(matcher.group(1));
                argumentList.add(arg);
            }
        }

        executor.shutdown();
        ui.printLine("총 " + (correct + wrong) + "문제, 정답 " + correct + ", 오답 " + wrong);
        System.exit(0);
    }
}
