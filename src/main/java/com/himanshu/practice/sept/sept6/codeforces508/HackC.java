import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 06/09/18.
 */
public class HackC {
    public static void main(String[] args) throws IOException {

        System.out.println(100000);
        for (int i = 0; i < 100000; i++) {
            System.out.print(1000000-1);
            if (i == (100000 - 1)) {

            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int i = 0; i < 100000; i++) {
            System.out.print(1);
            if (i == (100000 - 1)) {

            } else {
                System.out.print(" ");
            }
        }
    }
}
