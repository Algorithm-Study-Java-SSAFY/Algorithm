package 수업.분할정복백트래킹.최적경로;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

class Point {
	int y;
	int x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + "]";
	}

}

public class Solution {

	static int N;
	static Point[] points;
	static boolean[] visited;

	static Point start;
	static Point dest;

	static int answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(in.readLine());
			int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			start = new Point(line[1], line[0]);
			dest = new Point(line[3], line[2]);
			points = new Point[N + 2];
			visited = new boolean[N + 2];
			points[0] = start;
			points[N + 1] = dest;
			for (int i = 0; i < N * 2; i += 2) {
				points[i / 2 + 1] = new Point(line[i + 5], line[i + 4]);
			}
			// System.out.println(Arrays.toString(points));
			answer = Integer.MAX_VALUE;
			int[] ret = new int[N + 2];
			ret[0] = 0;
			ret[N + 1] = N + 1;
			dfs(ret, 1);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	public static void dfs(int[] ret, int depth) {
		if (depth == N + 1) {
			int sum = 0;
			// System.out.println(Arrays.toString(ret));
			for (int i = 1; i < N + 2; i++) {
				sum += getDist(ret[i - 1], ret[i]);
			}
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				ret[depth] = i;
				dfs(ret, depth + 1);
				visited[i] = false;
			}
		}
	}

	private static int getDist(int prev, int i) {
		return Math.abs(points[i].y - points[prev].y) + Math.abs(points[i].x - points[prev].x);
	}
}
