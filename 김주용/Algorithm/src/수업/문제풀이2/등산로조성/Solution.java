package 수업.문제풀이2.등산로조성;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int N;
	static int K;

	static int[][] board;
	static boolean[][] visited;
	static int maxHeight;
	static boolean canDown;

	static int answer = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			K = Integer.parseInt(line[1]);
			board = new int[N][N];
			visited = new boolean[N][N];
			maxHeight = 0;
			for (int i = 0; i < N; i++) {
				line = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(line[j]);
					maxHeight = Math.max(maxHeight, board[i][j]);
				}
			}
			answer = 0;
			solution();
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static void solution() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == maxHeight) {
					init();
					visited[i][j] = true;
					dfs(i, j, 1);
				}
			}
		}
	}

	public static void dfs(int y, int x, int depth) {
		answer = Math.max(answer, depth);
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i], nx = x + dx[i];
			if (-1 < ny && ny < N && -1 < nx && nx < N && !visited[ny][nx]) {;
				if (board[ny][nx] < board[y][x]) {
					visited[ny][nx] = true;
					dfs(ny, nx, depth + 1);
					visited[ny][nx] = false;
				} else if (canDown ) {
					for(int k = 1; k <= K; k++ ) {
						if(board[ny][nx] - k >= board[y][x]) {
							continue;
						}
						visited[ny][nx] = true;
						canDown = false;
						board[ny][nx] -= k;
						dfs(ny, nx, depth + 1);
						canDown = true;
						board[ny][nx] += k;
						visited[ny][nx] = false;
					}
				}
			}
		}
	}

	public static void init() {
		canDown = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
	}

	public static void debug(String msg) {
		System.out.println(msg);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
}
