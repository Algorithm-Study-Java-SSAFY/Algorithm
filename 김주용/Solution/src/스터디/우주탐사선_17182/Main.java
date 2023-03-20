package 스터디.우주탐사선_17182;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int K;
	static int[][] graph;
	static boolean[] visited;

	static int[][] timeGraph;

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = line[0];
		K = line[1];

		graph = new int[N][N];
		visited = new boolean[N];
		for (int cur = 0; cur < N; cur++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int next = 0; next < N; next++) {
				graph[cur][next] = line[next];
			}
		}
		solution();
		System.out.println(answer);
	}

	public static void solution() {
		// 각 노드마다 다른 노드 까지 최단 거리 구하기
		timeGraph = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				timeGraph[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int cur = 0; cur < N; cur++) {
			for (int dest = 0; dest < N; dest++) {
				if (cur == dest)
					continue;
				visited[cur] = true;
				dfs(cur, dest, cur, 0);
				init();
			}
		}
		// 출발
		visited[K] = true;
		goDfs(K, 0, 1);

	}

	public static void dfs(int start, int dest, int cur, int time) {
		if (cur == dest) {
			timeGraph[start][dest] = Math.min(timeGraph[start][dest], time);
			return;
		}

		for (int next = 0; next < N; next++) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(start, dest, next, time + graph[cur][next]);
				visited[next] = false;
			}
		}
	}

	public static void goDfs(int cur, int time, int depth) {
		if (depth == N) {
			answer = Math.min(answer, time);
			return;
		}

		for (int next = 0; next < N; next++) {
			if (!visited[next]) {
				visited[next] = true;
				goDfs(next, time + timeGraph[cur][next], depth + 1);
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
