package sources;

import org.testng.annotations.Test;

import static java.lang.System.out;

public class Testing {
    @Test
    public void method1() {
        out.println("Method 1...");
    }
    @Test
    public static void method2() {
        out.println("Method 2...");
    }

    public static void method3(int tralaa) {
        out.println("Method 3...");
    }
}
