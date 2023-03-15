package 스터디.여행가자_1976;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int M;
	static boolean[][] graph;
	static boolean[] visited;

	static int[] path;

	static String answer = "NO";

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		graph = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			String[] line = in.readLine().split(" ");
			graph[i][i] = true;
			for (int j = 0; j < N; j++) {
				if (line[j].equals("1")) {
					graph[i][j + 1] = true;
					graph[j + 1][i] = true;
				}
			}
		}

		path = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for(int i = 0; i < path.length; i++) {
			visited = new boolean[N + 1];
			dfs(path[i], 1);	
		}

		System.out.println(answer);
	}

	
	public static void dfs(int cur, int depth) {
		if (canGo()) {
			answer = "YES";
		}

		for (int next = 1; next <= N; next++) {
			if (graph[cur][next] && !visited[next]) {
				visited[next] = true;
				dfs(next, depth + 1);
				visited[next] = true;
			}
		}
	}

	public static boolean canGo() {
		for (int i = 0; i < path.length; i++) {
			if (!visited[path[i]]) {
				return false;
			}
		}
		return true;
	}

}
