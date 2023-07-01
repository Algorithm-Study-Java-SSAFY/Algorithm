package 백준.최단거리.주난의난_14497;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Point implements Comparable<Point> {
	int x;
	int y;
	int jump;
	public Point(int x, int y, int jump) {
		super();
		this.x = x;
		this.y = y;
		this.jump = jump;
	}
	@Override
	public int compareTo(Point o) {
		return this.jump - o.jump;
	}
	
	
}

public class Main {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static int N;
	static int M;

	static char[][] board;
	static int[][] visited;
	static int startX;
	static int startY;
	static int destX;
	static int destY;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] line = in.readLine().split(" ");

		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		line = in.readLine().split(" ");
		startX = Integer.parseInt(line[0]) - 1;
		startY = Integer.parseInt(line[1]) - 1;
		destX = Integer.parseInt(line[2]) - 1;
		destY = Integer.parseInt(line[3]) - 1;

		board = new char[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = in.readLine().toCharArray();
		}
		solution();
	}

	public static void solution() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = -1;
			}
		}
		PriorityQueue<Point> queue = new PriorityQueue<>(); // 벽 개수가 적은거 먼저 
		queue.add(new Point(startX, startY, 0));
		visited[startX][startY] = 0;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i], ny = cur.y + dy[i];
				if (-1 < nx && nx < N && -1 < ny && ny < M) {
					if (visited[nx][ny] == -1) {
						int jumps = 0;
						if (board[nx][ny] == '0') {
							jumps = cur.jump;
						} else { // 점프
							jumps = cur.jump + 1;
						}
						queue.add(new Point(nx, ny, jumps));
						visited[nx][ny] = jumps;
						
					}
				}
			}
		}

		System.out.println(visited[destX][destY]);
	}

}
