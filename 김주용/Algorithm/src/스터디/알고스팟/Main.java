package 스터디.알고스팟;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Point implements Comparable<Point> {
	int y;
	int x;
	int wall;

	public Point(int y, int x, int wall) {
		this.y = y;
		this.x = x;
		this.wall = wall;
	}

	@Override
	public int compareTo(Point o) {
		return this.wall - o.wall;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + ", wall=" + wall + "]";
	}

}

public class Main {

	static int N;
	static int M;
	static char[][] board;
	static int[][] wallInfo;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[1]);
		M = Integer.parseInt(line[0]);

		board = new char[N][M];
		wallInfo = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				wallInfo[i][j] = Integer.MAX_VALUE;
			}
		}

		solution();
		// System.out.println(answer);
	}

	public static void solution() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0));
		wallInfo[0][0] = 0;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.y == N - 1 && cur.x == M - 1) {
				break;
			}

			// System.out.println(cur);
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i], nx = cur.x + dx[i];
				if (!inRange(ny, nx) || wallInfo[ny][nx] != Integer.MAX_VALUE) {
					continue;
				}
				if (board[ny][nx] == '0') {
					pq.add(new Point(ny, nx, cur.wall));
					wallInfo[ny][nx] = wallInfo[cur.y][cur.x];
				} else {
					pq.add(new Point(ny, nx, cur.wall + 1));
					wallInfo[ny][nx] = wallInfo[cur.y][cur.x] + 1;
				}

			}
		}
		System.out.println(wallInfo[N - 1][M - 1]);
	}

	public static boolean inRange(int y, int x) {
		return -1 < y && y < N && -1 < x && x < M;
	}
}