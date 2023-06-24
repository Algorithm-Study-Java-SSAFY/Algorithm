import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M;
	static HashMap<Integer, List<Integer>> GRAPH;
	static boolean[] VISITED;
	static int ANSWER;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(ANSWER);
		
	}
	
	
	static void solution() {
		
		for(int i=0;i<N;i++) {
			Arrays.fill(VISITED, false);
			VISITED[i] = true;
			dfs(i,1);
			if(ANSWER==1) return;
		}
		
	}
	
	static void dfs(int current, int len) {
		if(len==5) {
			ANSWER = 1;
			return;
		}
		
		for(int dest:GRAPH.get(current)) {
			if(!VISITED[dest]) {
				VISITED[dest] = true;
				dfs(dest,len+1);
				VISITED[dest] = false;
			}
		}
	}
	
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		GRAPH = new HashMap<>();
		for(int i=0;i<N;i++) GRAPH.put(i, new ArrayList<>());
		
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			GRAPH.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
			GRAPH.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
		}
		
		VISITED = new boolean[N];
		
		ANSWER = 0;
	}
}