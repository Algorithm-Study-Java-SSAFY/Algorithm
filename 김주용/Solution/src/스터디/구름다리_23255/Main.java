package 스터디.구름다리_23255;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N;
    static int M;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int[] colors;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        adj = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);

            adj[from].add(to);
            adj[to].add(from);
        }
        solution();
    }

    // 인접한 노드 색 중 가장 작은 값.
    public static void solution() {
        int[] colors = new int[N+1];
        for(int i = 1; i <= N; i++) {
            HashSet<Integer> cur = new HashSet<>();
            for(int next : adj[i]) {
                if(colors[next] != 0) {
                    cur.add(colors[next]);
                }
            }
            ArrayList<Integer> adjColors = new ArrayList<>(cur);
            Collections.sort(adjColors);

            colors[i] = 1;
            for (int j : adjColors) {
                if (j != colors[i])
                    break;
                colors[i]++;
            }
            System.out.print(colors[i] + " ");
        }
    }

}
