package 스터디.PuyoPuyo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static int N = 12;
	static int M = 6;
	static char[][] board = new char[N][M];
	static char[][] copyBoard = new char[N][M];

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			board[i] = in.readLine().toCharArray();
		}

		solution();
	}

	public static void solution() {
		int answer = 0;

		while (true) {
			int ret = boom();
			// debug("boom");
			if (ret == 0) {
				break;
			}
			answer += ret;
			drop();
			// debug("drop:");
		}
		System.out.println(answer);
	}

	public static void drop() {
		for (int x = 0; x < M; x++) {
			for (int y = N - 1; y >= 0; y--) {
				if (board[y][x] != '.') {
					int idx = indexOf(y, x);
					if (idx == -1) {
						continue;
					}
					board[idx][x] = board[y][x];
					board[y][x] = '.';
				}
			}
		}
	}

	public static int boom() {
		int ret = 0;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] != '.') {
					ret = Math.max(ret, bfs(i, j, board[i][j]));
				}
			}
		}
		return ret;
	}

	private static int bfs(int sy, int sx, char color) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new int[] { sy, sx });
		visited[sy][sx] = true;
		int ret = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < M && !visited[ny][nx] && board[ny][nx] == color) {
					queue.add(new int[] { ny, nx });
					visited[ny][nx] = true;
					ret++;
				}
			}
		}
		if (ret >= 4) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j]) {
						board[i][j] = '.';
					}
				}
			}
		} else {
			return 0;
		}
		return 1;
	}

	public static int indexOf(int y, int x) {
		for (int i = N - 1; i > y; i--) {
			if (board[i][x] == '.') {
				return i;
			}
		}
		return -1;
	}

	public static void debug(String msg) {
		System.out.println(msg);
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println(" ");
	}

}
