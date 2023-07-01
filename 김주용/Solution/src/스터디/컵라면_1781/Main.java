package 스터디.컵라면_1781;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Problem implements Comparable<Problem> {
    int num;
    int deadline;
    int cup;

    public Problem(int num, int deadline, int cup) {
        this.num = num;
        this.deadline = deadline;
        this.cup = cup;
    }

    @Override
    public int compareTo(Problem o) {
        if(this.deadline == o.deadline) {
            return o.cup - this.cup;
        }
        return this.deadline - o.deadline;
    }
}

public class Main {

    static int N;
    static Problem[] problems;
    static int maxDay;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        problems = new Problem[N];
        for(int i = 0; i < N; i++) {
            String[] line = in.readLine().split(" ");
            int deadline = Integer.parseInt(line[0]);
            int cup = Integer.parseInt(line[1]);

            problems[i] = new Problem(i + 1, deadline, cup);
            maxDay = Math.max(maxDay, deadline);
        }
    }

    public static void solution() {
        int idx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= maxDay; i++) {
            while (idx < N && problems[idx].deadline <= i) {
                pq.add(problems[idx].cup);
                idx++;
            }

        }
    }
}
