package 백준.최단거리.SmallWorldNework_18243;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N;
	static int K;
	static boolean[][] graph;

	static Queue<Integer> queue;
	static int[] visited;

	String answer;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);

		graph = new boolean[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			line = in.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			graph[a][b] = true;
			graph[b][a] = true;
		}
		System.out.println(solution());
	}

	public static String solution() {
		visited = new int[N + 1];
		queue = new LinkedList<>();
		for (int start = 1; start <= N; start++) {
			init();
			bfs(start);
			if (!isSmallWorld()) {
				return "Big World!";
			}
		}
		return "Small World!";
	}

	public static void bfs(int start) {
		queue.add(start);
		visited[start] = 0;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int next = 1; next <= N; next++) {
				if (visited[next] == Integer.MAX_VALUE && graph[cur][next]) {
					queue.add(next);
					visited[next] = Math.min(visited[next], visited[cur] + 1);
				}
			}
		}
	}

	public static void init() {
		queue.clear();
		for (int i = 1; i <= N; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
	}

	public static boolean isSmallWorld() {
		for (int i = 1; i <= N; i++) {
			if (visited[i] > 6) {
				return false;
			}
		}
		return true;
	}
}
