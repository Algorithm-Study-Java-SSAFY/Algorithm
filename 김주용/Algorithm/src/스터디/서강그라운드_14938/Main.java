package 스터디.서강그라운드_14938;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

class Node implements Comparable<Node> {
	int num;
	int dist;

	public Node(int num, int dist) {
		this.num = num;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		return this.dist - o.dist;
	}
}

public class Main {

	static int N;
	static int M;
	static int R;

	static int[] items;
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		R = line[2];

		items = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		dist = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				dist[i][j] = 16;
			}
		}

		for (int i = 0; i < R; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			dist[line[0]][line[1]] = line[2];
			dist[line[1]][line[0]] = line[2];
		}
		solution();
	}

	public static void solution() {
		// 플루이드 - 와샬
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			int cur = items[i - 1];
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					continue;
				}
				if (dist[i][j] <= M) {
					cur += items[j - 1];
				}
			}
			answer = Math.max(answer, cur);
		}
		System.out.println(answer);
	}

}
