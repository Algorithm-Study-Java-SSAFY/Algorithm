package 스터디.탈출_3055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int R;
	static int C;

	static int destY;
	static int destX;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static char[][] board;
	static int[][] visited;
	static ArrayList<char[][]> waterBoards = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);

		board = new char[R][C];
		visited = new int[R][C];
		int sy = 0, sx = 0;
		for (int i = 0; i < R; i++) {
			board[i] = in.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (board[i][j] == 'S') {
					sy = i;
					sx = j;
				}
				if (board[i][j] == 'D') {
					destY = i;
					destX = j;
				}
			}
		}

		getWaterBoards();

		int answer = bfs(sy, sx);

		if (answer == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(answer);
		}

	}

	public static int bfs(int sy, int sx) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { sy, sx });
		visited[sy][sx] = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int time = visited[cur[0]][cur[1]];

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (canGo(time - 1, ny, nx) && visited[ny][nx] == 0) {
					queue.add(new int[] { ny, nx });
					visited[ny][nx] = time + 1;
					if (ny == destY && nx == destX) {
						return time;
					}
				}
			}
		}
		return -1;
	}

	public static void getWaterBoards() {
		waterBoards.add(board);
		int idx = 1;
		while (true) {
			char[][] curBoard = waterBoards.get(idx - 1);
			char[][] newBoard = new char[R][C];
			copy(curBoard, newBoard);
			boolean flag = false;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (curBoard[i][j] == '*') {
						for (int d = 0; d < 4; d++) {
							int ny = i + dy[d], nx = j + dx[d];
							if (checkRange(ny, nx) && (curBoard[ny][nx] == '.' || curBoard[ny][nx] == 'S')) {
								newBoard[ny][nx] = '*';
								flag = true;
							}
						}
					}
				}
			}
			if (!flag) {
				break;
			}
			idx++;
			waterBoards.add(newBoard);
		}

	}

	public static void copy(char[][] curBoard, char[][] newBoard) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				newBoard[i][j] = curBoard[i][j];
			}
		}
	}

	public static boolean canGo(int depth, int ny, int nx) {
		char[][] curBoard;
		if(depth > waterBoards.size() - 1) {
			curBoard = waterBoards.get(waterBoards.size() - 1);
		} else {
			curBoard = waterBoards.get(depth);
		}

		if (ny == destY && nx == destX) {
			return true;
		}

		if (!checkRange(ny, nx)) {
			return false;
		}

		if (curBoard[ny][nx] == '*' || curBoard[ny][nx] == 'X') {
			return false;
		}

		for (int i = 0; i < 4; i++) {
			int nny = ny + dy[i], nnx = nx + dx[i];
			if (checkRange(nny, nnx) && curBoard[nny][nnx] == '*') {
				return false;
			}
		}

		return true;
	}

	public static boolean checkRange(int y, int x) {
		return -1 < y && y < R && -1 < x && x < C;
	}

}
