import java.io.*;
import java.util.*;

public class Main {
	static int T = 1; 
	
	static BufferedReader br; 
	static int N;
	static int[][] board;
	static int[][] visited;
	static PriorityQueue<int[]> pq; 
	
	static int[] MY = {1,-1,0,0};
	static int[] MX = {0,0,1,-1};
	
	static List<Integer> resList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N != 0) {
				init();
				BFS();
				
				Collections.sort(resList);
				System.out.println("Problem "+T+": "+resList.get(0));
				T++; 
			}
			else {
				break; 
			}
		}
		
	}
	
	static void BFS() {
		pq.add(new int[] {0,0,board[0][0]}); // y,x, 누적(잃은 루피)
		visited[0][0] = board[0][0]; 
		
		while(!pq.isEmpty()) {
			int[] out = pq.poll();

			for(int i=0; i<4; i++) {
				int dy = out[0]+MY[i];
				int dx = out[1]+MX[i];
				
				if(0<=dy && dy<N && 0<=dx && dx<N && visited[dy][dx] > out[2]+board[dy][dx]) {
					
					if(dy == N-1 && dx == N-1) {
						resList.add(out[2]+board[N-1][N-1]);
					}else {
						visited[dy][dx] = out[2]+board[dy][dx]; 
						pq.add(new int[] {dy,dx,out[2]+board[dy][dx]});
					}
				
				}
				
			}
			
		}
	}
	
	static void init() throws NumberFormatException, IOException {
		board = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(s[j]);			
			}
		}
		
		visited = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		pq = new PriorityQueue<>((o1,o2)-> {
			return board[o1[0]][o1[1]]-board[o2[0]][o2[1]];
		});
	
		resList = new ArrayList<>(); 
	}

}