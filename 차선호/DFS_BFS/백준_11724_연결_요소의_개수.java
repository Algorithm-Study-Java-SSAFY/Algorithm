package code_test;

import java.io.*;
import java.util.*;

public class Main {
	
	static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
	static HashSet<Integer> totalVisited = new HashSet<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		for(int i=1;i<N+1;i++) {
			graph.put(i, new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			String[] sd = br.readLine().split(" ");
			int start = Integer.parseInt(sd[0]);
			int dest = Integer.parseInt(sd[1]);
			graph.get(dest).add(start);
			graph.get(start).add(dest);
			
		}
		
		
		int answer = 0;
		for(int key:graph.keySet()){
			if(!totalVisited.contains(key)) {
				bfs(key);
				answer++;
			}
		}
		
		System.out.println(answer);
		
	}
	
	static void bfs(int start) {
		Queue<Integer> needVisit = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		needVisit.add(start);
		visited.add(start);
		totalVisited.add(start);
		
		while(needVisit.size()>0) {
			int node = needVisit.poll();
			if(graph.get(node)!=null) {
				for(int dest : graph.get(node)) {
					if(!visited.contains(dest)) {
						needVisit.add(dest);
						visited.add(dest);
						totalVisited.add(dest);
					}
				}
			}
		}
		
	}
	
}