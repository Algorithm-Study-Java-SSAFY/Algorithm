package 기출문제.파이프옮기기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	static int answer = 0;

	static int[] dy = { 0, 1, 1 };
	static int[] dx = { 1, 1, 0 };
	
	static int[][] board;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		solution(0, 0, 1);
		System.out.println(answer);
	}

	public static void solution(int direct, int y, int x) {
		if (y == N - 1 && x == N - 1) {
			answer++;
			return;
		}

		if (direct == 0) { // 우
			for (int i = 0; i <= 1; i++) {
				int ny = y + dy[i], nx = x + dx[i];
				if(isGo(i, ny, nx)) {
					solution(i, ny, nx);
				}
			}
		} else if (direct == 2) { // 아래
			for (int i = 1; i <= 2; i++) {
				int ny = y + dy[i], nx = x + dx[i];
				if(isGo(i, ny, nx)) {
					solution(i, ny, nx);
				}
			}
		} else {
			for (int i = 0; i <= 2; i++) { // 우측아래
				int ny = y + dy[i], nx = x + dx[i];
				if(isGo(i, ny, nx)) {
					solution(i, ny, nx);
				}
			}
		}
	}

	public static boolean isGo(int direct, int y, int x) {
		if (!(-1 < y && y < N && -1 < x && x < N) || board[y][x] == 1) {
			return false;
		}
		if (direct == 1 && ((y - 1 > -1 && board[y - 1][x] == 1) || (x - 1 > -1 && board[y][x - 1] == 1))) {
			return false;
		}
		return true;
	}
}
