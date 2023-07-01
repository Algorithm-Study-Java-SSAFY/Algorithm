package 스터디.시간이겹칠까_28018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

    static int MAX = 1000002;
    static int N;
    static int[] times = new int[MAX];
    static int Q;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        for(int i = 0; i < N; i++) {
            String[] line = in .readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);

            // 들어올 땐 + 1 나갈 땐 -1
            times[start]++;
            times[end + 1]--; // 종료된 직후는 불가
        }

        // 누적합 sum[i] = i 시간에 몇 명이 있는지
        int[] sum = new int[MAX];
        for(int i = 1; i < MAX; i++) {
            sum[i] = sum[i-1] + times[i];
        }

        StringBuilder answer = new StringBuilder();

        Q = Integer.parseInt(in.readLine());
        int[] findTimes = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int find : findTimes) {
            answer.append(sum[find]).append('\n');
        }

        System.out.print(answer);
    }
}
