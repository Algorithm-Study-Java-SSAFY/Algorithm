import java.util.*;
import java.io.*;



class Edge implements Comparable<Edge>{
	int v1;
	int v2;
	int cost;
	Edge(int v1, int v2, int cost){
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return cost - o.cost;
	}
	
}



public class Main {
	
	static int N, TOTAL, REMAIN;
	static PriorityQueue<Edge> PQ;
	static HashMap<Integer,List<Integer>> GRAPH;
	static int[] PARENT;
	
	public static void main(String[] args) throws Exception{
		init();
		if(check()) { 
			MST();
			System.out.println(TOTAL-REMAIN);
		}else System.out.println(-1);
	}
	
	static boolean check() {
		Queue<Integer> needVisit = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		needVisit.add(1);
		visited[1] = true;
		int cnt = 1;
		while(!needVisit.isEmpty()) {
			int start = needVisit.poll();
			for(int dest:GRAPH.get(start)) {
				if(!visited[dest]) {
					needVisit.add(dest);
					visited[dest] = true;
					cnt++;
				}
			}
		}
		if(cnt==N) return true;
		else return false;
	}
	
	static void MST() {
		while(!PQ.isEmpty()) {
			Edge edge = PQ.poll();
			if(find(edge.v1)==find(edge.v2)) continue;
			union(edge.v1,edge.v2);
//			System.out.println(edge.v1+"--"+edge.v2+"--->"+edge.cost);
			REMAIN += edge.cost;
		}
	}
	
	static int toInt(char c) {
		if((int)c <=90) return (int)c-38; 
		else return (int)c - 96;
	}
	
	static int find(int v) {
		if(PARENT[v]==v) return v;
		return PARENT[v] = find(PARENT[v]);
	}
	
	static void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if(p1<p2) PARENT[p2] = p1;
		else PARENT[p1] = p2;
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PQ = new PriorityQueue<>();
		PARENT = new int[N+1];
		GRAPH = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			GRAPH.put(i, new ArrayList<>());
		}
		for(int i=1;i<N+1;i++) {
			String input = br.readLine();
			for(int j=1;j<N+1;j++) {
				int cost = (input.charAt(j-1)=='0')? 0 : toInt(input.charAt(j-1));
				if(cost == 0) continue;
				TOTAL += cost;
				PQ.add(new Edge(i,j,cost));
				GRAPH.get(i).add(j);
				GRAPH.get(j).add(i);
			}
			PARENT[i] = i;
		}
		REMAIN = 0;
	}
}