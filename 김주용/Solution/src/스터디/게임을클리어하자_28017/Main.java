package 스터디.게임을클리어하자_28017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

    static int N;
    static int M;
    static int[][] times;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        times = new int[N][M];
        dp = new int[N][M];
        for(int i = 0; i < N; i++) {
            int[] cur = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            times[i] = cur;
        }
        solution();
    }

    // dp[i][j]: i 회차에서 j 번째 무기를 골랐을 때 최솟값
    // dp[i][j] = times[i][j] + MIN(dp[i-1][0], dp[i-1][1], ... , dp[i-1[M-1] * 자신은 제외하고
    public static void solution() {
        dp[0] = times[0].clone();
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int minTime = Integer.MAX_VALUE;
                for(int k = 0; k < M; k++) {
                    if(j == k) continue;
                    minTime = Math.min(minTime, dp[i-1][k]);
                }
                dp[i][j] = times[i][j] + minTime;
            }
        }
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < M; i++) {
            answer = Math.min(answer, dp[N-1][i]);
        }
        System.out.println(answer);
    }
}
