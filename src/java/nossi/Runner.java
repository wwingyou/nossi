package nossi;

import static nossi.Util.resolveWrapperClass;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;

public class Runner implements Callable<Object> {
    String methodName;
    Class<?> solutionClass;
    List<Object> args;

    public Runner(String methodName, Class<?> solutionClass, List<Object> args) {
        this.methodName = methodName;
        this.solutionClass = solutionClass;
        this.args = args;
    }

    @Override
    public Object call() throws Exception {
        Class<?>[] typeArray = args.stream()
            .map(arg -> resolveWrapperClass(arg.getClass()))
            .toArray(n -> new Class<?>[n]);
        Object[] argArray = args.toArray();

        Method method = solutionClass.getMethod(methodName, typeArray);
        Object result = method.invoke(solutionClass.getDeclaredConstructor().newInstance(), argArray);
        return result;
    }
}
