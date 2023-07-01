package 스터디.주사위쌓기_2116;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;


public class Main {

    static int N;
    static int[][] dices;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        dices = new int[N][6];
        for (int i = 0; i < N; i++) {
            int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dices[i] = line;
        }
        solution();
    }

    public static void solution() {
        int answer = 0;
        for (int i = 0; i < 6; i++) {
            int top = dices[0][i];
            int botIdx = getOpposite(i);
            int ret = getMaxSide(0, i, botIdx);
            // 주사위 쌓기
            for (int j = 1; j < N; j++) {
                botIdx = find(j, top);
                int topIdx = getOpposite(botIdx);
                top = dices[j][topIdx];
                ret += getMaxSide(j, topIdx, botIdx);
            }


            answer = Math.max(answer, ret);
        }
        System.out.println(answer);
    }

    public static int find(int diceNum, int top) {
        for (int i = 0; i < 6; i++) {
            if (top == dices[diceNum][i]) {
                return i;
            }
        }
        return -1;
    }

    public static int getOpposite(int cur) {
        if (cur == 0) return 5;
        if (cur == 1) return 3;
        if (cur == 2) return 4;
        if (cur == 3) return 1;
        if (cur == 4) return 2;
        return 0;
    }

    public static int getMaxSide(int cur, int topIdx, int botIdx) {
        int ret = 0;
        for (int i = 0; i < 6; i++) {
            if (i == topIdx || i == botIdx) {
                continue;
            }
            ret = Math.max(ret, dices[cur][i]);
        }
        return ret;
    }
}
