package 기출문제.스타트택시;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

class People implements Comparable<People> {
	int idx;
	int curY;
	int curX;
	int destY;
	int destX;
	int dist;

	public People(int idx, int curY, int curX, int destY, int destX) {
		this.idx = idx;
		this.curY = curY;
		this.curX = curX;
		this.destY = destY;
		this.destX = destX;
	}
	
	public void setDist(int dist) {
		this.dist = dist;
	}

	@Override
	public int compareTo(People o) {
		if(this.dist < o.dist) {	
			return -1;
		} else if(this.dist == o.dist) {
			if(this.curY < o.curY) {
				return -1;
			} else if (this.curY == o.curY) {
				if(this.curX < o.curX) {
					return -1;
				}
			}
		}
		return 1;
	}

	@Override
	public String toString() {
		return "People [curY=" + curY + ", curX=" + curX + ", destY=" + destY + ", destX=" + destX + ", dist=" + dist
				+ "]";
	}
}

class Taxi {
	int curY;
	int curX;
	int fuel;

	public Taxi(int curY, int curX, int fuel) {
		super();
		this.curY = curY;
		this.curX = curX;
		this.fuel = fuel;
	}

	@Override
	public String toString() {
		return "Taxi [curY=" + curY + ", curX=" + curX + ", fuel=" + fuel + "]";
	}
}

public class Main {
	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int fuel;

	static int[][] board;
	
	static Taxi taxi;
	static People[] peopleList;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		fuel = Integer.parseInt(line[2]);
		board = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			line = in.readLine().split(" ");
			for(int j = 1; j < N+1; j++) {
				board[i][j] = Integer.parseInt(line[j-1]);
			}
		}
		
		line = in.readLine().split(" ");
		taxi = new Taxi(Integer.parseInt(line[0]), Integer.parseInt(line[1]), fuel);
		peopleList = new People[M+1];
		for(int i = 1; i < M+1; i++) {
			int[] info = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			peopleList[i] = new People(i, info[0], info[1], info[2], info[3]);
		}
		
		System.out.println(solution());
	}
	
	public static int solution() {
		int people = M;
		while(people > 0) {
			// 가장 가까운 승객 
			int[][] distBoard = findDistance();
			PriorityQueue<People> priorPeople = new PriorityQueue<People>();
			for(int i = 1; i < M+1; i++) {
				if(peopleList[i] == null) {
					continue;
				}
				People cur = peopleList[i];
				int dist = distBoard[cur.curY][cur.curX] - 1;
				cur.dist = dist;
				priorPeople.add(cur);
			}
			// 승객 위치로 이동
			People curPeople = priorPeople.peek();
			peopleList[curPeople.idx] = null;
			taxi.curY = curPeople.curY;
			taxi.curX = curPeople.curX;
			if(taxi.fuel - curPeople.dist <= 0 || curPeople.dist == -1) {
				return -1;
			}
			taxi.fuel = (taxi.fuel - curPeople.dist);
			// 승객 목적지로 이동  
			distBoard = findDistance();
			taxi.curY = curPeople.destY;
			taxi.curX = curPeople.destX; 
			int dist = distBoard[curPeople.destY][curPeople.destX] - 1;
			if(taxi.fuel - dist < 0 || dist == -1) {
				return -1;
			}
			taxi.fuel = taxi.fuel + dist;
			people--;
		}
		return taxi.fuel;
	}
	
	// 거리 계산 
	public static int[][] findDistance() {
		Queue<int []> queue = new LinkedList<int[]>();
		int[][] visited = new int[N+1][N+1];
		queue.add(new int[] {taxi.curY, taxi.curX});
		visited[taxi.curY][taxi.curX] = 1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if(0 < ny && ny < N+1 && 0 < nx && nx < N+1) {
					if(board[ny][nx] != 1 && visited[ny][nx] == 0) {
						visited[ny][nx] = visited[cur[0]][cur[1]] + 1;
						queue.add(new int[] {ny, nx});
					}
				}
			}
		}

		return visited;
		
	}
}
