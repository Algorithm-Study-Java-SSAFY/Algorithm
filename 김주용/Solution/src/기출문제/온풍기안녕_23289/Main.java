package 기출문제.온풍기안녕_23289;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int y;
	int x;
	int heat;
	int direct;
	boolean up;
	boolean down;
	boolean left;
	boolean right;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
		this.heat = 0;
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
	}

	public Point(int y, int x, int direct) {
		this.y = y;
		this.x = x;
		this.direct = direct;
		this.heat = 0;
		if (this.direct == 0) {
			this.right = true;
		}
		else if (this.direct == 1) {
			this.left = true;
		}
		else if (this.direct == 2) {
			this.up = true;
		}
		else if (this.direct == 3) {
			this.down = true;
		}
	}

	public int[] getDy() {
		if (this.up) {
			return new int[] { -1, -1, -1 };
		}
		if (this.down) {
			return new int[] { 1, 1, 1 };
		}
		return new int[] { 1, 0, -1 };
	}

	public int[] getDx() {
		if (this.left) {
			return new int[] { -1, -1, -1 };
		}
		if (this.right) {
			return new int[] { 1, 1, 1 };
		}
		return new int[] { 1, 0, -1 };
	}

	@Override
	public String toString() {
		return "[" + heat + "]";
	}
	
}

public class Main {
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dx = { 1, -1, 0, 0 };

	static int R;
	static int C;
	static int K;

	static Point[][] board;
	static ArrayList<Point> checkPoint = new ArrayList<>();
	static ArrayList<Point> heaters = new ArrayList<>();

	static int W;
	
	static int[][] newBoard;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);

		board = new Point[R + 1][C + 1];
		newBoard = new int[R + 1][C + 1];
		
		for (int i = 1; i <= R; i++) {
			line = in.readLine().split(" ");
			for (int j = 1; j <= C; j++) {
				int cur = Integer.parseInt(line[j - 1]);
				if (0 < cur && cur < 5) {
					heaters.add(new Point(i, j, cur-1));
				}
				if (cur == 5) {
					checkPoint.add(new Point(i, j));
				}
				board[i][j] = new Point(i, j);
			}
		}

		W = Integer.parseInt(in.readLine());

		for (int i = 0; i < W; i++) {
			line = in.readLine().split(" ");
			int y = Integer.parseInt(line[0]), x = Integer.parseInt(line[1]), t = Integer.parseInt(line[2]);
			if (t == 0) {
				board[y][x].up = true;
				board[y - 1][x].down = true;
			} else {
				board[y][x].right = true;
				board[y][x + 1].left = true;
			}
		}
		solution();
	}

	public static void solution() {
		int answer = 0;
		
		while(true) {
			for (Point heater : heaters) {
				onHeater(heater);
				//debug("히터 작동");
			}
			
			adjustHeat();
			//debug("온도 적응");
			
			decreaseBorder();
			
			//debug("한 스테이지 끝");
			answer++;
			if(check()) {
				break;
			} 
			if(answer > 100) {
				break;
			}
		}
		System.out.println(answer);
		
	}

	private static void onHeater(Point heater) {
		int direct = heater.direct;
		int sy = heater.y + dy[direct], sx = heater.x + dx[direct];
		int[] curDy = heater.getDy();
		int[] curDx = heater.getDx();
		Queue<Point> queue = new LinkedList<>();
		queue.add(board[sy][sx]);
		int[][] visited = new int[R+1][C+1];
		visited[sy][sx] = 5;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i = 0; i < 3; i++) {
				int ny = cur.y + curDy[i], nx = cur.x + curDx[i];
				if(0 < ny && ny <= R && 0 < nx && nx <= C && visited[ny][nx] == 0 && canGo(cur, curDy[i], curDx[i], heater.direct)) {
					if(visited[cur.y][cur.x] - 1 > 0) {
						queue.add(board[ny][nx]);
						visited[ny][nx] = visited[cur.y][cur.x] - 1;
					}
				}
			}
		}
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				board[i][j].heat += visited[i][j];
			}
		}
	}
	
	public static boolean canGo(Point cur, int cdy, int cdx, int direct) {
		if(direct == 0) { return canGoRight(cur, cdy, cdx);}
		if(direct == 1) { return canGoLeft(cur, cdy, cdx);}
		if(direct == 2) { return canGoUp(cur, cdy, cdx);}
		
		return canGoDown(cur, cdy, cdx);
	}
	
	
	private static void adjustHeat() {
		copy();
		for(int i = R; i >= 0; i--) {
			for(int j = C; j >= 0; j--) {
				for(int d = 1; d < 3; d++) {
					int ny = i + dy[d], nx = j + dx[d];
					if(0 < ny && ny <= R && 0 < nx && nx <= C) {
						if(!canGo(board[i][j], dy[d], dx[d], d)) {
							continue;
						}
						int next = newBoard[ny][nx];
						int cur = newBoard[i][j];
						int temp = 0;
						if(cur > next) {
							temp = (cur - next) / 4;
							board[i][j].heat -= temp;
							board[ny][nx].heat += temp;
							if(board[i][j].heat < 0) {
								board[i][j].heat = 0;
							}
						} 
						if(cur < next) {
							temp = (next - cur) / 4;
							board[i][j].heat += temp;
							board[ny][nx].heat -= temp;
							if(board[ny][nx].heat < 0) {
								board[ny][nx].heat = 0;
							}
						}
					}
				}
			}
		}
	}
	
	public static void decreaseBorder() {
		for(int i = 1; i <= C; i++) {
			if(board[1][i].heat > 0) {
				board[1][i].heat--;
			}
			if(board[R][i].heat > 0) {
				board[R][i].heat--;
			}
		}
		for(int i = 2; i < R; i++) {
			if(board[i][1].heat > 0) {
				board[i][1].heat--;
			}
			if(board[i][C].heat > 0) {
				board[i][C].heat--;
			}
		}
	}
	
	public static boolean check() {
		for(Point p : checkPoint) {
			if(board[p.y][p.x].heat < K) {
				return false;
			}
		}
		return true;
	}

	// 오른 방향 기준
	public static boolean canGoRight(Point cur, int cdy, int cdx) {
		// 위, 아래
		if ((cur.up && cdy == -1) || (cdy == 1 && cur.down)) {
			return false;
		}
		// 대각선, 오른
		Point dest = board[cur.y + cdy][cur.x + cdx];
		if (dest.left) {
			return false;
		}
		return true;
	}

	public static boolean canGoLeft(Point cur, int cdy, int cdx) {
		// 위, 아래
		if ((cur.up && cdy == -1) || (cdy == 1 && cur.down)) {
			return false;
		}
		// 대각선, 오른
		Point dest = board[cur.y + cdy][cur.x + cdx];
		if (dest.right) {
			return false;
		}
		return true;
	}
	
	public static boolean canGoUp(Point cur, int cdy, int cdx) {
		if ((cur.left && cdx == -1) || (cur.right && cdx == 1)) {
			return false;
		}
		
		Point dest = board[cur.y + cdy][cur.x + cdx];
		if (dest.down) {
			return false;
		}
		return true;
	}
	
	public static boolean canGoDown(Point cur, int cdy, int cdx) {

		if ((cur.left && cdx == -1) || (cur.right && cdx == 1)) {
			return false;
		}

		Point dest = board[cur.y + cdy][cur.x + cdx];
		if (dest.up) {
			return false;
		}
		return true;
	}
	
	public static void copy() {
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				newBoard[i][j] = board[i][j].heat;
			}
		}
	}
	
	
	private static void debug(String msg) {
		System.out.println(msg);
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				System.out.print(board[i][j].toString() + " ");
			}
			System.out.println(" ");
		}
		System.out.println("------------------");
	}
	

}
