package 스터디.인터넷설치_1800;


import java.io.*;
import java.util.*;

class Edge {
    int num, cost, free;

    public Edge(int num, int cost, int free) {
        this.num = num;
        this.cost = cost;
        this.free = free;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "num=" + num +
                ", cost=" + cost +
                ", free=" + free +
                '}';
    }
}

public class Main {

    static int N, P, K;
    static ArrayList<Edge>[] graph;
    static int[] costs;
    static int[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        P = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);

        graph = new ArrayList[N+1];
        visited = new int[N+1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        costs = new int[P];
        for(int i = 0; i < P; i++) {
            line = in.readLine().split(" ");
            int to = Integer.parseInt(line[0]);
            int from = Integer.parseInt(line[1]);
            int cost = Integer.parseInt(line[2]);

            graph[from].add(new Edge(to, cost, 0));
            graph[to].add(new Edge(from, cost, 0));
            costs[i] = cost;
        }

        solution();
    }

    // 중량제한 문제와 동일
    // 이분 탐색 으로 최소 금액 정하고 BFS
    public static void solution() {
        int answer = Integer.MAX_VALUE;
        int left = 0, right = 1000000;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(bfs(mid)) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }
        if(answer == Integer.MAX_VALUE) { // N까지 도달 불가
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    // 무료 케이이블을 가장 적게 사용하도록 BFS
    public static boolean bfs(int cost) {
        for(int i = 1; i <= N; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if(cur == N && visited[N] <= K) { // 탈출 조건: N에 도달 && 무료 케이블 K개 이하
                return true;
            }

            for(Edge next : graph[cur]) {
                if(next.cost <= cost && visited[next.num] > visited[cur]) { // 진행 조건: 최대 비용 보다 작을땐 && 현재 사용한 무로 케이블 수가 적으면
                    visited[next.num] = visited[cur];
                    queue.add(next.num);
                }
                if(next.cost > cost && visited[next.num] > visited[cur] + 1) { // 진행 조건: 최댓비용 보다 클 땐 && 무료 케이블 + 1 수 보다 적으면
                    visited[next.num] = visited[cur] + 1;
                    queue.add(next.num);
                }
            }
        }
        return false;
    }
}
