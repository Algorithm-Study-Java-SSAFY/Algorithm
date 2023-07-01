package 스터디.주사위굴리기2_23288;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
	// 시계방향: 동 남 서 북
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	// { 아래, 북, 위, 남, 동, 서 };

	static HashMap<Character, Integer> dice = new HashMap<Character, Integer>() {
		{
			put('D', 3);
			put('U', 4);
			put('N', 2);
			put('E', 1);
			put('W', 6);
			put('S', 5);
		}
	};

	static int N;
	static int M;
	static int K;

	static int[][] board;
	static int[][] scoreBoard;

	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);

		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		solution();
	}

	public static void solution() {
		scoreBoard = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				scoreBoard[i][j] = getScore(i, j, board[i][j]) * board[i][j];
			}
		}

		simulation(0, 1, 0, 0);
		System.out.println(answer);
	}

	public static void simulation(int cy, int cx, int cd, int k) {
		if (k == K) {
			return;
		}

		// 2
		int cur = board[cy][cx];
		answer += scoreBoard[cy][cx];

		// 3
		if (dice.get('D') > cur) {
			cd = (cd + 1) % 4;
		} else if (dice.get('D') < cur) {
			cd = (cd != 0) ? cd - 1 : 3;
		}

		int ny = cy + dy[cd], nx = cx + dx[cd];
		// 1: 범위 밖이면 반대 방향
		if (!(-1 < ny && ny < N && -1 < nx && nx < M)) {
			cd = (cd + 2) % 4;
			ny = cy + dy[cd];
			nx = cx + dx[cd];
			rotateDice(cd);
		} else {
			rotateDice(cd);
		}

		simulation(ny, nx, cd, k + 1);
	}

	public static void rotateDice(int curD) {
		HashMap<Character, Integer> newDice = (HashMap<Character, Integer>) dice.clone();
		/*
		 * 진행방향 = 바닥 
		 * 동<->서 남 <-> 북 은 서로 반대
		 */
		if (curD == 0) { // 동
			dice.put('E', newDice.get('U'));
			dice.put('U', newDice.get('W'));
			dice.put('W', newDice.get('D'));
			dice.put('D', newDice.get('E'));
		} else if (curD == 2) { // 서
			dice.put('E', newDice.get('D'));
			dice.put('U', newDice.get('E'));
			dice.put('W', newDice.get('U'));
			dice.put('D', newDice.get('W'));
		} else if (curD == 1) { // 남
			dice.put('S', newDice.get('U'));
			dice.put('U', newDice.get('N'));
			dice.put('N', newDice.get('D'));
			dice.put('D', newDice.get('S'));
		} else { // 북
			dice.put('S', newDice.get('D'));
			dice.put('U', newDice.get('S'));
			dice.put('N', newDice.get('U'));
			dice.put('D', newDice.get('N'));
		}
	}

	public static int getScore(int y, int x, int num) {
		int ret = 1;
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new int[] { y, x });
		visited[y][x] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < M && !visited[ny][nx] && board[ny][nx] == num) {
					queue.add(new int[] { ny, nx });
					visited[ny][nx] = true;
					ret++;
				}
			}
		}
		return ret;
	}

}
