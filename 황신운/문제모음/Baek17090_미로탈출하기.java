package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Baek17090_미로탈출하기 {
	public static char[][] map;
	public static boolean[][] visited;
	public static ArrayList<Integer[]> currentVisited;
	public static boolean[][] successVisited;
	public static boolean[][] failVisited;
	public static HashMap<Character, Integer[]> dir;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		int N = Integer.parseInt(tmp[0]);
		int M = Integer.parseInt(tmp[1]);
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		setDir();
		int answer = 0;

		visited = new boolean[N][M];
		successVisited = new boolean[N][M];
		failVisited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j] == false)
					answer += checkWay(N, M, j,i);
				else if(successVisited[i][j])
					answer++;
			}
		}
		System.out.println(answer);
		
	}
	
	public static int checkWay(int N, int M, int x, int y) {
		currentVisited = new ArrayList<>();
		Queue<Integer[]> que = new LinkedList<>();
		que.offer(new Integer[] {x,y});
		visited[y][x] = true;
		
		while(!que.isEmpty()) {
			Integer[] tmpArr = que.poll();
			x = tmpArr[0];
			y = tmpArr[1];
			currentVisited.add(new Integer[] {x,y});
			
			char direction = map[y][x];
			int cx = x + dir.get(direction)[0];
			int cy = y + dir.get(direction)[1];
			if(cx<0 || cx>=M || cy<0 || cy>=N) {
				putSuccess();
				return 1;
			} else if(visited[cy][cx] == false) {
				que.offer(new Integer[] {cx,cy});
				visited[cy][cx] = true;
			} else if (successVisited[cy][cx]) {
				putSuccess();
				return 1;
			} else if (failVisited[cy][cx]) {
				putFail();
				return 0;
			}
		}
		putFail();
		return 0;
		
	}
	public static void putSuccess() {
		for(int i=0; i<currentVisited.size(); i++) {
			successVisited[currentVisited.get(i)[1]][currentVisited.get(i)[0]] = true;
		}
	}
	
	public static void putFail() {
		for(int i=0; i<currentVisited.size(); i++) {
			failVisited[currentVisited.get(i)[1]][currentVisited.get(i)[0]] = true;
		}
	}
	public static void setDir() {
		dir = new HashMap<>();
		dir.put('R', new Integer[] {1,0});
		dir.put('L', new Integer[] {-1,0});
		dir.put('U', new Integer[] {0,-1});
		dir.put('D', new Integer[] {0,1});
	}
}
