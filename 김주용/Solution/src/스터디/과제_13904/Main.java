package 스터디.과제_13904;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Homework {
    int idx;
    int d;
    int score;

    public Homework(int idx, int d, int score) {
        this.idx = idx;
        this.d = d;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "idx=" + idx +
                ", d=" + d +
                ", score=" + score +
                '}';
    }
}

public class Main {

    static int N;
    static Homework[] homeworks;
    static int day;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        homeworks = new Homework[N];
        for (int i = 0; i < N; i++) {
            String[] line = in.readLine().split(" ");
            int d = Integer.parseInt(line[0]);
            int w = Integer.parseInt(line[1]);
            homeworks[i] = new Homework(i, d, w);
            day = Math.max(day, d);
        }
        solution();
    }

    public static void solution() {
        int answer = 0;
        for (int i = day; i > 0; i--) {
            Homework cur = find(i);
            if (cur.idx == -1) {
                continue;
            }
            answer += cur.score;
            homeworks[cur.idx] = null;
        }
        System.out.println(answer);
    }

    public static Homework find(int d) {
        Homework ret = new Homework(-1, 0, 0);
        for (int i = 0; i < N; i++) {
            if (homeworks[i] != null && homeworks[i].d >= d) {
                if (ret.score <= homeworks[i].score) {
                    ret = homeworks[i];
                }
            }
        }
        return ret;
    }
}
