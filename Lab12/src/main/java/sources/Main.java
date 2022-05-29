package sources;

import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static java.lang.System.out;

public class Main {
    public static void main(String args[]){
        Class clazz = null;

        URL classUrl = null;
        try {
            classUrl = new URL("file:///U:/courses/PA/lab/PA_2022_2B4_VIVDICI_INA/Lab12/target/classes/sources/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        URL[] classUrls = {classUrl};
        URLClassLoader urlLoader = new URLClassLoader(classUrls);

        try {
            clazz = urlLoader.loadClass("sources.Testing");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(clazz == null){
            out.println("Class not found!");
            System.exit(0);
        }

        out.println("Package: " + getPackageName(clazz) + "\n");

        printMethods(clazz);

        invokeStaticMethods(clazz);

    }

    public static String getPackageName(Class c){
        Package p = c.getPackage();
        if(p != null)
            return p.getName();
        return "";
    }

    public static void printMethods(Class c){
        Method[] methods = c.getMethods();

        for (Method method : methods) {
            out.print(method.getReturnType() + " " + method.getName() + ": ");
            for (var it : method.getParameters()) {
                out.print(it.getType() + " " + it.getName() + " ");
            }
            out.println();
        }
        out.println();
    }

    public static void invokeStaticMethods(Class c){
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class) && method.getParameterCount() <= 0) {
                try {
                    method.invoke(null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
