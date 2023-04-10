import java.util.*;
import java.io.*;


public class Main {
	static BufferedReader br;

	static int R,C;
	static char[][] board;
	static int startY, startX; 
	static int[] DY = { 1, -1, 0, 0 };
	static int[] DX = { 0, 0, 1, -1 };
	static int spreadCnt = 1; 
	
	static List<int[]> fire = new ArrayList<int[]>();

	static int answer = Integer.MAX_VALUE; 
	
	public static void main(String[] args) throws IOException {
		init();
		move();  
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(answer);
		}

	}

	static void move() {
		Deque<int[]> dq = new ArrayDeque<int[]>();
		dq.add(new int[] {startY, startX, 1});
		board[startY][startX] = '+';
		
		if(startY == R-1 || startY == 0 || startX == C-1 || startX == 0) {
			answer = 1; 
		}else {
			while(!dq.isEmpty()) {
				int[] out = dq.removeFirst(); 
				
				
				if(out[2] == spreadCnt) {
					spreadCnt++; 
					fireSpread();
				}
				

				for(int i=0; i<4; i++) {
					int my = out[0]+DY[i];
					int mx = out[1]+DX[i]; 
					
					if((my == R-1 || my == 0 || mx == C-1 || mx == 0)  && board[my][mx] == '.') {
						if(out[2]+1 < answer) answer = out[2]+1;
					}
					else if(0<=my && my<R && 0<=mx && mx<C && board[my][mx] == '.' && out[2] < answer ) {
						board[my][mx] = '+';
						dq.add(new int[] {my,mx,out[2]+1});
					}
						
				}
				
			}
		}
		
	}
	
	static void fireSpread() {
		List<int[]> subfire = new ArrayList<>(); 

		for(int i=0; i<fire.size(); i++) {
			int fy = fire.get(i)[0];
			int fx = fire.get(i)[1];
			
			for(int j=0; j<4; j++) {
				int sy = fy+DY[j];
				int sx = fx+DX[j];
				if(0<=sy && sy<R && 0<=sx && sx<C && (board[sy][sx] == '.' || board[sy][sx] == '+')) {
					board[sy][sx] = 'F'; 
					subfire.add(new int[] {sy, sx});
				}
			}
		}
		
		fire = subfire;
	}

	static void init() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);

		board = new char[R][C];

		
		for(int i=0; i<R; i++) {
			String s2 = br.readLine();
			for(int j=0; j<C; j++) {
				board[i][j] = s2.charAt(j);
				if(board[i][j] == 'F') {
					fire.add(new int[] {i,j});
				}
				if(board[i][j] == 'J') {
					startY = i; 
					startX = j; 
				}
			}
		}
	}

}