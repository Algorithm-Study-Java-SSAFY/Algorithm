package 현대.출퇴근길;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class Main {

    static int N;
    static int M;
    static int S, T;

    static ArrayList<Integer>[] graph;
    static boolean[] toS;
    static boolean[] toT;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        graph = new ArrayList[N+1];
        toS = new boolean[N+1];
        toT = new boolean[N+1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++) {
            line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            graph[from].add(to);
        }

        line = in.readLine().split(" ");
        S = Integer.parseInt(line[0]);
        T = Integer.parseInt(line[1]);

        solution();
    }

    public static void solution() {
        boolean[] visitedToT = new boolean[N+1];
        visitedToT[S] = true;
        boolean[] visitedToS = new boolean[N+1];
        visitedToS[T] = true;

        dfs(S, T, S, visitedToT, toT);
        dfs(T, S, T, visitedToS, toS);

        int answer = 0;
        for(int i = 1; i <= N; i++) {
            if(toT[i] && toS[i] && i != S && i != T) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void dfs(int start, int dest, int cur, boolean[] visited, boolean[] path) {
        if(cur == dest || path[cur]) {
            for(int i = 1; i <= N; i++) {
                if(visited[i]) {
                    path[i] = true;
                }
            }
            return;
        }


        for(int next : graph[cur]) {
            if(!visited[next] || path[next]) {
                visited[next] = true;
                dfs(start, dest, next, visited, path);
                visited[next] = false;
            }
        }
    }

}