package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class 트리의부모찾기 {
	public static int N;
	public static int[] parent;
	public static Map<Integer, List<Integer>> nodes;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		nodes = new HashMap<>();
		
		for(int i=0; i<N-1; i++) {
			String[] tmp = br.readLine().split(" ");
			int from = Integer.parseInt(tmp[0]);
			int to = Integer.parseInt(tmp[1]);
			
			if(!nodes.containsKey(from)) nodes.put(from, new ArrayList<>());
			nodes.get(from).add(to);
			if(!nodes.containsKey(to)) nodes.put(to, new ArrayList<>());
			nodes.get(to).add(from);
		}
		
		solve(1);
		
		for(int i=2; i<=N; i++) System.out.println(parent[i]);
	}
	public static void solve(int start) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			for(int i=0; i<nodes.get(node).size(); i++) {
				int nextNode = nodes.get(node).get(i);
				if(!visited[nextNode]) {
					parent[nextNode] = node;
					q.offer(nextNode);
					visited[nextNode] = true;
				}
			}
		}
	}
}
