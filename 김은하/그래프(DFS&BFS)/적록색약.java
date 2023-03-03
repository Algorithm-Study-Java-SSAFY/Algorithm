import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] painting;
	static boolean[][] color_visited;
	static boolean[][] color_weekness_visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		painting = new int[N + 1][N + 1];
		color_visited = new boolean[N + 1][N + 1];
		color_weekness_visited = new boolean[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			String temp = br.readLine();
			for (int j = 1; j < N + 1; j++) {
				if (temp.charAt(j - 1) == 'R')
					painting[i][j] = 0;
				else if (temp.charAt(j - 1) == 'B')
					painting[i][j] = 1;
				else
					painting[i][j] = 2;
			}
		}

		int color_result = 0;
		int color_weekness_result = 0;

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (color_visited[i][j] == false) {
					colorBfs(i, j);
					color_result += 1;
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (color_weekness_visited[i][j] == false) {
					colorWeeknessBfs(i, j);
					color_weekness_result += 1;
				}
			}
		}

		bw.write(color_result + " " + color_weekness_result);
		br.close();
		bw.close();
	}

	public static void colorBfs(int i, int j) {
		Queue<List<Integer>> que = new LinkedList<>();
		int target = painting[i][j];
		color_visited[i][j] = true;
		que.offer(new ArrayList<>(Arrays.asList(i, j)));

		while (!que.isEmpty()) {
			List<Integer> temp = que.poll();
			int x = temp.get(0);
			int y = temp.get(1);

			if (x != 1 && color_visited[x - 1][y] == false && painting[x - 1][y] == target) {
				que.offer(new ArrayList<>(Arrays.asList(x - 1, y)));
				color_visited[x - 1][y] = true;
			}
			if (x != N && color_visited[x + 1][y] == false && painting[x + 1][y] == target) {
				que.offer(new ArrayList<>(Arrays.asList(x + 1, y)));
				color_visited[x + 1][y] = true;
			}
			if (y != 1 && color_visited[x][y - 1] == false && painting[x][y - 1] == target) {
				que.offer(new ArrayList<>(Arrays.asList(x, y - 1)));
				color_visited[x][y - 1] = true;
			}
			if (y != N && color_visited[x][y + 1] == false && painting[x][y + 1] == target) {
				que.offer(new ArrayList<>(Arrays.asList(x, y + 1)));
				color_visited[x][y + 1] = true;
			}
		}
	}

	public static void colorWeeknessBfs(int i, int j) {
		Queue<List<Integer>> que = new LinkedList<>();
		int target = painting[i][j];
		color_weekness_visited[i][j] = true;
		que.offer(new ArrayList<>(Arrays.asList(i, j)));

		while (!que.isEmpty()) {
			List<Integer> temp = que.poll();
			int x = temp.get(0);
			int y = temp.get(1);

			if (x != 1 && color_weekness_visited[x - 1][y] == false
					&& (painting[x - 1][y] == target || Math.abs(painting[x - 1][y] - target) == 2)) {
				que.offer(new ArrayList<>(Arrays.asList(x - 1, y)));
				color_weekness_visited[x - 1][y] = true;
			}
			if (x != N && color_weekness_visited[x + 1][y] == false
					&& (painting[x + 1][y] == target || Math.abs(painting[x + 1][y] - target) == 2)) {
				que.offer(new ArrayList<>(Arrays.asList(x + 1, y)));
				color_weekness_visited[x + 1][y] = true;
			}
			if (y != 1 && color_weekness_visited[x][y - 1] == false
					&& (painting[x][y - 1] == target || Math.abs(painting[x][y - 1] - target) == 2)) {
				que.offer(new ArrayList<>(Arrays.asList(x, y - 1)));
				color_weekness_visited[x][y - 1] = true;
			}
			if (y != N && color_weekness_visited[x][y + 1] == false
					&& (painting[x][y + 1] == target || Math.abs(painting[x][y + 1] - target) == 2)) {
				que.offer(new ArrayList<>(Arrays.asList(x, y + 1)));
				color_weekness_visited[x][y + 1] = true;
			}
		}
	}
}
