import java.util.*;
import java.io.*;

public class Main {

	/*
	 * 비어있는 곳 : .
	 * 물이 차있는 곳 : * (N)
	 * 돌 : X (N)
	 * 비버 굴 : D (하나)
	 * 고슴도치 위치 : S (하나)
	 * 매 분마다 고슴도치는 상하좌우로 한칸씩 이동 가능 
	 * 물도 매 분마다 비어있는 인접한 칸으로 확장(상하좌우)
	 * 물과 고슴도치는 돌을 통과할 수 없다. 
	 * 고슴도치는 물이 있는 곳은 이동 불가능 
	 * 물은 비버의 소굴(D)로 이동할 수 없다. 
	 * 주의!! 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 
	 */
	
	static int R, C; // 행, 열  -> 50 이하 
	static char[][] board; 
	static int[] DY = {1,-1,0,0};
	static int[] DX = {0,0,1,-1};
	static int[] start, end; 
	static int[][] visited;
	static int[][] visitedWater;
	static List<Integer> resList = new ArrayList<>();
	static Deque<int[]> dqW = new ArrayDeque<>();
	static int timestack = 0; 
	
	public static void main(String[] args) throws IOException { 
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		 
		board = new char[R][C];
		visited = new int[R][C];
		visitedWater = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String s2 = br.readLine();
			for(int j=0; j<C; j++) {
				board[i][j] = s2.charAt(j);
				if(board[i][j] == 'S') start = new int[] {i,j};	
			}
		}
		
		
		// 시간에 따른 물이 차는 곳을 표시 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(board[i][j] =='*') visitedWater[i][j] = 0; 
				else if(board[i][j] == 'D') visitedWater[i][j] = Integer.MAX_VALUE;
				else if(board[i][j] == 'X') visitedWater[i][j] = -10;
				else visitedWater[i][j] = -1; 
			}
		}
		int time = 1;
		while(spreadWater(time)) {
			time++; 
		}
		
		
		
		// 고슴도치가 이동할 수 있는 경우가 여러가지 -> deep을 넣어서 물이 다이면 cut
		moveBFS();
		
		
		// 결과 
		if(resList.size() == 0) {
			System.out.println("KAKTUS");
		}else {
			Collections.sort(resList);
			System.out.println(resList.get(0));
		}

		
	} 

	
	static void moveBFS() {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {start[0],start[1], 1}); // y, x, time(deep)
		visited[start[0]][start[1]] = 1;
		
		
		while(!dq.isEmpty()) {
			int[] out = dq.removeFirst();
			
			
			for(int i=0; i<4; i++) {
				int dy = out[0] + DY[i];
				int dx = out[1] + DX[i];
				if(0<=dy && dy<R && 0<=dx && dx<C) {
					if(board[dy][dx] == 'D') {
						resList.add(out[2]);
					}
					else if(visited[dy][dx] == 0 && ( visitedWater[dy][dx] == -1 ||visitedWater[dy][dx] > out[2])) {
						visited[dy][dx] = 1;
						dq.add(new int[] {dy,dx,out[2]+1});
					}

					
				}
			}
		}
		
		
	}

	
	
	static boolean spreadWater(int time) {
		boolean tf = false;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(visitedWater[i][j] == (time-1)) { 
					dqW.add(new int[] {i,j});
				}
			}
		}
		
		while(!dqW.isEmpty()) {
			int[] out = dqW.removeFirst();
			for(int i=0; i<4; i++) {
				int dy = out[0] + DY[i];
				int dx = out[1] + DX[i];
				if(0<=dy && dy<R && 0<=dx && dx<C && visitedWater[dy][dx] == -1) {
					if(board[dy][dx] != 'X' && board[dy][dx] != 'D' && board[dy][dx] != 'S') {
						tf = true;
						visitedWater[dy][dx] = time;
					}

				}
			}
		}
	
		return tf;
	}
	

	

}