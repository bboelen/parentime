import java.util.Arrays;
import java.util.function.Consumer;

public class TestFormula {


    private static int D = 2;
    private static int T = 2;
    private static int C = 2;

    private static String[] sequence = new String[D * T * C];

    public static void main(String... args) {

        set(0, 0, 0, "0");
        set(0, 0, 1, "1");
        set(0, 1, 0, "2");
        set(0, 1, 1, "3");
        set(1, 0, 0, "4");
        set(1, 0, 1, "5");
        set(1, 1, 0, "6");
        set(1, 1, 1, "7");


        for(int i = 0; i < sequence.length; i++) {
            System.out.println(sequence[i]);
        }
    }

    private static String get(int x, int y, int z) {
        return sequence[z * D * C + y * D + x];
    }

    private static void set(int x, int y, int z, String value) {
        sequence[z * D * C + y * D + x] = value;
    }
}
