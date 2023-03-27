import java.util.*;
import java.io.*;


public class Main {

	static BufferedReader br;
	static int N;
	static int[][] graph;
	static int[][] costs;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while(true) {
			init();
			search();
			System.out.println("Problem "+(tc++)+": "+costs[N-1][N-1]);
		}
	}
	
	static void search() {
		Queue<int[]> needVisit = new LinkedList<>();
		needVisit.add(new int[] {0,0,costs[0][0]});
		while(!needVisit.isEmpty()) {
			int[] node = needVisit.poll();
			int x = node[0];
			int y = node[1];
			int cost = node[2];
			for(int i=0;i<4;i++) {
				int mx = x+dx[i];
				int my =y+dy[i];
				if(0<=mx&&mx<N&&0<=my&&my<N && cost+graph[mx][my]<costs[mx][my]) {
					costs[mx][my] = cost+graph[mx][my];
					needVisit.add(new int[] {mx,my,cost+graph[mx][my]});
				}
			}
		}
	}
	
	
	
	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		if(N==0) System.exit(0);
		graph = new int[N][N];
		costs = new int[N][N];
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(input[j]);
				costs[i][j] = 1_000_000;
			}
		}
		costs[0][0] = graph[0][0];
	}
}