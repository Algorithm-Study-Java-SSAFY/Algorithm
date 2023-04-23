package 스터디.미친아두이노_8972;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Point {
	int y;
	int x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {

	static int N;
	static int M;
	static char[][] board;
	static int[][] madRobotNum;

	static Point robot;
	static ArrayList<Point> madRobots;

	static String directs;

	static int[] dy = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dx = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		board = new char[N][M];
		madRobots = new ArrayList<>();
		madRobotNum = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'R') {
					madRobots.add(new Point(i, j));
				}
				if (board[i][j] == 'I') {
					robot = new Point(i, j);
				}
			}
		}
		directs = in.readLine();
		String ret = solution();
		if (ret.equals("FINISH")) {
			printBoard();
		} else {
			System.out.println(ret);
		}
	}

	public static String solution() {
		for (int i = 0; i < directs.length(); i++) {
			int d = directs.charAt(i) - '0';
			if (!moveRobot(d)) {
				return "kraj" + " " + (i + 1);
			}
			if (!moveMadRobots()) {
				return "kraj" + " " + (i + 1);
			}
		}
		return "FINISH";
	}

	// 1. 2. : 종수 아두이노 움직이기
	public static boolean moveRobot(int d) {
		int ny = robot.y + dy[d], nx = robot.x + dx[d];
		if (board[ny][nx] == 'R') {
			return false;
		}
		board[robot.y][robot.x] = '.';
		board[ny][nx] = 'I';
		robot.y = ny;
		robot.x = nx;
		return true;
	}

	public static boolean moveMadRobots() {
		for (Point cur : madRobots) {
			int d = getDirect(cur);
			if (!moveMadRobot(cur, d)) {
				return false;
			}
		}
		// 2개 이상 미친 로봇 지우고 갱신
		madRobots.clear();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (madRobotNum[i][j] >= 2) {
					board[i][j] = '.';
				} else if (madRobotNum[i][j] == 1) {
					madRobots.add(new Point(i, j));
					board[i][j] = 'R';
				} else if (board[i][j] != 'I') {
					board[i][j] = '.';
				}
				madRobotNum[i][j] = 0;
			}
		}

		return true;
	}

	public static boolean moveMadRobot(Point cur, int d) {
		int ny = cur.y + dy[d], nx = cur.x + dx[d];
		if (board[ny][nx] == 'I') {
			return false;
		}
		madRobotNum[ny][nx] += 1;
		cur.y = ny;
		cur.x = nx;
		return true;
	}

	public static int getDirect(Point cur) {
		int minDist = Integer.MAX_VALUE;
		int direct = -1;
		for (int d = 1; d <= 9; d++) {
			int ny = cur.y + dy[d], nx = cur.x + dx[d];
			if (!inRange(ny, nx)) {
				continue;
			}
			int dist = Math.abs(robot.y - ny) + Math.abs(robot.x - nx);
			if (dist < minDist) {
				minDist = dist;
				direct = d;
			}
		}
		return direct;
	}

	public static boolean inRange(int y, int x) {
		return -1 < y && y < N && -1 < x && x < M;
	}

	public static void printBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
