import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M;
	static int[][] GRAPH;
	static PriorityQueue<int[]> PQ;
	static Queue<int[]> POOLS;
	static Queue<int[]> NEW_POOLS;
	static int[] DX = {0,0,1,-1};
	static int[] DY = {1,-1,0,0};
	static Queue<int[]> QUEUE;
	static Stack<int[]> STACK;
	static boolean[][] VISITED;
	static int ANSWER;
	
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(ANSWER);
	
	}
	
	static void solution() {
		while(!PQ.isEmpty()) {
			int[] data = PQ.poll();
			isPool(new int[] {data[0],data[1]});
		}
	}
	
	static void isPool(int[] node) {
		if(node[0]==0 || node[0]==N-1 || node[1]==0 || node[1]==M-1) return;
		
		int minHeight = 10;
		
		QUEUE.clear();
		QUEUE.add(node);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				VISITED[i][j] = false;
			}
		}
		VISITED[node[0]][node[1]] = true;
		
		while(!QUEUE.isEmpty()) {
			int[] data = QUEUE.poll();
			for(int i=0;i<4;i++) {
				int mx = data[0]+DX[i];
				int my = data[1]+DY[i];
				if(0<=mx&&mx<N&&0<=my&&my<M && !VISITED[mx][my]){
					if(GRAPH[node[0]][node[1]]>=GRAPH[mx][my]) {
						if(mx==0 || mx==N-1 || my==0 || my==M-1) return; //테두리까지 샐 수 있는 놈
						QUEUE.add(new int[] {mx,my});
						VISITED[mx][my] = true;
					}else {
						minHeight = Math.min(minHeight, GRAPH[mx][my]);
					}
				}
			}
		}
		ANSWER += minHeight - GRAPH[node[0]][node[1]];
		GRAPH[node[0]][node[1]] = minHeight;
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		PQ = new PriorityQueue<>((o1,o2)-> {
			return o2[2]-o1[2];
		});
		GRAPH = new int[N][M];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				GRAPH[i][j] = input.charAt(j)-'0';
				PQ.add(new int[] {i,j,GRAPH[i][j]});
			}
		}
		POOLS = new LinkedList<>();
		NEW_POOLS = new LinkedList<>();
		
		QUEUE = new LinkedList<>();
		STACK = new Stack<>();
		VISITED = new boolean[N][M];
		
		ANSWER = 0;
	}
}