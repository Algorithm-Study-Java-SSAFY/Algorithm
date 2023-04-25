import java.util.*;
import java.io.*;



public class Main {
	
	static int M,N;
	static int[][] GRAPH, COUNT;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	
	public static void main(String[] args) throws Exception{
		init();
		BFS();
		System.out.println(COUNT[N-1][M-1]);
	}
	
	static void BFS() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0,0,0});
		COUNT[0][0] = 0;
		while(!queue.isEmpty()) {
			int[] data = queue.poll();
			for(int i=0;i<4;i++) {
				int mx = data[0]+dx[i];
				int my = data[1]+dy[i];
				int cnt = data[2];
				if(0<=mx&&mx<N&&0<=my&&my<M) {
					if(GRAPH[mx][my]==0) {
						if(cnt<COUNT[mx][my]) {
							queue.add(new int[] {mx,my,cnt});
							COUNT[mx][my] = cnt;
						}
					}else {
						if(++cnt<COUNT[mx][my]) {
							queue.add(new int[] {mx,my,cnt});
							COUNT[mx][my] = cnt;
						}
					}
				}
			}
		}
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] MN = br.readLine().split(" ");
		M = Integer.parseInt(MN[0]);
		N = Integer.parseInt(MN[1]);
		GRAPH = new int[N][M];
		COUNT = new int[N][M];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				GRAPH[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
				COUNT[i][j] = Integer.MAX_VALUE;
			}
		}
	}
}