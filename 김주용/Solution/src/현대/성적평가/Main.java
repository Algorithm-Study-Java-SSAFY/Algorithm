package 현대.성적평가;

import java.util.*;
import java.io.*;
import java.util.stream.Stream;

class Score implements Comparable<Score> {
    int idx;
    int score;
    int rank;

    public Score(int idx, int score) {
        this.idx = idx;
        this.score = score;
        this.rank = 0;
    }

    @Override
    public int compareTo(Score o) {
        return o.score - this.score;
    }
}

public class Main {

    static int N;
    static StringBuilder answer;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        answer = new StringBuilder();
        int[] total = new int[N];
        for(int i = 0; i < 3; i++) {
            int[] cur = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Score[] scores = new Score[N];
            for(int j = 0; j < N; j++) {
                scores[j] = new Score(j, cur[j]);
                total[j] += cur[j];
            }
            solution(scores);
        }
        Score[] scores = new Score[N];
        for(int i = 0; i < N; i++) {
            scores[i] = new Score(i, total[i]);
        }
        solution(scores);
        System.out.print(answer);
    }

    public static void solution(Score[] scores) {
        Arrays.sort(scores);
        scores[0].rank = 1;
        for(int i = 1; i < N; i++) {
            if(scores[i-1].score == scores[i].score) {
                scores[i].rank = scores[i-1].rank;
            } else {
                scores[i].rank = i + 1;
            }
        }
        Arrays.sort(scores, Comparator.comparingInt(o -> o.idx));
        for(Score cur : scores) {
            answer.append(cur.rank).append(" ");
        }
        answer.append("\n");
    }
}
