package 스터디.미로탈출하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static Map<Character, Integer> map = new HashMap<Character, Integer>() {
		{
			put('U', 0);
			put('R', 1);
			put('D', 2);
			put('L', 3);
		}
	};

	static int N;
	static int M;
	static int[][] board;
	static boolean[][] visited;
	static boolean[][] success;
	static LinkedList<int[]> path = new LinkedList<int[]>();
	
	static int ret = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		board = new int[N][M];
		visited = new boolean[N][M];
		success = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = in.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = map.get(row.charAt(j));
			}
		}
		solution();
	}

	public static void solution() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j);
				path.clear();
			}
		}
		System.out.println(ret);
	}

	public static void dfs(int cy, int cx) {
		if (cy < 0 || cy >= N || cx < 0 || cx >= M || success[cy][cx]) {
			while (!path.isEmpty()) {
				int[] point = path.poll();
				if(!success[point[0]][point[1]]) {
					ret++;
				}
				success[point[0]][point[1]] = true;
			}
			return;
		}

		if (visited[cy][cx]) {
			return;
		}

		path.add(new int[] { cy, cx });
		visited[cy][cx] = true;

		int direct = board[cy][cx];
		int ny = cy + dy[direct], nx = cx + dx[direct];

		dfs(ny, nx);

	}

}
