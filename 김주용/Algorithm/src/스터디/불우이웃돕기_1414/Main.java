package 스터디.불우이웃돕기_1414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	int value;
	int length;
	
	public Node(int value, int length) {
		super();
		this.value = value;
		this.length = length;
	}

	@Override
	public int compareTo(Node o) {
		return this.length - o.length;
	}
	
}

public class Main {
	
	static int N;
	static int K;
	static int[][] graph;
	static boolean[] visited;
	static int[] lengths;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		graph = new int[N][N];
		visited = new boolean[N];
		lengths = new int[N];
		for(int i = 0; i < N; i++) {
			lengths[i] = Integer.MAX_VALUE;
			char[] line = in.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				int length = -1;
				if(line[j] == '0') {
					length = 0;
				} else if(Character.isUpperCase(line[j])) {
					length = line[j] - 'A' + 27;
					
				} else {
					length = line[j] - 'a' + 1;
				}
				graph[i][j] = length;
				K += length;
			}
		}
		System.out.println(solution());
		
	}
	
	// 최소 랜선 길이로 모든 컴퓨터 연결 -> 최소 신장 트리(MST) by 크루스칼 or 프림 
	public static int solution() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));
		
		int ret = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.value]) {
				continue;
			}
			visited[cur.value] = true;
			
			for(int i = 0; i < N; i++) {
				int newLength = cur.length + graph[cur.value][i];
				if(graph[cur.value][i] > 0 && !visited[i]) {
					lengths[i] = Math.min(lengths[i], graph[cur.value][i]);
					pq.add(new Node(i, newLength));
					
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i] == Integer.MAX_VALUE) {
				return -1;
			}
		}
		return K - ret;
	}

}
