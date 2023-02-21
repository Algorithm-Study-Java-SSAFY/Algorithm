package 기출문제.감시_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.event.AncestorEvent;

public class Main {
	// 위 아래 오
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static int N;
	static int M;
	static int[][] board;
	static int[][] visited;

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		solution(board);

		System.out.println(answer);
	}

	public static int solution(int[][] board) {
		int answer = 0;

		ArrayList<int[]> cctvs = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] > 0 && board[i][j] < 6) {
					cctvs.add(new int[] { i, j });
				}
			}
		}
		int[][] visited = new int[N][M];
		dfs(cctvs, visited, 0);

		return answer;

	}

	public static void dfs(ArrayList<int[]> cctvs, int[][] visited, int cur) {
		if (cur == cctvs.size()) {
			int ret = goCCTV(visited);
			answer = Math.min(answer, ret);
			return;
		}

		int[] info = cctvs.get(cur);
		int y = info[0], x = info[1];
		if (board[y][x] == 2) {
			for (int d = 0; d < 2; d++) {
				int[][] newVisited = clone(visited);
				newVisited[y][x] = d + 1;
				dfs(cctvs, newVisited, cur + 1);
			}
		} else {
			for (int d = 0; d < 4; d++) {
				int[][] newVisited = clone(visited);
				newVisited[y][x] = d + 1;
				dfs(cctvs, newVisited, cur + 1);
			}
		}

	}

	public static int goCCTV(int[][] visited) {
		int[][] newBoard = clone(board);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] > 0) {
					getSafeZone(newBoard, board[i][j], visited[i][j], i, j);
				}
			}
		}

		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newBoard[i][j] == 0) {
					ret++;
				}
			}
		}
		return ret;
	}

	/*
	 * 1 : 위 아래 오 왼 2 : 가로 세로 3 : 수직 직선 4 : 한 뱡향 빼고 직선 5 : 4방향 다
	 */
	public static void getSafeZone(int[][] newBoard, int num, int direct, int y, int x) {
		
		switch (num) {
		case 1:
			if (direct == 1) {
				goRight(newBoard, y, x);
			}
			if (direct == 2) {
				goLeft(newBoard, y, x);
			}
			if (direct == 3) {
				goUp(newBoard, y, x);
			}
			if (direct == 4) {
				goDown(newBoard, y, x);
			}
			break;
		case 2:
			if (direct == 1) {
				goRight(newBoard, y, x);
				goLeft(newBoard, y, x);
			}
			if (direct == 2) {
				goUp(newBoard, y, x);
				goDown(newBoard, y, x);
			}
			break;
		case 3:
			if (direct == 1) {
				goUp(newBoard, y, x);
				goRight(newBoard, y, x);
			}
			if (direct == 2) {
				goDown(newBoard, y, x);
				goRight(newBoard, y, x);
			}
			if (direct == 3) {
				goDown(newBoard, y, x);
				goLeft(newBoard, y, x);
			}
			if (direct == 4) {
				goUp(newBoard, y, x);
				goLeft(newBoard, y, x);
			}
			break;

		case 4:
			if (direct == 1) {
				goUp(newBoard, y, x);
				goRight(newBoard, y, x);
				goLeft(newBoard, y, x);
			}
			if (direct == 2) {
				goUp(newBoard, y, x);
				goDown(newBoard, y, x);
				goRight(newBoard, y, x);
			}
			if (direct == 3) {
				goDown(newBoard, y, x);
				goLeft(newBoard, y, x);
				goRight(newBoard, y, x);
			}
			if (direct == 4) {
				goUp(newBoard, y, x);
				goLeft(newBoard, y, x);
				goDown(newBoard, y, x);
			}
			break;
		case 5:
			goRight(newBoard, y, x);
			goLeft(newBoard, y, x);
			goUp(newBoard, y, x);
			goDown(newBoard, y, x);
			break;

		}
	}

	public static void goRight(int[][] newBoard, int curY, int curX) {
		for (int nx = curX + 1; nx < M; nx++) {
			if (isGo(curY, nx)) {
				newBoard[curY][nx] = -1;
			} else {
				break;
			}
		}
	}

	public static void goLeft(int[][] newBoard, int curY, int curX) {
		for (int nx = curX - 1; nx >= 0; nx--) {
			if (isGo(curY, nx)) {
				newBoard[curY][nx] = -1;
			} else {
				break;
			}
		}
	}

	public static void goUp(int[][] newBoard, int curY, int curX) {
		for (int ny = curY - 1; ny >= 0; ny--) {
			if (isGo(ny, curX)) {
				newBoard[ny][curX] = -1;
			} else {
				break;
			}
		}
	}

	public static void goDown(int[][] newBoard, int curY, int curX) {
		for (int ny = curY + 1; ny < N; ny++) {
			if (isGo(ny, curX)) {
				newBoard[ny][curX] = -1;
			} else {
				break;
			}
		}
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

	public static boolean isGo(int ny, int nx) {
		if (!(-1 < ny && ny < N && -1 < nx && nx < M) || board[ny][nx] == 6) {
			return false;
		}
		return true;
	}

}
