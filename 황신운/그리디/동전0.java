import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int cost = Integer.parseInt(tmp[1]);

        int[] moneyType = new int[n];
        for (int i = 0; i < n; i++) {
            moneyType[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;

        for (int i = n - 1; i > -1; i--) {
            if (cost >= moneyType[i]) {
                answer += cost / moneyType[i];
                cost %= moneyType[i];
            }
        }

        System.out.println(answer);
    }

}