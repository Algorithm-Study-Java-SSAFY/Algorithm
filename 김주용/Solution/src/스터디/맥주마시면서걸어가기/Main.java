package 스터디.맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int num;
    int y;
    int x;
    int bottle;
    public Pair(int num, String[] line) {
        this.num = num;
        this.x = Integer.parseInt(line[0]);
        this.y = Integer.parseInt(line[1]);
    }
}

public class Main {

    static int N;
    static Pair src;
    static Pair[] stores;
    static Pair dest;

    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(in.readLine());
            src = new Pair(0, in.readLine().split(" "));
            stores = new Pair[N + 2];
            visited = new boolean[N + 2];
            stores[0] = src;
            for(int i = 1; i <= N; i++) {
                stores[i] = new Pair(i, in.readLine().split(" "));
            }
            dest = new Pair(N + 1, in.readLine().split(" "));
            stores[N+1] = dest;

            System.out.println(solution());
        }
    }

    public static String solution() {
        src.bottle = 20;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(src);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for(int i = 1; i < N + 2; i++) {
                Pair next = stores[i];
                int dist = Math.abs(cur.y - next.y) + Math.abs(cur.x - next.x);
                if(!visited[next.num] && dist <= 1000) {
                    visited[next.num] = true;
                    queue.add(next);
                    if(i == N + 1) {
                        return "happy";
                    }
                }
            }
        }
        return "sad";
    }
}
