package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 소문난칠공주 {
	public static int answer;
	public static int N = 5;
	public static char[][] map;
	public static boolean[] visited;
	public static int[][] tmp;
	public static List<Integer[]> arr;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = tmp.charAt(j);
		}

		arr = new ArrayList<>();
		visited = new boolean[N * N];
		answer = 0;

		putArr();
		get7People(0, 7);

		System.out.println(answer);
	}

	public static void get7People(int start, int r) {
		if (r == 0) {
			find7Princess();
			return;
		}

		for (int i = start; i < arr.size(); i++) {
			visited[i] = true;
			get7People(i + 1, r - 1);
			visited[i] = false;
		}

	}

	public static void find7Princess() {
		int cntS = 0;
		int x = 0, y = 0;
		tmp = new int[N][N];
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == true) {
				y = arr.get(i)[0];
				x = arr.get(i)[1];
				tmp[y][x] = 1;
				if (map[y][x] == 'S')
					cntS++;
			}
		}

		if (cntS >= 4) {
			if (checkTeam(y,x))
				answer++;
		}
	}

	public static boolean checkTeam(int y, int x) {
		boolean check = false;
		int cntPeople = 0;
		boolean[][] tmpV = new boolean[N][N];
		Queue<Integer[]> que = new LinkedList<>();
		que.offer(new Integer[] { x, y });
		tmpV[y][x] = true;

		while (!que.isEmpty()) {
			Integer[] xy = que.poll();
			x = xy[0];
			y = xy[1];
			cntPeople++;

			for (int i = 0; i < 4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];

				if (cx < 0 || cx >= N || cy < 0 || cy >= N)
					continue;
				if (tmp[cy][cx] == 1 && tmpV[cy][cx] == false) {
					que.offer(new Integer[] { cx, cy });
					tmpV[cy][cx] = true;
				}
			}
		}
		
		if (cntPeople == 7) 
			check = true;


		return check;
	}

	public static void putArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr.add(new Integer[] { i, j });
			}
		}
	}

}
