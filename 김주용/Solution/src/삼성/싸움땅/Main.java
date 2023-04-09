package 삼성.싸움땅;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Player {
	int num;
	int y;
	int x;
	int d;
	int s;
	int gun;
	int point;

	public Player(int num, int y, int x, int d, int s) {
		this.num = num;
		this.y = y;
		this.x = x;
		this.d = d;
		this.s = s;
	}

	@Override
	public String toString() {
		return "Player [num=" + num + ", y=" + y + ", x=" + x + ", d=" + d + ", s=" + s + ", gun=" + gun + ", point="
				+ point + "]";
	}

}

public class Main {
	static int N;
	static int M;
	static int K;

	static PriorityQueue<Integer>[][] gunBoard;
	static int[][] playerBoard;
	static Player[] players;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		K = line[2];

		gunBoard = new PriorityQueue[N][N];
		playerBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < N; j++) {
				gunBoard[i][j] = new PriorityQueue<>(Collections.reverseOrder());
				if (line[j] == 0)
					continue;
				gunBoard[i][j].add(line[j]);
			}
		}
		players = new Player[M + 1];
		for (int i = 1; i <= M; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			players[i] = new Player(i, line[0] - 1, line[1] - 1, line[2], line[3]);
			playerBoard[line[0] - 1][line[1] - 1] = i;
		}
		solution();
	}

	public static void solution() {
		for (int i = 0; i < K; i++) {
			for (int num = 1; num <= M; num++) {
				Player cur = players[num];
				int[] next = movePlayer(cur);
				int ny = next[0], nx = next[1];
				if (playerBoard[ny][nx] == 0) { // 플레이어가 없다면
					move(cur, ny, nx);
					getGun(cur);
					continue;
				}
				Player other = players[playerBoard[ny][nx]];
				boolean isWin = fight(cur, other);
				int point = Math.abs((cur.s + cur.gun) - (other.s + other.gun));
				move(cur, ny, nx);
				if (isWin) { // 현재 플레이어가 승리
					cur.point += point;
					losePlayer(other);
					move(cur, ny, nx);
					getGun(other);
					getGun(cur);
				} else {
					other.point += point;
					losePlayer(cur);
					move(other, ny, nx);
					getGun(cur);
					getGun(other);
				}
			}
		}
		for(int i = 1; i <= M; i++) {
			System.out.print(players[i].point + " ");
		}
	}

	public static int[] movePlayer(Player cur) {
		int d = cur.d;
		int ny = cur.y + dy[d], nx = cur.x + dx[d];
		if (!isRange(ny, nx)) {
			d = (d + 2) % 4;
			cur.d = d;
			ny = cur.y + dy[d];
			nx = cur.x + dx[d];
		}
		return new int[] { ny, nx };
	}

	public static void getGun(Player cur) {
		int y = cur.y, x = cur.x;
		if (gunBoard[y][x].size() == 0) {
			return;
		}
		if (cur.gun == 0) {
			cur.gun = gunBoard[y][x].poll();
			return;
		}
		if (cur.gun < gunBoard[y][x].peek()) {
			gunBoard[y][x].add(cur.gun);
			cur.gun = gunBoard[y][x].poll();
		}
	}

	public static boolean fight(Player cur, Player other) {
		int curScore = cur.s + cur.gun;
		int otherScore = other.s + other.gun;
		if (curScore > otherScore) {
			return true;
		}
		if (curScore == otherScore && cur.s > other.s) {
			return true;
		}
		return false;
	}

	public static void losePlayer(Player loser) {
		gunBoard[loser.y][loser.x].add(loser.gun);
		loser.gun = 0;

		for (int i = 0; i < 4; i++) {
			int nd = (loser.d + i) % 4;
			int ny = loser.y + dy[nd];
			int nx = loser.x + dx[nd];
			if(isRange(ny, nx) && playerBoard[ny][nx] == 0) {
				loser.d = nd;
				move(loser, ny, nx);
				return;
			}
		}

	}
	
	public static void move(Player cur, int ny, int nx) {
		playerBoard[cur.y][cur.x] = 0;
		cur.y = ny;
		cur.x = nx;
		playerBoard[ny][nx] = cur.num;
	}

	private static boolean isRange(int ny, int nx) {
		return -1 < ny && ny < N && -1 < nx && nx < N;
	}
	
	public static void debug() {
		for(int [] i : playerBoard) {
			System.out.println(Arrays.toString(i));
		}
		System.out.println(" ");
	}

}
