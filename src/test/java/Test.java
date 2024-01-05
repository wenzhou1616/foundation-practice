import java.io.IOException;
import java.util.Arrays;

/**
 * @author herry
 */
public class Test {
    @org.junit.Test
    public void test01() throws IOException {
        int[] a = {1,2,3,4};
        int[] b = Arrays.copyOf(a, a.length);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        b[0] = 123;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

    }
}
