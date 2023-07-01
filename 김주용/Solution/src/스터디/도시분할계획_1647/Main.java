package 스터디.도시분할계획_1647;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	int num;
	int cost;
	
	public Node(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
	
}

public class Main {

	static int N;
	static int M;
	static ArrayList<Node>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			line = in.readLine().split(" ");
			int A = Integer.parseInt(line[0]);
			int B = Integer.parseInt(line[1]);
			int C = Integer.parseInt(line[2]);
			
			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
		}
		
		System.out.println(solution());
	}
	
	// MST 후 최대 cost Edge 끊기 
	private static int solution() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		int sumCost = 0;
		int maxCost = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.num]) {
				continue;
			}
			visited[cur.num] = true;
			
			sumCost += cur.cost;
			maxCost = Math.max(maxCost, cur.cost);
			
			for(Node next : graph[cur.num]) {
				if(!visited[next.num]) {
					pq.add(next);
				}
			}
			
		}
		String s = "1";
		s.equals(s);
		return sumCost - maxCost;
	}

}
