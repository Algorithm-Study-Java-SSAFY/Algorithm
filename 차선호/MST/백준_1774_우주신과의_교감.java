import java.util.*;
import java.io.*;


class Edge{
	int v1;
	int v2;
	double distance;
	public Edge(int v1, int v2, double distance) {
		this.v1 = v1;
		this.v2 = v2;
		this.distance = distance;
	}
}


public class Main {
	
	static int N,M;
	static HashMap<Integer, int[]> GODS;
	static PriorityQueue<Edge> EDGES;
	static boolean[][] VISITED;
	static int[] PARENT;
	static double ANSWER;
	
	public static void main(String[] args) throws Exception {
		
		init();
	
		solution();
		
		
		System.out.println(String.format("%.2f", ANSWER));
	}
	
	static void solution() {
		makeEdges();
		
		mst();
	}
	
	static void mst() {
		while(!EDGES.isEmpty()) {
			Edge edge = EDGES.poll();
			if(find(edge.v1)!=find(edge.v2)) {
				union(edge.v1, edge.v2);
				ANSWER += edge.distance;
			}
		}
	}
	
	static void makeEdges() {
		
		EDGES = new PriorityQueue<>((o1,o2)-> {
			double diff = o1.distance-o2.distance;
    		if(diff<0) return -1;
    		else return 1;
		});
		
		for(int i=1;i<N+1;i++) {
			int[] v1 = GODS.get(i);
			for(int j=i+1;j<N+1;j++) {
				int[] v2 = GODS.get(j);
				double distance = 0.0;
				if(!VISITED[i][j]) distance = getDistance(v1,v2);
				EDGES.add(new Edge(i,j,distance));
			}
		}
	}
	
	static double getDistance(int[] v1, int[] v2) {
		return Math.sqrt(Math.pow(v1[0]-v2[0], 2)+Math.pow(v1[1]-v2[1], 2));
	}
	
	static void union(int v1, int v2) {
    	int p1 = find(v1);
		int p2 = find(v2);
		if(p1<p2) PARENT[p2] = p1;
		else PARENT[p1] = p2;
    }
    
    static int find(int v) {
    	if(PARENT[v]==v) return v;
		return PARENT[v] = find(PARENT[v]);
    }
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
	
		GODS = new HashMap<>();
		PARENT = new int[N+1];
		for(int i=1;i<N+1;i++) {
			String[] xy = br.readLine().split(" ");
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);
			GODS.put(i, new int[] {x,y});
			PARENT[i] = i;
		}
		
		VISITED = new boolean[N+1][N+1];
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int v1 = Integer.parseInt(input[0]);
			int v2 = Integer.parseInt(input[1]);
			VISITED[v1][v2] = true;
			VISITED[v2][v1] = true;
//			if(v1<v2) PARENT[v2] = v1; //이렇게 하다 봉변 당함,,,,,,,,,,,,,
//			else PARENT[v1] = v2;
		}
		
		ANSWER = 0.0;
	}
}