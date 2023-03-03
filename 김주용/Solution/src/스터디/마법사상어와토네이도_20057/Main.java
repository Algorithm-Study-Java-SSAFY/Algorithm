package 스터디.마법사상어와토네이도_20057;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

import SWExpert.최대상금_1244.Solution;

public class Main {
	// 왼 아래 오 위
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { -1, 0, 1, 0 };

	static int N;
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		tornado();

	}

	/*
	 * 1 1 -> 2 2 -> 3 3 -> 4 4 -> 6 6 - > 7 만큼 방향마다 가게 됨
	 */
	public static void tornado() {
		int initSand = getSand();
		int y = N / 2, x = N / 2;
		int canGO = 0;
		int k = 0, curD = 0;
		int answer = 0;
		int c = 0;
		while (k < 2 * N - 1) { // 마지막은 한번만
			if (k % 2 == 0) { // 현재 방향에서 진행하는 칸 수
				canGO++;
			}
			for (int i = 0; i < canGO; i++) { // 한칸 이동
				int ny = y + dy[curD], nx = x + dx[curD];
				if (-1 < ny && ny < N && -1 < nx && ny < N) {
					if (curD == 0 || curD == 2) {
						moveSandVertical(y, x, ny, nx, curD, board[ny][nx]);
					} else {
						moveSandHorizon(y, x, ny, nx, curD, board[ny][nx]);
					}
					debug(y + " " + x + " " + ny + " " + nx + " " + curD + " " + c);
					c++;
					y = ny;
					x = nx;
				}
			}
			curD = (curD + 1) % 4; // 방향 전환
			k++;
		}
		int remainSand = getSand();
		System.out.println(initSand + " " + remainSand);
		int ret = initSand - remainSand;
		System.out.println(ret);
	}

	// cur -> next 로 이동할 때 next의 모래가 비율과 그 나머지 만큼 이동함.
	public static void moveSandVertical(int curY, int curX, int nextY, int nextX, int curD, int sand) {
		int tempSand = sand;
		board[nextY][nextX] = 0;
		int[] startDy = { -1, -2, -1, 0 };
		int[] endDy = { 1, 2, 1, 0 };
		Double[] ratio = { 0.01, 0.07, 0.1, 0.05 };

		for (int i = 0; i < 4; i++) {
			// System.out.print(curX + ": ");
			for (int y = startDy[i]; y <= endDy[i]; y++) {
				double r = (y == -2 || y == 2) ? 0.02 : ratio[i];
				double curSand = Math.floor(sand * r);
				if (i < 3 && y == 0) {
					continue;
				}
				if (curY + y < 0 || curY + y >= N) {
					tempSand -= curSand;
					continue;
				}
				
				if(curY + y == N/2 && curX == N / 2) {
					continue;
				}

				board[curY + y][curX] += curSand;
				tempSand -= curSand;

			}

			curX += dx[curD];
			if (curX < 0 || curX >= N)
				break;
		}
		int alphaX = nextX + dx[curD];
		if (-1 < alphaX && alphaX < N && !(alphaX == N/2 && curY == N/2) ) {
			board[curY][alphaX] += tempSand;
			System.out.println(curY + " " + alphaX + " " + tempSand);
		}
	}

	public static void moveSandHorizon(int curY, int curX, int nextY, int nextX, int curD, int sand) {
		int tempSand = sand;
		board[nextY][nextX] = 0;
		int[] startDx = { -1, -2, -1, 0 };
		int[] endDx = { 1, 2, 1, 0 };
		Double[] ratio = { 0.01, 0.07, 0.1, 0.05 };

		for (int i = 0; i < 4; i++) {
			;
			for (int x = startDx[i]; x <= endDx[i]; x++) {
				double r = (x == -2 || x == 2) ? 0.02 : ratio[i];
				double curSand = Math.floor(sand * r);

				if (i < 3 && x == 0)
					continue;
				if (curX + x < 0 || curX + x >= N) {
					tempSand -= curSand;
					continue;
				}
				if(curX + x == N/2 && curY == N / 2) {
					continue;
				}

				board[curY][curX + x] += curSand;
				tempSand -= curSand;

			}

			curY += dy[curD];
			if (curY < 0 || curY >= N)
				break;
		}
		int alphaY = nextY + dy[curD];
		if (-1 < alphaY && alphaY < N && !(alphaY == N/2 && curX == N/2)) {
			board[alphaY][curX] += tempSand;
		}
	}

	public static void debug(String msg) {
		System.out.println(msg);
		System.out.println("----");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	public static int getSand() {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ret += board[i][j];
			}
		}
		return ret;
	}
}
