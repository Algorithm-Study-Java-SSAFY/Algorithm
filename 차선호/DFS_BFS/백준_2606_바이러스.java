package code_test;

import java.io.*;
import java.util.*;

public class Main {
	
static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		
		
		for(int i=1;i<N+1;i++) {
			graph.put(i, new ArrayList<>());
		}
		
		for(int i=0;i<E;i++) {
			String[] sd = br.readLine().split(" ");
			int start = Integer.parseInt(sd[0]);
			int dest = Integer.parseInt(sd[1]);
			graph.get(dest).add(start);
			graph.get(start).add(dest);
			
		}
		
		System.out.println(bfs(1)-1);
		
	}
	
	static int bfs(int start) {
		Queue<Integer> needVisit = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		needVisit.add(start);
		visited.add(start);
		
		while(needVisit.size()>0) {
			int node = needVisit.poll();
			if(graph.get(node)!=null) {
				for(int dest : graph.get(node)) {
					if(!visited.contains(dest)) {
						needVisit.add(dest);
						visited.add(dest);
					}
				}
			}
		}
		
		return visited.size();
	}
	
}