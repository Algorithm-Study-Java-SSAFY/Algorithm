package 스터디.고층건물_1027;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    static int N;
    static long[] heights;
    static boolean[][] isSee;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        heights = Stream.of(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        solution();
    }

    public static void solution() {
        answer = new int[N];
        isSee = new boolean[N][N];
        findRight(0);
        findLeft(N-1);
        for(int i = 1; i < N-1; i++) {
            findLeft(i);
            findRight(i);
        }
        int ret = 0;
        for(int i = 0; i < N; i++) {
            int sum = 0;
            for(int j = 0; j < N; j++) {
                if(isSee[i][j]) {
                    sum++;
                }
            }
            ret = Math.max(ret, sum);
        }
        System.out.println(ret);
    }

    public static void findLeft(int cur) {
        int ret = 0;
        double maxA = -1;
        for(int i = cur - 1; i >= 0; i--) {
            if(heights[i] < heights[cur]) continue;
            int width = Math.abs(i - cur);
            long height = Math.abs(heights[i] - heights[cur]);
            double a = (double) height / width;
            if(maxA < a) {
                maxA = a;
                isSee[i][cur] = true;
                isSee[cur][i] = true;
            }
        }
    }

    public static void findRight(int cur) {
        int ret = 0;
        double maxA = -1;
        for(int i = cur + 1; i < N; i++) {
            if(heights[i] < heights[cur]) continue;
            int width = Math.abs(i - cur);
            long height = Math.abs(heights[i] - heights[cur]);
            double a = (double) height / width;
            if(maxA < a) {
                maxA = a;
                isSee[i][cur] = true;
                isSee[cur][i] = true;
            }
        }
    }
}
