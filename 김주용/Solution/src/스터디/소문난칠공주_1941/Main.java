package 스터디.소문난칠공주_1941;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static int N = 5;
	static char[][] board = new char[5][5];

	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			board[i] = in.readLine().toCharArray();
		}

		solution();
	}

	public static void solution() {
		boolean[] visited = new boolean[26];
		int[] arr = new int[26];
		combination(arr, visited, 1, 26, 7); // 25C7 : 480700
		System.out.println(answer);
	}

	public static void combination(int[] arr, boolean visited[], int cur, int n, int r) {
		if (r == 0) {
			if (check(visited)) {
				answer++;
			} 
			return;
		}

		for (int i = cur; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	public static boolean check(boolean[] visited) {
		boolean[][] path = new boolean[N][N];
		int sy = -1, sx = -1;
		int countS = 0, countY = 0;
		// 좌표로 바꿔주고 도연파가 많으면 불가능
		for (int i = 1; i <= 25; i++) {
			if (visited[i]) {
				sy = (i - 1) / N;
				sx = (i - 1) % N;
				if (board[sy][sx] == 'S') {
					countS++;
				} else {
					countY++;
				}
				path[sy][sx] = true;
			}
		}
		if (countS < 4) {
			return false;
		}
		// 서로 인접한지
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] newVisited = new boolean[N][N];
		newVisited[sy][sx] = true;
		queue.add(new int[] { sy, sx });

		int ret = 1;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N && path[ny][nx] && !newVisited[ny][nx]) {
					ret++;
					newVisited[ny][nx] = true;
					queue.add(new int[] { ny, nx });
				}
			}
		}

		if (ret == 7) {
			return true;
		}

		return false;
	}

}
