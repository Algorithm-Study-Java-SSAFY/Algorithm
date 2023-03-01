package 기출문제.온풍기안녕_23289;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class Heater {
	int y;
	int x;
	int direct;
	
	public Heater(int y, int x, int direct) {
		this.y = y;
		this.x = x;
		this.direct = direct;
	}
	
}



public class Main {
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	
	static int R;
	static int C;
	static int K;
	static int[][] board;
	static int[][] temper; 
	static ArrayList<int []> heaterList = new ArrayList<int []>();
	static ArrayList<int []> checkList = new ArrayList<int[]>();
	static boolean[][][] visited;
	static int W;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		board = new int[R+1][C+1];
		temper = new int[R+1][C+1];
		for(int i = 1; i <= R; i++) {
			line = in.readLine().split(" ");
			for(int j = 1; j <= C; j++) {
				board[i][j] = Integer.parseInt(line[j-1]);
				if(board[i][j] > 0 && board[i][j] < 5) {
					board[i][j]--;
					heaterList.add(new int[] {i, j});
				} else if(board[i][j] == 5) {
					checkList.add(new int[] {i, j});
				}
			}
		}
		W = Integer.parseInt(in.readLine());
		visited = new boolean[R+1][C+1][4];
		for(int i = 0; i < W; i++) {
			line = in.readLine().split(" ");
			int x = Integer.parseInt(line[0]), y = Integer.parseInt(line[1]), t = Integer.parseInt(line[2]);
			if(t == 0) {
				visited[x][y][1] = true;
			} else {
				visited[x][y][2] = true;
			}
			System.out.println(x + " " + y);
			System.out.println(Arrays.toString(visited[x][y]));
		}
		solution();
	}
	
	public static void solution() {
		for(int i = 1; i < heaterList.size(); i++) {
			int[] cur = heaterList.get(i);
			int direct = board[cur[0]][cur[1]];
			int[] directY = directionY(direct);
			int[] directX = directionX(direct);
			System.out.println(cur[0] + " " + cur[1]);
			onHeater(cur[0] + dy[direct], cur[1] + dx[direct], directY, directX, direct);
		}
	}

	// 온풍기 동작 
	public static void onHeater(int y, int x, int[] tdy, int[] tdx, int direct) {
		Queue<int []> queue = new LinkedList<int[]>();
		Queue<int []> newQueue = new LinkedList<int[]>();
		boolean[][] tVisited = new boolean[R+1][C+1];
		queue.add(new int[] {y, x});
		temper[y][x] += 5;
		
		
		for(int k = 4; k > 0; k--) {
			if(k < 4) { cloneQueue(queue, newQueue);}
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				loop:
				for(int i = 0; i < 3; i++) {
					int ny = cur[0] + tdy[i], nx = cur[1] + tdx[i];
					if(0 < ny && ny <= R && 0 < nx && nx <= C && !tVisited[ny][nx]) {
						boolean[] wallInfo = visited[ny][nx];
						for(int j = 0; j < 4; j++) {
	
							if(wallInfo[j] && j < 2 && tdx[i] != 0) {		
								System.out.println(1);
								continue loop;
							}
							if(wallInfo[j] && j >= 2 && tdy[i] != 0) {	
								System.out.println(2);
								System.out.println(ny + " " + nx);
								continue loop;
							}
						}
						tVisited[ny][nx] = true;
						temper[ny][nx] += k;
						newQueue.add(new int[] {ny, nx});
					}
				}
				
			}
			
		}
		for(int[] t: temper) {
			System.out.println(Arrays.toString(t));
		}
		System.out.println(" ");
	}
	
	public static void cloneQueue(Queue<int []> a, Queue<int []> b) {
		while(!b.isEmpty()) {
			a.add(b.poll());
		}
		b.clear();
	}
	
	// 온풍기 방향에 따른 진행 방향 
	public static int[] directionY(int direct) {
		if(direct == 2) {	
			return new int[] {-1, -1, -1};
		}
		if(direct == 3) {
			return new int[] {1, 1, 1}; 
		}
		
		return new int[] {1, 0, -1};
	}
	
	public static int[] directionX(int direct) {
		if(direct == 0) {	
			return new int[] {1, 1, 1};
		} 
		if(direct == 1) {
			return new int[] {-1, -1, -1};
		}
		
		return new int[] {1, 0, -1};
	}
	
	// 온도가 1 이상인 가장 바깥쪽 칸 온도 1 감소 
	
	// 초콜릿 하나 먹기 
	
	// 조사하는 모든 칸의 온도가 1 씩 감소 
}
