import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static final int[] DX = { 0, 1, 0, -1 };
	static final int[] DY = { 1, 0, -1, 0 };
	static final int EAST = 0;
	static final int SOUTH = 1;
	static final int WEST = 2;
	static final int NORTH = 3;
	static int R, D, L, U;
	static List<List<Integer>> Dice = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int move = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		Dice.add(0, new ArrayList<>());
		Dice.add(1, Arrays.asList(5, 3, 2, 4));
		Dice.add(2, Arrays.asList(3, 6, 4, 1));
		Dice.add(3, Arrays.asList(5, 6, 2, 1));
		Dice.add(4, Arrays.asList(5, 1, 2, 6));
		Dice.add(5, Arrays.asList(1, 4, 6, 3));
		Dice.add(6, Arrays.asList(3, 5, 4, 2));

		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			map[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
		}

		bw.write(String.valueOf(moveDice(move)));

		br.close();
		bw.close();
	}

	public static int moveDice(int move) {
		int total = 0;
		int x = 0;
		int y = 0;
		int direction = EAST;
		int bottom = 6;
		int next = 0;
		R = 0;
		D = 1;
		L = 2;
		U = 3;

		while (move > 0) {
			// 방향으로 옮길 시 바닥이 될 수 구하기 + 좌표 옮기기
			if (direction == EAST) {
				next = Dice.get(bottom).get(R);
				y++;
			} else if (direction == SOUTH) {
				next = Dice.get(bottom).get(D);
				x++;
			} else if (direction == WEST) {
				next = Dice.get(bottom).get(L);
				y--;
			} else if (direction == NORTH) {
				next = Dice.get(bottom).get(U);
				x--;
			}

			// 방향에 따른 주사위 배열 정렬
			sortDice(direction, bottom, next);

			// 점수 계산
			total += calculScore(x, y);

			// 방향 결정
			bottom = next;
			if (bottom > map[x][y]) {
				direction = (direction + 1) % 4;
			} else if (bottom < map[x][y]) {
				direction = (direction - 1 + 4) % 4;
			}
			if (y == 0 && direction == WEST)
				direction = EAST;
			if (x == 0 && direction == NORTH)
				direction = SOUTH;
			if (y == M - 1 && direction == EAST)
				direction = WEST;
			if (x == N - 1 && direction == SOUTH)
				direction = NORTH;

			move--;
		}

		return total;
	}

	public static void sortDice(int direction, int before, int now) {
		int before_direction = (direction + 2) % 4;
		if (before_direction == EAST) {
			R = Dice.get(now).indexOf(before);
			D = (R + 1) % 4;
			L = (R + 2) % 4;
			U = (R + 3) % 4;
		} else if (before_direction == SOUTH) {
			D = Dice.get(now).indexOf(before);
			L = (D + 1) % 4;
			U = (D + 2) % 4;
			R = (D + 3) % 4;
		} else if (before_direction == WEST) {
			L = Dice.get(now).indexOf(before);
			U = (L + 1) % 4;
			R = (L + 2) % 4;
			D = (L + 3) % 4;
		} else if (before_direction == NORTH) {
			U = Dice.get(now).indexOf(before);
			R = (U + 1) % 4;
			D = (U + 2) % 4;
			L = (U + 3) % 4;
		}
	}

	public static int calculScore(int i, int j) {
		int target = map[i][j];
		int score = target;
		int x = i;
		int y = j;
		Queue<int[]> path = new LinkedList<>();
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		path.add(new int[] { x, y });
		visited[x][y] = true;

		// 동서남북 방향으로 연속해서 이동할 수 있는 칸 구하기
		while (!q.isEmpty()) {
			int[] temp = q.poll();

			for (int k = 0; k < 4; k++) {
				x = temp[0] + DX[k];
				y = temp[1] + DY[k];
				if (x >= 0 && y >= 0 && x < N && y < M && !visited[x][y] && map[x][y] == target) {
					q.add(new int[] { x, y });
					path.add(new int[] { x, y });
					visited[x][y] = true;
				}
			}
		}

		score = path.size() * target;

		// visited 초기화
		while (!path.isEmpty()) {
			int[] temp = path.poll();
			visited[temp[0]][temp[1]] = false;
		}

		return score;
	}
}
