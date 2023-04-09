package 삼성.코드트리빵;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int y;
	int x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Object extends Point {
	int num;
	boolean go;

	public Object(int num, int y, int x) {
		super(y, x);
		this.num = num;
	}

	@Override
	public String toString() {
		return "Object [num=" + num + ", y=" + y + ", x=" + x + " " + go + "]";
	}

}

public class Main {

	static int N;
	static int M;

	static int[][] board;
	static ArrayList<Object> baseCamps;
	static Object[] people;
	static Object[] stores;
	static int[][] distances;

	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		board = new int[N][N];

		distances = new int[N][N];

		baseCamps = new ArrayList<>();
		int num = 0;
		for (int i = 0; i < N; i++) {
			line = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(line[j]);
				if (board[i][j] == 1) {
					baseCamps.add(new Object(num, i, j));
					num++;
				}
			}
		}

		people = new Object[M];
		stores = new Object[M];
		for (int i = 0; i < M; i++) {
			line = in.readLine().split(" ");
			int y = Integer.parseInt(line[0]) - 1;
			int x = Integer.parseInt(line[1]) - 1;
			stores[i] = new Object(i, y, x);
			people[i] = new Object(i, -1, -1);
			people[i].go = false;
		}

		solution();

	}

	public static void solution() {
		int t = 0;
		while (!isFinish()) {
			// 1
			movePeople();
			// 2
			for(Object cur : people) {
				Object dest = stores[cur.num];
				System.out.println(cur);
				if(cur.y == dest.y && cur.x == dest.x) {
					board[cur.y][cur.x] = 2;
					cur.go = false;
				}
			}
			System.out.println(" ");
			// 3
			if(t < M) {
				Object curPeople = people[t];
				Object curStore = stores[t];
				bfs(curStore);
				int minDist = Integer.MAX_VALUE;
				int minY = -1, minX = -1;
				for(int i = 0 ; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(board[i][j] == 1 && board[i][j] != 2 && minDist > distances[i][j] && distances[i][j] >= 0) {
							minDist = distances[i][j];
							minY = i;
							minX = j;
						}
					}
				}
				curPeople.y = minY;
				curPeople.x = minX;
				curPeople.go = true;
				board[minY][minX] = 2;
				System.out.println(curPeople);
			}
			System.out.println(" ");
			t++;
		}
		System.out.println(t);
	}


	public static void movePeople() {
		for (Object cur : people) {
			if (!cur.go) {
				continue;
			}
			init();
			bfs(stores[cur.num]);
			int minDist = Integer.MAX_VALUE;
			int minY = -1, minX = -1;
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i], nx = cur.x + dx[i];
				if (inRange(ny, nx) && distances[ny][nx] >= 0 && minDist > distances[ny][nx]) {
					minDist = distances[ny][nx];
					minY = ny;
					minX = nx;
				}
			}
			cur.y = minY;
			cur.x = minX;
		}
		init();
	}

	public static void bfs(Object dest) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(dest.y, dest.x));
		distances[dest.y][dest.x] = 0;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i], nx = cur.x + dx[i];
				if (inRange(ny, nx) && distances[ny][nx] == -1 && board[ny][nx] != 2) {
					distances[ny][nx] = distances[cur.y][cur.x] + 1;
					queue.add(new Point(ny, nx));
				}
			}
		}
	}
	
	public static boolean isFinish() {
		for(Object cur : people) {
			Object dest = stores[cur.num];
			if(!(cur.y == dest.y && cur.x == dest.x)) {
				return false;
			}
		}
		return true;
	}

	public static boolean inRange(int y, int x) {
		return -1 < y && y < N && -1 < x && x < N;
	}

	public static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				distances[i][j] = -1;
			}
		}
	}
	
	public static void debug() {
		for(Object cur : people) {
			System.out.println(cur);
		}
		System.out.println(" ");
	}
}
