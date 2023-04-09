package 삼성.마법사상어와파이어볼_20056;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class Fireball {
	int y;
	int x;
	int m;
	int s;
	int d;

	public Fireball(int y, int x, int m, int s, int d) {
		this.y = y;
		this.x = x;
		this.m = m;
		this.s = s;
		this.d = d;
	}

	@Override
	public String toString() {
		return "Fireball [y=" + y + ", x=" + x + ", m=" + m + ", s=" + s + ", d=" + d + "]";
	}

}

public class Main {

	static int N;
	static int M;
	static int K;
	static ArrayList<Fireball>[][] board;
	static ArrayList<Fireball>[][] tempBoard;

	static Queue<Fireball> queue;

	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		K = line[2];

		board = new ArrayList[N + 1][N + 1];
		tempBoard = new ArrayList[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = new ArrayList<>();
				tempBoard[i][j] = new ArrayList<>();
			}
		}

		queue = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int r = line[0] - 1, c = line[1] - 1;
			int m = line[2], s = line[3], d = line[4];
			Fireball cur = new Fireball(r, c, m, s, d);
			board[r][c].add(cur);
		}
		solution();
	}

	public static void solution() {
		for (int i = 0; i < K; i++) {
			moveFireball();
			copy();
			init();
//			debug();
			mergeAndDivide();
			copy();
			init();
//			debug();
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Fireball cur : board[i][j]) {
					answer += cur.m;
				}
			}
		}
		System.out.println(answer);
	}

	public static void mergeAndDivide() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int n = board[i][j].size();
				if (n < 2) {
					if (n == 1 && board[i][j].get(0).m > 0) {
						tempBoard[i][j].add(board[i][j].get(0));
					}
					continue;
				}
				Fireball merge = new Fireball(i, j, 0, 0, 0);
				int mergeM = 0, mergeS = 0, idx = 0;
				int[] dArr = new int[n];
				for (Fireball cur : board[i][j]) {
					mergeM += cur.m;
					mergeS += cur.s;
					dArr[idx++] = cur.d;
				}
				int[] mergeD = getDirect(dArr);
				if (mergeM / 5 < 1) {
					continue;
				}
				tempBoard[i][j].clear();
				for (int k = 0; k < 4; k++) {
					tempBoard[i][j].add(new Fireball(i, j, mergeM / 5, mergeS / n, mergeD[k]));
				}
			}
		}
	}

	public static int[] getDirect(int[] direct) {
		int odd = 0, even = 0;
		for (int d : direct) {
			if (d % 2 == 0) {
				odd++;
			} else {
				even++;
			}
		}
		if (odd == direct.length || even == direct.length) {
			return new int[] { 0, 2, 4, 6 };
		}
		return new int[] { 1, 3, 5, 7 };
	}

	public static void moveFireball() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Fireball cur : board[i][j]) {
					int ny = (cur.y + dy[cur.d] * cur.s) % N;
					int nx = (cur.x + dx[cur.d] * cur.s) % N;
					ny = (ny < 0) ? N - Math.abs(ny) : ny;
					nx = (nx < 0) ? N - Math.abs(nx) : nx;
					cur.y = ny;
					cur.x = nx;
					tempBoard[cur.y][cur.x].add(cur);
				}
			}
		}
	}

	public static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tempBoard[i][j].clear();
			}
		}
	}

	public static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j].clear();
				for (Fireball cur : tempBoard[i][j]) {
					board[i][j].add(cur);
				}
			}
		}
	}

	public static void debug() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Fireball cur : board[i][j]) {
					System.out.print(cur.m + " ");
				}
				System.out.print(" / ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

}
