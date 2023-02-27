package 수업.큐.미로1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution {
	static int N = 16;
	static char[][] board;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		board = new char[N][N];
		for (int test_case = 1; test_case <= T; test_case++) {
			String t = in.readLine();
			for (int i = 0; i < N; i++) {
				board[i] = in.readLine().toCharArray();
			}
			int answer = solution();
			System.out.printf("#%s %d\n", t, answer);
		}
	}

	public static int solution() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.add(new int[] { 1, 1 });
		visited[1][1] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N) {
					if (board[ny][nx] == '3') {
						return 1;
					}
					if (board[ny][nx] == '0' && !visited[ny][nx]) {
						visited[ny][nx] = true;
						queue.add(new int[] { ny, nx });
					}
				}
			}
		}
		return 0;
	}
}
