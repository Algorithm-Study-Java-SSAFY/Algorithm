import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M;
	static HashMap<Integer, List<Integer>> graph;
	static int[] degree;
	static PriorityQueue<Integer> pq;
	static StringBuffer sb;
	
	public static void main(String[] args) throws Exception{
		init();
		solution();
		System.out.println(sb);
	}
	
	static void solution() {
		boolean sbEmpty = true;
		while(!pq.isEmpty()) {
			int start = pq.poll();
			if(sbEmpty) {
				sb.append(start);
				sbEmpty = false;
			}else sb.append(" "+start);
			
			for(int dest:graph.get(start)) {
				if(--degree[dest]==0) pq.add(dest);
			}
		}
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		degree = new int[N+1];
		graph = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			graph.put(i, new ArrayList<>());
		}
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int dest = Integer.parseInt(input[1]);
			graph.get(start).add(dest);
			degree[dest]++;
		}
		pq = new PriorityQueue<>();
		for(int i=1;i<N+1;i++) {
			if(degree[i]==0) pq.add(i);
		}
		sb = new StringBuffer();
	}
}