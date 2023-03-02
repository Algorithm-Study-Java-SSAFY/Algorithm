import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] maze;
	static boolean visited[][];
	static int already[][];
	static int exit_cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				maze[i][j] = str.charAt(j - 1);
			}
		}

		already = new int[N + 2][M + 2];
		visited = new boolean[N + 2][M + 2];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (already[i][j] == 1) {
					exit_cnt += 1;
				} else if (already[i][j] == 2) {
					continue;
				} else {
					if (checkExit(i, j))
						exit_cnt += 1;
				}
			}
		}

		bw.write(String.valueOf(exit_cnt));

		br.close();
		bw.close();
	}

	public static boolean checkExit(int i, int j) {
		boolean exit = false;
		Queue<List<Integer>> q = new LinkedList<List<Integer>>();
		Queue<List<Integer>> path = new LinkedList<List<Integer>>();
		q.offer(Arrays.asList(i, j));
		path.offer(Arrays.asList(i, j));
		visited[i][j] = true;
		int x = i;
		int y = j;

		while (!q.isEmpty()) {
			List<Integer> position = q.poll();
			x = position.get(0);
			y = position.get(1);
			visited[x][y] = true;
			if (x == 0 || x == N + 1 || y == 0 || y == M + 1) {
				exit = true;
				break;
			}
			if (already[x][y] == 1) {
				exit = true;
				break;
			}
			if (already[x][y] == 2)
				break;

			if (maze[x][y] == 'U' && !visited[x - 1][y]) {
				q.offer(Arrays.asList(x - 1, y));
				path.offer(Arrays.asList(x - 1, y));
			} else if (maze[x][y] == 'R' && !visited[x][y + 1]) {
				q.offer(Arrays.asList(x, y + 1));
				path.offer(Arrays.asList(x, y + 1));
			} else if (maze[x][y] == 'D' && !visited[x + 1][y]) {
				q.offer(Arrays.asList(x + 1, y));
				path.offer(Arrays.asList(x + 1, y));
			} else if (maze[x][y] == 'L' && !visited[x][y - 1]) {
				q.offer(Arrays.asList(x, y - 1));
				path.offer(Arrays.asList(x, y - 1));
			}
		}

		if (exit) {
			while (!path.isEmpty()) {
				List<Integer> p = path.poll();
				already[p.get(0)][p.get(1)] = 1;
				visited[p.get(0)][p.get(1)] = false;
			}
		} else {
			while (!path.isEmpty()) {
				List<Integer> p = path.poll();
				already[p.get(0)][p.get(1)] = 2;
				visited[p.get(0)][p.get(1)] = false;
			}
		}

		return exit;
	}
}
