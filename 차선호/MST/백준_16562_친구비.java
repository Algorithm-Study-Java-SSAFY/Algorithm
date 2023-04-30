import java.util.*;
import java.io.*;



public class Main {
	
	static int N,M,K;
	static int[] COST;
	static int[] PARENT;
	static Queue<int[]> QUEUE;
	static int answer;
	
	public static void main(String[] args) throws Exception{
		init();
		setPARENT();
//		System.out.println(Arrays.toString(PARENT));
		if(getAnswer()!=-1) System.out.println(answer);
		else System.out.println("Oh no");
		
	}
	
	static int getAnswer() {
		boolean[] visited = new boolean[N+1];
		for(int i=1;i<N+1;i++) {
			if(!visited[PARENT[i]]) {
				visited[PARENT[i]] = true;
				answer += COST[PARENT[i]];
				if(answer>K) return -1;
			}
		}
		return answer;
	}
	
	static void setPARENT() {
		for(int i=1;i<N+1;i++) {
			PARENT[i] = getPARENT(i);
		}
	}
	
	static int getPARENT(int p) {
		if(PARENT[p]==p) return p;
		else return PARENT[p] = getPARENT(PARENT[p]);
	}
	
	static void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if(COST[p1]<=COST[p2]) PARENT[p2] = p1;
		else PARENT[p1] = p2;
	}
	
	static int find(int v) {
		if(PARENT[v]==v) return v;
		return PARENT[v] = find(PARENT[v]);
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMK = br.readLine().split(" ");
		N = Integer.parseInt(NMK[0]);
		M = Integer.parseInt(NMK[1]);
		K = Integer.parseInt(NMK[2]);
		COST = new int[N+1];
		PARENT = new int[N+1];
		String[] costInput = br.readLine().split(" ");
		for(int i=1;i<N+1;i++) {
			COST[i] = Integer.parseInt(costInput[i-1]);
			PARENT[i] = i;
		}
		QUEUE = new LinkedList<>();
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int v1 = Integer.parseInt(input[0]);
			int v2 = Integer.parseInt(input[1]);
			if(find(v1)!=find(v2)) union(v1,v2);
		}
		answer = 0;
	}
}