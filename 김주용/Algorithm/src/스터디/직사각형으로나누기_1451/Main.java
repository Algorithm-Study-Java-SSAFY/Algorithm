package 스터디.직사각형으로나누기_1451;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

	static int N;
	static int M;
	static int[][] board;
	static int[][] sumBoard;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		board = new int[N + 1][M + 1];
		sumBoard = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String cur = in.readLine();
			for (int j = 1; j <= M; j++) {
				board[i][j] = cur.charAt(j-1) - '0';
			}

		}
		// 누적합
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sumBoard[i][j] = sumBoard[i - 1][j] + sumBoard[i][j - 1] - sumBoard[i - 1][j - 1] + board[i][j];
			}
		}
		solution();
	}

	// 총 6가지 경우의 수
	public static void solution() {
		long answer = 0;
		// 1. ㅣ
		for (int i = 1; i <= M - 2; i++) {
			for (int j = i + 1; j <= M - 1; j++) {
				long a = getSum(1, 1, N, i);
				long b = getSum(1, i + 1, N, j);
				long c = getSum(1, j + 1, N, M);
				answer = Math.max(answer, a * b * c);
			}
		}
		// 2. -
		// -
		for (int i = 1; i <= N - 2; i++) {
			for (int j = i + 1; j <= N - 1; j++) {
				long a = getSum(1, 1, i, M);
				long b = getSum(i + 1, 1, j, M);
				long c = getSum(j + 1, 1, N, M);
				answer = Math.max(answer, a * b * c);
			}
		}

		// 3. ㅏ
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 1; j <= M - 1; j++) {
				long a = getSum(1, 1, N, j);
				long b = getSum(1, j + 1, i, M);
				long c = getSum(i + 1, j + 1, N, M);
				answer = Math.max(answer, a * b * c);
			}
		}
		// 4. ㅓ
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 1; j <= M - 1; j++) {
				long a = getSum(1, 1, i, j);
				long b = getSum(i + 1, 1, N, j);
				long c = getSum(1, j + 1, N, M);
				answer = Math.max(answer, a * b * c);
			}
		}

		// 5. ㅜ
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 1; j <= M - 1; j++) {
				long a = getSum(1, 1, i, M);
				long b = getSum(i + 1, 1, N, j);
				long c = getSum(i + 1, j + 1, N, M);
				answer = Math.max(answer, a * b * c);
			}
		}

		// 6. ㅗ
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 1; j <= M - 1; j++) {
				long a = getSum(1, 1, i, j);
				long b = getSum(1, j + 1, i, M);
				long c = getSum(i + 1, 1, N, M);
				answer = Math.max(answer, a * b * c);
			}
		}
		System.out.println(answer);

	}

	public static int getSum(int sy, int sx, int dy, int dx) {
		return sumBoard[dy][dx] - sumBoard[dy][sx - 1] - sumBoard[sy - 1][dx] + sumBoard[sy - 1][sx - 1];
	}
}
