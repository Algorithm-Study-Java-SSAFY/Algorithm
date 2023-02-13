import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        int[] useMoney;
        int k = 0;
        int max_use = 0;

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        useMoney = new int[N];

        for (int i = 0; i < N; i++) {
            useMoney[i] = Integer.parseInt(br.readLine());
            max_use = Math.max(max_use, useMoney[i]);
        }

        int start = max_use;
        int end = (int) 1e9;

        while (start <= end) {
            int mid = (start + end) / 2;
            int money = mid;
            int turn = 1;
            for (int i = 0; i < N; i++) {
                if (money >= useMoney[i]) {
                    money -= useMoney[i];
                } else {
                    money = mid - useMoney[i];
                    turn++;
                }
            }
            if (turn < M) {
                end = mid - 1;
            } else {
                k = mid;
                start = mid + 1;
            }

        }

        System.out.println(k);

    }
}