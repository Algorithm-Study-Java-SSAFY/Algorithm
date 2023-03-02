import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static char[][] girls = new char[5][5];
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < 5; i++) {
			girls[i] = br.readLine().toCharArray();
		}

		int[] arr = new int[25];
		boolean[] visited = new boolean[25];
		for (int i = 0; i < 25; i++)
			arr[i] = i;

		combination(arr, visited, 0, 25, 7);
		bw.write(String.valueOf(answer));
		br.close();
		bw.close();
	}

	public static void combination(int[] arr, boolean[] visited, int depth, int n, int r) {
		if (r == 0) {
			checkPrincess(visited);
			return;
		}
		if (depth == n)
			return;

		visited[depth] = true;
		combination(arr, visited, depth + 1, n, r - 1);

		visited[depth] = false;
		combination(arr, visited, depth + 1, n, r);
	}

	public static void checkPrincess(boolean[] visited) {
		int idx = 0, total = 0, S = 0, Y = 0;
		boolean[][] test_case = new boolean[5][5];
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				test_case[i / 5][i % 5] = true;
				idx = i;
			}
		}

		boolean[][] checked = new boolean[5][5];
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { idx / 5, idx % 5 });

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			checked[x][y] = true;
			total += 1;
			if (girls[x][y] == 'S')
				S += 1;
			else
				Y += 1;

			if (x != 0 && test_case[x - 1][y] && !checked[x - 1][y]) {
				q.add(new int[] { x - 1, y });
				checked[x - 1][y] = true;
			}
			if (x != 4 && test_case[x + 1][y] && !checked[x + 1][y]) {
				q.add(new int[] { x + 1, y });
				checked[x + 1][y] = true;
			}
			if (y != 0 && test_case[x][y - 1] && !checked[x][y - 1]) {
				q.add(new int[] { x, y - 1 });
				checked[x][y - 1] = true;
			}
			if (y != 4 && test_case[x][y + 1] && !checked[x][y + 1]) {
				q.add(new int[] { x, y + 1 });
				checked[x][y + 1] = true;
			}
		}

		if (total == 7 && S >= 4) {
			answer += 1;
		}
	}
}
