import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {

	int x;
	int y;
	int loopy;

	public Node(int x, int y, int loopy) {
		this.x = x;
		this.y = y;
		this.loopy = loopy;
	}

	@Override
	public int compareTo(Node o) {
		return loopy - o.loopy;
	}

}

public class Main {
	static final int[] DX = { 0, 1, 0, -1 };
	static final int[] DY = { 1, 0, -1, 0 };
	static final int INF = 14000;
	static int N, cnt = 1;
	static int[][] cave;
	static int[][] cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			cave = new int[N][N];
			cost = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
					cost[i][j] = INF;
				}
			}

			sb.append("Problem ").append(cnt).append(": ").append(calLoopy()).append("\n");
			cnt++;
		}

		System.out.println(sb);
		br.close();
	}

	public static int calLoopy() {
		boolean[][] visited = new boolean[N][N];
		int x = 0, y = 0, dx = 0, dy = 0, value = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, cave[0][0]));
		visited[x][y] = true;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			x = cur.x;
			y = cur.y;
			value = cur.loopy;

			for (int i = 0; i < 4; i++) {
				dx = x + DX[i];
				dy = y + DY[i];

				if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy] && cost[dx][dy] > cave[dx][dy] + value) {
					cost[dx][dy] = cave[dx][dy] + value;
					visited[dx][dy] = true;
					pq.add(new Node(dx, dy, cost[dx][dy]));
				}
			}
		}

		return cost[N - 1][N - 1];
	}
}
