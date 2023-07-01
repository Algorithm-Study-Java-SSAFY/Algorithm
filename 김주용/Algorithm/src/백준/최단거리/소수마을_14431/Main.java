package 백준.최단거리.소수마을_14431;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Point implements Comparable<Point> {
	int idx;
	int y;
	int x;
	int d;

	public Point(int idx, int y, int x) {
		this.idx = idx;
		this.y = y;
		this.x = x;
		this.d = 0;
	}

	public int getDist(Point o) {
		int ret = (int) Math.sqrt(Math.pow(this.y - o.y, 2) + Math.pow(this.x - o.x, 2));
		return ret;
	}

	@Override
	public int compareTo(Point o) {
		return this.d - o.d;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + ", d=" + d + ", idx=" + idx + "]";
	}

}

public class Main {

	static Point start;
	static Point dest;

	static int N;
	static Point[] points;
	static int[] visited;

	static int[][] dist;

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(in.readLine());
		start = new Point(0, Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		dest = new Point(N + 1, Integer.parseInt(line[2]), Integer.parseInt(line[3]));

		points = new Point[N + 2];
		visited = new int[N + 2];
		points[0] = start;
		points[N + 1] = dest;
		visited[N + 1] = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			line = in.readLine().split(" ");
			points[i] = new Point(i, Integer.parseInt(line[0]), Integer.parseInt(line[1]));
			visited[i] = Integer.MAX_VALUE;
		}
		getDist();
		solution();
		answer = visited[N + 1];
		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		System.out.println(answer);
	}

	public static void solution() {	// 다익스트라 
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.add(points[0]);
		visited[0] = 0;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int next = 1; next < N + 2; next++) {
				if (dist[cur.idx][next] > 0) {
					if (visited[next] > visited[cur.idx] + dist[cur.idx][next]) {
						queue.add(points[next]);
						visited[next] = points[cur.idx].d + dist[cur.idx][next];
						points[next].d = visited[next];
					}
				}

			}
		}
	}

	public static void getDist() {
		// 마을 사이 거리
		dist = new int[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				int curDist = points[i].getDist(points[j]);
				if (isPrime(curDist)) {
					dist[i][j] = curDist;
					dist[j][i] = curDist;
				}
			}
		}
	}

	public static boolean isPrime(int curDist) {
		if (curDist == 0 || curDist == 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(curDist); i++) {
			if (curDist % i == 0) {
				return false;
			}
		}
		return true;
	}

}
