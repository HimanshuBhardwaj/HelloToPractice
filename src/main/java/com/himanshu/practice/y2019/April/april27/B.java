import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 26/04/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char ch[] = br.readLine().toCharArray();
        int number[] = new int[ch.length];

        for (int i = 0; i < ch.length; i++) {
            number[i] = ch[i] - '0';
        }

        HashMap<Integer, Integer> mapping = new HashMap<>();
        String str[] = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            mapping.put(i + 1, Integer.parseInt(str[i]));
        }

        boolean hasStarted = false;
        for (int i = 0; i < n; i++) {
            if (hasStarted == false) {
                if (mapping.get(number[i]) > number[i]) {
                    hasStarted = true;
                    number[i] = mapping.get(number[i]);
                }
            } else {
                if (mapping.get(number[i]) >= number[i]) {
                    number[i] = mapping.get(number[i]);
                } else {
                    break;
                }
            }
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < n; i++) {
            sb.append(number[i]);
        }

        System.out.print(sb.toString());

    }
}