package 스터디.컬러볼_10800;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

class Ball {
    int idx;
    int color;
    int size;
    public Ball(int idx, int color, int size) {
        this.idx = idx;
        this.color = color;
        this.size = size;
    }
}

public class Main {

    static int N;
    static Ball[] balls;
    static int maxColor;
    static int maxSize;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Ball cur = new Ball(i, line[0], line[1]);
            balls[i] = cur;
            maxColor = Math.max(maxColor, line[0]);
            maxSize = Math.max(maxSize, line[1]);
        }
        solution();
    }

    public static void solution() {
        Arrays.sort(balls, (o1, o2) -> {
            if(o1.size == o2.size) {
                return o1.color - o2.color;
            }
            return o1.size - o2.size;
        });

        int[] answer = new int[N];

        int[] sum = new int[N];
        int[] sameSize = new int[maxSize + 1];
        int[] sameColor = new int[maxColor + 1];
        for(int i = 1; i < N; i++) {
            sum[i] = sum[i-1] + balls[i-1].size;
            sameColor[balls[i-1].color] += balls[i-1].size;
            sameSize[balls[i-1].size] += balls[i-1].size;

            if(balls[i-1].color == balls[i].color && balls[i-1].size == balls[i].size) {
                answer[balls[i].idx] = answer[balls[i-1].idx];
                continue;
            }
            answer[balls[i].idx] = sum[i] - sameColor[balls[i].color] - sameSize[balls[i].size];
        }

        StringBuilder out = new StringBuilder();
        for(int cur : answer) {
            out.append(cur).append("\n");
        }
        System.out.print(out);
    }
}
