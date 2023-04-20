import java.util.*;
import java.io.*;

public class Main {
	
	static int N,M,X;
	static HashMap<Integer,List<int[]>> GRAPH;
	static int MAX;
	
	public static void main(String[] args) throws Exception{
		init();
		for(int i=1;i<N+1;i++) {
			if(i!=X) dijkstra(i);
		}
		System.out.println(MAX);
	}
	
	static void dijkstra(int start) {
		int result = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			return o1[1]-o2[1];
		});
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		pq.add(new int[] {start,0});
		distance[start] = 0;
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			for(int[] data:GRAPH.get(node[0])) {
				int dest = data[0];
				int cost = data[1];
				if(node[1]+cost < distance[dest]) {
					pq.add(new int[] {dest,node[1]+cost});
					distance[dest] = node[1]+cost;
				}
			}
		}
		result += distance[X];
		pq = new PriorityQueue<>((o1,o2)-> {
			return o1[1]-o2[1];
		});
		distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		pq.add(new int[] {X,0});
		distance[X] = 0;
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			for(int[] data:GRAPH.get(node[0])) {
				int dest = data[0];
				int cost = data[1];
				if(node[1]+cost < distance[dest]) {
					pq.add(new int[] {dest,node[1]+cost});
					distance[dest] = node[1]+cost;
				}
			}
		}
		result += distance[start];
		MAX = Math.max(MAX, result);
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMX = br.readLine().split(" ");
		N = Integer.parseInt(NMX[0]);
		M = Integer.parseInt(NMX[1]);
		X = Integer.parseInt(NMX[2]);
		GRAPH = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			GRAPH.put(i, new ArrayList<>());
		}
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			GRAPH.get(Integer.parseInt(input[0])).add(new int[] {Integer.parseInt(input[1]),Integer.parseInt(input[2])});
		}
		MAX = 0;
	}
}