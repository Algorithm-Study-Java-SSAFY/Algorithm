package 현대.지우는소수를좋아해;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class Edge {
    int num;
    long level;

    public Edge(int num, long level) {
        this.num = num;
        this.level = level;
    }
}

public class Main {

    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static long maxLevel = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        long left = Long.MAX_VALUE;
        long right = 0;
        for(int i = 0; i < M; i++) {
            String[] cur = in.readLine().split(" ");
            int from = Integer.parseInt(cur[0]);
            int to = Integer.parseInt(cur[1]);
            long level = Long.parseLong(cur[2]);
            graph[from].add(new Edge(to, level));
            graph[to].add(new Edge(from, level));

            left = Math.min(left, level);
            right = Math.max(right, level);
        }

        solution(left, right);
    }

    public static void solution(long left, long right) {
        right = getPrime(right);
        maxLevel = right;
        long minLevel = right;
        while (left <= right) {
            long mid = (left + right) / 2;
            if(!bfs(mid)) { // 현재 레벨로 목적지까지 갈 수 없다면
                left = mid + 1; // 레벨 업
            } else {
                right = mid - 1; // 있다면 레벨 다운
                minLevel = Math.min(minLevel, mid);
            }
        }
        //System.out.println(minLevel);
        System.out.println(getPrime(minLevel));
    }

    // 현재 레벨로 목적지 까지 갈 수 있는가
    public static boolean bfs(long level) {
        for(int i = 1; i <= N; i++) {
            visited[i] = false;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for(Edge next : graph[cur]) {
                if(!visited[next.num] && next.level < level) {
                    queue.add(next.num);
                    visited[next.num] = true;
                    if(next.num == N) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static long getPrime(long level) {
        if(level <= 2) {
            return 2;
        }
        for(long i = level; i <= maxLevel; i++) {
            if(isPrime(i)) {
                return i;
            }
        }
        return maxLevel;
    }

    public static boolean isPrime(long level) {
        for(long i = 2; i <= level / 2; i++) {
            if(level % i == 0) {
                return false;
            }
        }
        return true;
    }
}
