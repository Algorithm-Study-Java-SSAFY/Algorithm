package 기출문제.캐슬디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int M;
	static int D;

	static int[][] board;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		D = Integer.parseInt(line[2]);
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		solution(new boolean[M], 0, 3);
		System.out.println(answer);
	}

	public static void solution(boolean[] visited, int start, int r) {
		if (r == 0) {
			int[][] archors = new int[3][2];
			int idx = 0;
			;
			for (int i = 0; i < M; i++) {
				if (visited[i]) {
					archors[idx++] = new int[] { N, i };
				}
			}
			answer = Math.max(answer, simulation(clone(board), archors));
			return;
		}

		for (int i = start; i < M; i++) {
			visited[i] = true;
			solution(visited, i + 1, r - 1);
			visited[i] = false;
		}
	}

	public static int simulation(int[][] board, int[][] archors) {
		int ret = 0;
		while (true) {
			ret += attack(board, archors);
			boolean flag = goMonster(board);
			if (!flag) {
				break;
			}
		}
		return ret;
	}

	public static int attack(int[][] board, int[][] archors) {
		int[][] newBoard = clone(board);
		int ret = 0;

		for (int[] info : archors) {
			int curR = info[0], curC = info[1];
			PriorityQueue<AttackPoint> canAttacks = new PriorityQueue<>();
			for (int r = N - 1; r >= 0; r--) {
				for (int c = 0; c < M; c++) {
					int dist = getDist(curR, curC, r, c);
					if (newBoard[r][c] == 1 && dist <= D) { // 젤 가까운거 선택
						canAttacks.add(new AttackPoint(r, c, dist));
					}
				}
			}
			if (canAttacks.size() > 0) {
				AttackPoint attackPoint = canAttacks.peek();
				if (board[attackPoint.y][attackPoint.x] == 1) {
					ret++;
				}
				board[attackPoint.y][attackPoint.x] = 0;
			}
		}
		return ret;
	}

	public static boolean goMonster(int[][] board) {
		boolean flag = false;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					flag = true;
					int ny = i + 1;
					if (ny == N) {
						board[i][j] = 0;
					} else {
						board[i][j] = 0;
						board[ny][j] = 1;
					}
				}
			}
		}
		return flag;
	}

	public static int getDist(int r1, int c1, int r2, int c2) {
		int dist = Math.abs(r1 - r2) + Math.abs(c1 - c2);
		return dist;
	}

	public static int[][] clone(int[][] arr) {
		int[][] newArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newArr[i][j] = arr[i][j];
			}
		}
		return newArr;
	}

	static class AttackPoint implements Comparable<AttackPoint> {
		int y;
		int x;
		int dist;

		public AttackPoint(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		@Override
		public int compareTo(AttackPoint o) {
			if (this.dist > o.dist) {
				return 1;
			} else if (this.dist == o.dist) {
				if (this.x > o.x) {
					return 1;
				}
			}
			return -1;
		}

		@Override
		public String toString() {
			return "AttackPoint [y=" + y + ", x=" + x + ", dist=" + dist + "]";
		}
	}
}
