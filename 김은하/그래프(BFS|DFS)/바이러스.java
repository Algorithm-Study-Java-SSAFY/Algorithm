import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int E;
	static int[][] graph;
	static boolean[] visited;
	static int virus;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		graph = new int[N + 1][N + 1];
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());

			graph[idx1][idx2] = 1;
			graph[idx2][idx1] = 1;
		}
		visited = new boolean[N + 1];

		bfs(1);

		System.out.println(virus - 1);

		br.close();
	}

	public static void bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(i);
		visited[i] = true;
		while (!q.isEmpty()) {
			int temp = q.poll();
			virus += 1;
			for (int j = 1; j < N + 1; j++) {
				if (graph[temp][j] == 1 && visited[j] == false) {
					q.offer(j);
					visited[j] = true;
				}
			}
		}
	}
}
