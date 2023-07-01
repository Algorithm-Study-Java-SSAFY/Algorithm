package 스터디.파티;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	int value;
	int time;

	public Node(int value, int time) {
		this.value = value;
		this.time = time;
	}

	@Override
	public int compareTo(Node o) {
		return this.time - o.time;
	}
}

class Main {
	static int N;
	static int M;
	static int X;
	
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static int[][] graph;
	static int[] times;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		X = Integer.parseInt(line[2]);
		graph = new int[N+1][N+1];
		times = new int[N+1];
		for(int i = 0; i < M; i++) {
			line = in.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int dest = Integer.parseInt(line[1]);
			int time = Integer.parseInt(line[2]);
			graph[start][dest] = time;
		}
		solution();
	}
	
	public static void solution() {
		int maxTime = 0;
		for(int i = 1; i <= N; i++) {
			int go = dijkstra(i, X);
			int come = dijkstra(X, i);
			maxTime = Math.max(maxTime, go + come);
		}
		System.out.println(maxTime);
	} 
	
	public static int dijkstra(int start, int dest) {
		pq.clear(); 
		for(int i = 0; i < N + 1; i++) {
			times[i] = Integer.MAX_VALUE;
		}
	
		pq.add(new Node(start, 0));
		times[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.value == dest) {
				return times[dest];
			}
			
			for(int i = 1; i <= N; i++) {
				int time = cur.time + graph[cur.value][i];
				if(graph[cur.value][i] > 0 && time < times[i]) {
					pq.add(new Node(i, time));
					times[i] = time;
				}
			}
		}
		//System.out.println(times[dest]);
		return times[dest];
	}
}
