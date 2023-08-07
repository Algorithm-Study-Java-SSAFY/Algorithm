import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M;
	static int[][] BOARD, CNT;
	static HashMap<Integer, int[]> MAP;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
	}
	
	static void solution() {
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			return o1[2]-o2[2]; //주사위 던진 횟수 적은 순
		});
		pq.add(new int[] {1,1,0}); // h,w,주사위 던진 횟수
		CNT[1][1] = 0;

		while(!pq.isEmpty()) {
			int[] data = pq.poll();
//			System.out.println(Arrays.toString(data));
			int h = data[0];
			int w = data[1];
			int cnt = data[2];
			
			if(h==10 && w==10) {
				System.out.println(cnt);
				return;
			}
			
			for(int i=1;i<7;i++) {
				int value = 10*(h-1)+w+i;
				if(!MAP.keySet().contains(value)) continue;
				int[] node = MAP.get(value);
				int[] move = MAP.get(BOARD[node[0]][node[1]]);
				if(cnt+1 < CNT[move[0]][move[1]]) {
					pq.add(new int[] {move[0],move[1],cnt+1});
					CNT[move[0]][move[1]] = cnt+1;
				}
			}
		}
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		BOARD = new int[11][11];
		CNT = new int[11][11];
		MAP = new HashMap<>();
		for(int i=1;i<11;i++) {
			for(int j=1;j<11;j++) {
				BOARD[i][j] = 10*(i-1)+j;
				MAP.put(10*(i-1)+j, new int[] {i,j});
				CNT[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i=0;i<N;i++) {
			String[] xy = br.readLine().split(" ");
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);
			int[] node = MAP.get(x);
			BOARD[node[0]][node[1]] = y;
		}
		
		for(int i=0;i<M;i++) {
			String[] uv = br.readLine().split(" ");
			int u = Integer.parseInt(uv[0]);
			int v = Integer.parseInt(uv[1]);
			int[] node = MAP.get(u);
			BOARD[node[0]][node[1]] = v;
		}
	}
}