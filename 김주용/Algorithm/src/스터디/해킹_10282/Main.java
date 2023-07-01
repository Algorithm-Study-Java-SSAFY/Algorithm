package 스터디.해킹_10282;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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

public class Main {

	static int N;
	static int D;
	static int C;
	
	static ArrayList<Node>[] graph;
	static int[] times;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 0; t < T; t++) {
			String[] line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			D = Integer.parseInt(line[1]);
			C = Integer.parseInt(line[2]);
			
			graph = new ArrayList[N+1];
			times = new int[N+1];
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
				times[i] = Integer.MAX_VALUE;
			}
		
			for(int i = 0; i < D; i++) {
				line = in.readLine().split(" ");
				int a = Integer.parseInt(line[0]);
				int b = Integer.parseInt(line[1]);
				int s = Integer.parseInt(line[2]);
				graph[b].add(new Node(a, s));
			}
			solution();
		}
	}
	// 최단 경로로 마지막 노드를 방문했을때 시간 
	private static void solution() {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(C, 0));
		times[C] = 0;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
					
			for(Node next : graph[cur.value]) {
				if(times[cur.value] + next.time < times[next.value]) {
					times[next.value] = times[cur.value] + next.time;
					next.time = times[next.value];
					queue.add(next);
				}
			}
		}
		int cnt = 0, time = 0;
		for(int i = 1; i <= N; i++) {
			if(times[i] != Integer.MAX_VALUE) {
				cnt++;
				time = Math.max(time, times[i]);
			}
		}
		System.out.println(cnt + " " + time);
	}

}
