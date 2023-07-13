import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M;
	static char[][] GRAPH;
	static boolean[][] VISITED;
	static int[][] NODE;
	static HashMap<Character,int[]> MAP;
	static LinkedList<int[]> QUEUE;
	static int[] PARENT;
	static HashSet<Integer> RESULT;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();

		System.out.println(RESULT.size());
	}
	
	static void solution() {
		
		makeEdge();
		
		union_find();
		
		updateParent();
		
		makeResult();
		
//		System.out.println(Arrays.toString(PARENT));
		
	}
	
	static void updateParent() {
		for(int i=(N+1)*(M+1)-1;i>-1;i--) {
			PARENT[i] = find(PARENT[i]);
		}
	}
	
	static void union_find(){
		while(!QUEUE.isEmpty()) {
			int[] edge = QUEUE.poll();
			if(find(edge[0])!=find(edge[1])) {
				union(edge[0],edge[1]);
			}
			
		}
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
	
	static void makeEdge() {
		
		QUEUE = new LinkedList<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				int[] dir = MAP.get(GRAPH[i][j]);
				int dn = i+dir[0];
				int dm = j+dir[1];
				QUEUE.add(new int[] {NODE[i][j], NODE[dn][dm]});
				
			}
		}
	}
	
	static void makeResult() {
		
		RESULT = new HashSet<>();
		for(int i=0;i<(N+1)*(M+1);i++) {
			if(PARENT[i]!=0) RESULT.add(PARENT[i]);
		}
	}
	
	
	
	static boolean check(int n, int m) {
		return (0<=n&&n<N&&0<=m&&m<M);
	}
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		GRAPH = new char[N][M];
		VISITED = new boolean[N][M];
		NODE = new int[N][M];
		PARENT = new int[(N+1)*(M+1)];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				GRAPH[i][j] = input.charAt(j);
				NODE[i][j] = M*i +j+1;
				PARENT[NODE[i][j]] = NODE[i][j];
			}
		}
		
		MAP = new HashMap<>(); 
		MAP.put('U',new int[] {-1,0});
		MAP.put('D',new int[] {1,0});
		MAP.put('L',new int[] {0,-1});
		MAP.put('R',new int[] {0,1});
		
	}
}