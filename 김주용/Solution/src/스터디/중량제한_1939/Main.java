package 스터디.중량제한_1939;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Edge {
    int to;
    long weight;

    public Edge(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }

}
public class Main {

    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;

    static int start;
    static int dest;

    static long left = Long.MAX_VALUE;
    static long right = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        for(int i = 0; i < M; i++) {
            line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            long weight = Long.parseLong(line[2]);
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
            left = Math.min(left, weight);
            right = Math.max(right,weight);
        }
        line = in.readLine().split(" ");
        start = Integer.parseInt(line[0]);
        dest = Integer.parseInt(line[1]);
        solution();
    }

    // 목적지에서 도착지까지 경로 중 최솟값
    public static void solution() {
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if(bfs(mid)) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
            //System.out.println(left + " " + right);
        }
        System.out.println(answer);
    }

    public static boolean bfs(long weight) {
        for(int i = 1; i <= N; i++) {
            visited[i] = false;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for(Edge next : graph[cur]) {
                if(!visited[next.to] && next.weight >= weight) {
                    queue.add(next.to);
                    visited[next.to] = true;
                    if(next.to == dest) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
