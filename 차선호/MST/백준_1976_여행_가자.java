package code_test;

import java.util.*;
import java.io.*;


class Edge{
	int start;
	int dest;
	
	public Edge(int start, int dest) {
		this.start = start;
		this.dest = dest;
	}
}

public class Main {
	
	static int N, M;
	static Queue<Integer> route = new LinkedList<>();
	static String answer = "YES";
	static int[] parent;
	
	public static void main(String[] args) throws Exception{
		init();
		
		if(N>0) {
			int start = route.poll();
			while(!route.isEmpty()) {
				int dest = route.poll();
				if(find(start)!=find(dest)) { //다른 덩어리라면
					answer = "NO";
					break;
				}
				start = dest;
			}
		}
		
		System.out.println(answer);
	}
	
	static void union(int start, int dest) {
		int parent1 = find(start);
		int parent2 = find(dest);
		if(parent1 < parent2) parent[parent2] = parent1;
		else parent[parent1] = parent2;
	}
	
	static int find(int start) {
		if(parent[start]==start) return start;
		return parent[start] = find(parent[start]);
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for(int i=1;i<N+1;i++) {
			parent[i] = i; // 처음엔 자기 자신이 부모
		}
		
		for(int i=1;i<N+1;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=1;j<N+1;j++) {
				if(input[j-1].equals("1")) {
					if(find(i)!=find(j)) {
						union(j,i); 
					}
				}
			}
		}
		
		String[] inputRoute = br.readLine().split(" ");
		for(int i=0;i<inputRoute.length;i++) {
			route.add(Integer.parseInt(inputRoute[i]));
		}
	}
}