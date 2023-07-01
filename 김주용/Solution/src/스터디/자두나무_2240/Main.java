package 스터디.자두나무_2240;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int T;
    static int W;
    static int[] inform;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        T = Integer.parseInt(line[0]);
        W = Integer.parseInt(line[1]);

        inform = new int[T+1];
        dp = new int[T + 1][W + 1];
        for(int i = 1; i <= T; i++) {
            inform[i] = Integer.parseInt(in.readLine());
        }

    }

    public static void solution() {
        for(int i = 1; i <= T; i++) {
            for(int j = 1; j <= W; j++) {


            }
        }
    }
}
