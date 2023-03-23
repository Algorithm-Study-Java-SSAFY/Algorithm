package 수업.그래프.보급로;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Point implements Comparable<Point> {
	int y;
	int x;
	int time;

	public Point(int y, int x, int time) {
		this.y = y;
		this.x = x;
		this.time = time;
	}

	@Override
	public int compareTo(Point o) {
		return this.time - o.time;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + ", time=" + time + "]";
	}

}

public class Solution {

	static int N;
	static int[][] board;
	static int[][] visited;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(in.readLine());
			board = new int[N][N];
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = in.readLine();
				for (int j = 0; j < N; j++) {
					visited[i][j] = -1;
					board[i][j] = line.charAt(j) - '0';
				}
			}
			int answer = solution();
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static int solution() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.add(new Point(0, 0, 0));
		visited[0][0] = 0;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i], nx = cur.x + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N && visited[ny][nx] == -1) {
					int time = board[ny][nx];
					visited[ny][nx] = time + cur.time;
					queue.add(new Point(ny, nx, cur.time + time));
				}
			}
		}

		return visited[N - 1][N - 1];
	}

}
