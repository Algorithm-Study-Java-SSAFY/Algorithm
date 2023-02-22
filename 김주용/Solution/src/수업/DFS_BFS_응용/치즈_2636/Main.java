package DFS_BFS_응용.치즈_2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		int R = Integer.parseInt(line[0]), C = Integer.parseInt(line[1]);
		int[][] board = new int[R][C];

		for (int i = 0; i < R; i++) {
			line = in.readLine().split(" ");
			board[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
		}

		solution(board, R, C);

		// System.out.println(answer);
	}

	public static void solution(int[][] board, int R, int C) {
		int ret = 0;
		ArrayList<Integer> cheese = new ArrayList<>();

		while (true) {
			boolean flag = false;
			boolean[][] visited = getBorder(board, R, C);
			cheese.add(getCheese(board, R, C));
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (board[i][j] == 1) {
						melting(board, visited, R, C, i, j);
						flag = true;
					}
				}
			}

			if (!flag) {
				break;
			}

			ret++;
		}

		System.out.println(ret);

		for (int i = cheese.size() - 1; i >= 0; i--) {
			if (cheese.get(i) != 0) {
				ret = cheese.get(i);
				break;
			}
		}
		System.out.println(ret);

	}

	public static void melting(int[][] board, boolean[][] visited, int R, int C, int y, int x) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d], nx = x + dx[d];
			if (-1 < ny && ny < R && -1 < nx && nx < C) {
				if (visited[ny][nx]) {
					board[y][x] = 0;
					break;
				}
			}
		}
	}

	public static boolean[][] getBorder(int[][] board, int R, int C) {
		boolean[][] visited = new boolean[R][C];

		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, 0 });
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < R && -1 < nx && nx < C) {
					if (board[ny][nx] == 0 && !visited[ny][nx]) {
						visited[ny][nx] = true;
						queue.add(new int[] { ny, nx });
					}
				}
			}

		}
		return visited;
	}

	public static int getCheese(int[][] board, int R, int C) {
		int ret = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == 1) {
					ret++;
				}
			}
		}
		return ret;
	}

}
