package 수업.DFS_BFS_응용.빙산_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");

		int N = Integer.parseInt(line[0]), M = Integer.parseInt(line[1]);
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			line = in.readLine().split(" ");
			board[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
		}

		solution(board, N, M);
	}

	public static int solution(int[][] board, int N, int M) {
		int ret = 0;
		int time = 0;
		while (true) {
			time++;
			int[][] newBoard = clone(board, N, M);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] > 0) {
						melting(board, newBoard, N, M, i, j);
					}
				}
			}
			board = newBoard;
			int group = 0;
			boolean[][] visited = new boolean[N][M];
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] > 0 && !visited[i][j]) {
						getGroup(newBoard, visited, N, M, i, j);
						group++;
						flag = true;
					}
				}
			}

			if (!flag) {
				System.out.println(0);
				break;
			}
			if (group >= 2) {
				System.out.println(time);
				break;
			}

		}

		return ret;
	}

	public static int[][] melting(int[][] board, int[][] newBoard, int N, int M, int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i], nx = x + dx[i];
			if (-1 < ny && ny < N && -1 < nx && nx < M) {
				if (board[ny][nx] <= 0) {
					newBoard[y][x] -= 1;
				}
			}
		}
		return newBoard;
	}

	public static void getGroup(int[][] board, boolean[][] visited, int N, int M, int y, int x) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { y, x });
		visited[y][x] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < M) {
					if (!visited[ny][nx] && board[ny][nx] > 0) {
						visited[ny][nx] = true;
						queue.add(new int[] { ny, nx });
					}
				}
			}
		}
	}

	public static int[][] clone(int[][] board, int N, int M) {
		int[][] ret = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ret[i][j] = board[i][j];
			}
		}
		return ret;
	}

}
