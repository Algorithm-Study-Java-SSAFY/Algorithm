import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String tmp = br.readLine();
        StringTokenizer st = new StringTokenizer(tmp, " ");

        int[] num = new int[n];
        int idx = 0;
        while (st.hasMoreTokens()) {
            num[idx++] = Integer.parseInt(st.nextToken());
        }

        int[] inc = new int[n];
        int[] dec = new int[n];
        inc[0] = 1;
        dec[0] = 1;
        for (int i = 1; i < n; i++) {
            if (num[i] >= num[i - 1]) {
                inc[i] = inc[i - 1] + 1;
            } else {
                inc[i] = 1;
            }
            if (num[i] <= num[i - 1]) {
                dec[i] = dec[i - 1] + 1;
            } else {
                dec[i] = 1;
            }
        }

        int max_inc = 0;
        int max_dec = 0;

        for (int i = 0; i < n; i++) {
            max_inc = Math.max(max_inc, inc[i]);
            max_dec = Math.max(max_dec, dec[i]);
        }
        System.out.println(Math.max(max_inc, max_dec));
    }
}