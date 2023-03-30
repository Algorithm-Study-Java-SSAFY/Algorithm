package 스터디.녹색옷입은애가젤다지_4485;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Point implements Comparable<Point> {
	int y;
	int x;
	int k;

	public Point(int y, int x, int k) {
		this.y = y;
		this.x = x;
		this.k = k;
	}

	@Override
	public int compareTo(Point o) {
		return this.k - o.k;
	}
}

public class Main {

	static int N;
	static int[][] board;
	static int[][] visited;
	static PriorityQueue<Point> queue = new PriorityQueue<>();

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int t = 1;
		while (true) {
			N = Integer.parseInt(in.readLine());
			if (N == 0) {
				break;
			}
			board = new int[N][N];
			visited = new int[N][N];
			queue.clear();
			for (int i = 0; i < N; i++) {
				board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for(int j = 0; j < N; j++) {
					visited[i][j] = 1000000;
				}
			}
			solution();
			System.out.printf("Problem %d: %d\n", t, visited[N - 1][N - 1]);
			t++;
		}
	}

	public static void solution() {
		queue.add(new Point(0, 0, board[0][0]));
		visited[0][0] = board[0][0];

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i], nx = cur.x + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N && visited[ny][nx] >  visited[cur.y][cur.x] + board[ny][nx]){
					visited[ny][nx] = visited[cur.y][cur.x] + board[ny][nx];
					queue.add(new Point(ny, nx, visited[ny][nx]));
				}
			}
		}
	}

}
