package 스터디.ABCDE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        graph = new ArrayList[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            graph[from].add(to);
            graph[to].add(from);
        }
        solution();
    }

    public static void solution() {
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, 1);
            init();
        }
        System.out.println(0);
    }

    public static void dfs(int cur, int depth) {
        if(depth >= 5) {
            System.out.println(1);
            System.exit(0);
        }


        for(int next : graph[cur]) {
            if(!visited[next]) {
                visited[next] = true;
                dfs(next, depth + 1);
                visited[next] = false;
            }
        }
    }

    public static void init() {
        for (int i = 0; i < N; i++) {
            visited[i] = false;
        }
    }
}