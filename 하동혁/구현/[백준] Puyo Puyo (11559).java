import java.io.*;
import java.util.*;

/* 
 * 뿌요는 다른 뿌요를 만나기 전까지 계속 아래로 
 * 같은 색 뿌요가 4개이상 상화좌우 연결시 한번에 사라짐 (1연쇄 시작)
 * 동시에 여러 그룹이 터져야 하는 상황이면 한번에 터져야 하고 그것도 1연쇄로 한다. 
 * 
 * 연쇄가 몇번 연속으로 발생할지?
 * R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑
 */
public class Main {

	static int H = 12; // 높이
	static int W = 6; // 넓이 
	static char[][] board = new char[H][W];
	static int[][] popBoard; // 삭제할 것들을 모아두는 보드 
	static int[][] visited;
	static char[] color = {'R','G','B','P','Y'};
	
	static int[] DY = {1,-1,0,0};
	static int[] DX = {0,0,1,-1};
	static int cnt;
	static int res=0; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i=0; i<H; i++) {
			String s = br.readLine();
			for(int j=0; j<W; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		
		do {
			start(); // 호출시 1연쇄가 시작 
			removePuyo(); // 1연쇄시 삭제되어야할 puyo를 모두 삭제 
			downPuyo(); // 삭제가 끝난 후 나머지 puyo들을 아래로 내림
			if(cnt>0) res++; 
		}while(cnt!=0);

		System.out.println(res);
		

	}
	
	static void start() { 
		// BFS로 R,G,B,P,Y를 다 탐색  ( 1연쇄 )
		popBoard = new int[H][W];
		cnt = 0;
		for (int c=0; c<5; c++) { // 5가지 색 
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					if(color[c] == board[i][j]) {
						visited = new int[H][W];
						BFS(i,j,color[c]); // y, x, 색
					}
				}
			}
		}
		
		
	}
	
	static void BFS(int y, int x, char col) {
		Deque<int[]> dq = new ArrayDeque<int[]>();
		List<int[]> popYX = new ArrayList<int[]>(); // 일단 지나온 좌표를 담아두고 4개 이상이면 삭제 하는데 사용
		dq.add(new int[] {y,x});
		visited[y][x] = 1; 
		popYX.add(new int[] {y,x});
		
		while(!dq.isEmpty()) {
			int[] out = dq.removeFirst();
			for(int i=0; i<4; i++) {
				int dy = out[0]+DY[i];
				int dx = out[1]+DX[i];
				if(0<=dy && dy<H && 0<=dx && dx<W && visited[dy][dx]==0) {
					if(col == board[dy][dx]) { // 같은 색이라면 dq에 담기 
						dq.add(new int[] {dy,dx});
						visited[dy][dx] = 1;
						popYX.add(new int[] {dy,dx});
					}
				}
			}
		}
		
		// 만약 4개 이상이라면 -> 삭제 하기 위해 popBoard에 해당 좌표를 표시한다. 
		if(popYX.size()>=4) {
			cnt++; 
			for(int i=0; i<popYX.size(); i++) {
				int cy = popYX.get(i)[0];
				int cx = popYX.get(i)[1];
				popBoard[cy][cx] = 1;  
			}
		}
		
	}
	
	// 삭제되여야할 연쇄 그룹을 모두 빈 공간을 만듬  
	static void removePuyo() {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if(popBoard[i][j] == 1) {
					board[i][j] = '.';
				}
			}
		}
	}
	
	// 빈 공간을 모두 아래로 내림
	static void downPuyo() {
		// 아래에서 부터 puyo를 하나씩 확인하며 내려줌 
		for (int i=H-2; i>=0; i--) {
			for (int j=0; j<W; j++) {
				
				if(board[i][j] != '.') {
					down(i,j);
				}
				
			}
		}
	}
	
	static void down(int y, int x) {
		int dy = y; 
		for(int i=y+1; i<H; i++) {
			if(board[i][x]=='.') {
				board[i][x]=board[dy][x];
				board[dy][x]='.';
				dy = i;
			}
		}
	}
	
	

}
