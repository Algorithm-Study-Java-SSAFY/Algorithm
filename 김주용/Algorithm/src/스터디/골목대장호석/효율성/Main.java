package 스터디.골목대장호석.효율성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.naming.InitialContext;

class Point implements Comparable<Point2> {
	int value;
	long cost;

	public Point(int value, long cost) {
		this.value = value;
		this.cost = cost;
	}

	@Override
	public int compareTo(Point2 o) {
		return (int) (this.cost - o.cost);
	}

}

public class Main {
	static int N;
	static int M;
	static int A;
	static int B;
	static int C;

	static ArrayList<Point2>[] graph;
	static long[] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		A = Integer.parseInt(line[2]);
		B = Integer.parseInt(line[3]);
		C = Integer.parseInt(line[4]);

		graph = new ArrayList[N + 1];
		distance = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			line = in.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			int c = Integer.parseInt(line[2]);
			graph[a].add(new Point2(b, c));
			graph[b].add(new Point2(a, c));
		}
		System.out.println(solution());
	}

	public static int solution() {
		for (int i = 1; i <= 20; i++) {
			if (dijkstra(i)) {
				return i;
			}
		}
		return -1;
	}

	public static boolean dijkstra(int shame) {
		for(int i = 0; i < N+1; i++) {
			distance[i] = Long.MAX_VALUE;
		}
		PriorityQueue<Point2> queue = new PriorityQueue<>();
		queue.add(new Point2(A, 0));
		distance[A] = 0;

		while (!queue.isEmpty()) {
			Point2 cur = queue.poll();
			
			for (Point2 next : graph[cur.value]) {
				long dist = distance[cur.value] + next.cost;
				if (dist <= C && dist < distance[next.value] && next.cost <= shame) {
					if(next.value == B) {
						return true;
					}
					distance[next.value] = distance[cur.value] + next.cost;
					queue.add(new Point2(next.value, distance[next.value]));
				}
			}
		}
		return false;
	}
}
