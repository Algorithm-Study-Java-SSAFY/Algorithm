package testBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        int[] useMoney;
        int k = 0;

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        useMoney = new int[N];

        for (int i = 0; i < N; i++) {
            useMoney[i] = Integer.parseInt(br.readLine());
        }

        int start = 0;
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
                    if (money < 0) {
                        turn += M;
                    }
                    turn++;
                }
            }
            if (turn > M) {
                start = mid + 1;
            } else {
                end = mid - 1;
                k = mid;
            }

        }

        System.out.println(k);

    }
}