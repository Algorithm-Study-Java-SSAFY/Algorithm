package 기출문제.마법사상어와파이어스톰;

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

	static int N;
	static int Q;

	static int[][] board;
	static int[][] newBoard;

	static int[] levels;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		Q = Integer.parseInt(line[1]);
		N = (int) Math.pow(2, N);

		board = new int[N][N];
		newBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			line = in.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(line[j]);
			}
		}

		levels = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		solution();
		
		in.close();
	}

	public static void solution() {
		for (int i = 0; i < Q; i++) {
			int n = (int) Math.pow(2, levels[i]);
			divide(n);
			meltingIce();
		}
		int sumIce = 0;
		int group = 0;
		int[][] visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] > 0) {
					sumIce += board[i][j];
					if (visited[i][j] < 1) {
						group = Math.max(group, bfs(visited, i, j));
					}
				}
			}
		}
		System.out.println(sumIce);
		System.out.println(group);
	}

	public static void divide(int n) {
		copy();
		for (int i = 0; i < N; i += n) {
			for (int j = 0; j < N; j += n) {
				rotate(i, j, n);
			}
		}
	}

	public static void rotate(int sy, int sx, int length) {
		//int[][] newBoard = copy();
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < length; x++) {
				board[sy + y][sx + x] = newBoard[sy + length - x - 1][sx + y];
			}
		}
	}

	public static void meltingIce() {
		copy();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d], nx = j + dx[d];
					if (-1 < ny && ny < N && -1 < nx && nx < N && newBoard[ny][nx] > 0) {
						cnt++;
					}
				}
				if (cnt < 3 && board[i][j] > 0) {
					board[i][j] -= 1;
				}
			}
		}
	}

	public static int bfs(int[][] visited, int y, int x) {
		int ret = 1;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { y, x });
		visited[y][x] = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N) {
					if (visited[ny][nx] < 1 && board[ny][nx] > 0) {
						visited[ny][nx] = 1;
						ret++;
						queue.add(new int[] { ny, nx });
					}
				}
			}
		}
		return ret;
	}

	public static void copy() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				newBoard[y][x] = board[y][x];
			}
		}
	
	}

	public static void debug() {
		for (int[] line : board) {
			System.out.println(Arrays.toString(line));
		}
		System.out.println(" ");
	}

}
