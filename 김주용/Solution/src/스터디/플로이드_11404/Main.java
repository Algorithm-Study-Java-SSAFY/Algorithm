package 스터디.플로이드_11404;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int M;

	static long[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		graph = new long[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j) {
					graph[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			String[] line = in.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int dest = Integer.parseInt(line[1]);
			int cost = Integer.parseInt(line[2]);
			graph[start][dest] = Math.min(graph[start][dest], cost);
		}
		solution();

	}

	public static void solution() {
		setCostGraph();
		StringBuffer out = new StringBuffer();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(graph[i][j] == Integer.MAX_VALUE) {
					out.append(0 + " ");
					continue;
				} 
				out.append(graph[i][j] + " ");
			}
			out.append("\n");
		}
		System.out.println(out);
	}

	// 플로이드 - 와샬
	public static void setCostGraph() {
		// k를 중간 노드로 삼을 때와 아닐 때 비교
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}

	}

}
