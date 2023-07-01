package 스터디.최소비용구하기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge> {
    int num;
    int cost;

    String path;

    public Edge(int num, int cost, String  path) {
        this.num = num;
        this.cost = cost;
        this.path = path;
    }


    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {
    static int N;
    static int M;
    static ArrayList<Edge>[] adj;
    static int[] costs;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        adj = new ArrayList[N+1];
        costs = new int[N+1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<Edge>();
            costs[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            String[] line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            int cost = Integer.parseInt(line[2]);

            adj[from].add(new Edge(to, cost, ""));
        }
        String[] line = in.readLine().split(" ");
        int start = Integer.parseInt(line[0]);
        int dest = Integer.parseInt(line[1]);

        solution(start, dest);
    }

    public static void solution(int start, int dest) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0, start + " "));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(cur.num == dest) {
                System.out.println(cur.cost);
                System.out.println(cur.path.split(" ").length);
                System.out.println(cur.path);
                break;
            }

            for(Edge next : adj[cur.num]) {
                int curCost = costs[cur.num] + next.cost;
                if (curCost < costs[next.num]) {
                    costs[next.num] = curCost;
                    pq.add(new Edge(next.num, curCost, cur.path + next.num + " "));
                }
            }
        }
    }
}
