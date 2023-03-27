package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 탈출 {
	public static int R, C, minTime = 2500;
	public static String[][] map;
	public static boolean[][] visited;
	public static List<Integer[]> waterSpot;
	public static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		R = Integer.parseInt(tmp[0]);
		C = Integer.parseInt(tmp[1]);

		map = new String[R][C];
		waterSpot = new ArrayList<>();
		int beaverX = 0, beaverY = 0, hedgehogX = 0, hedgehogY = 0;	// 고슴도치와 비버 위치 저장 
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = "" + str.charAt(j);
				if (map[i][j].equals("D")) {
					beaverY = i;
					beaverX = j;
				} else if (map[i][j].equals("S")) {
					hedgehogY = i;
					hedgehogX = j;
				} else if(map[i][j].equals("*")) waterSpot.add(new Integer[] {i,j});	// 물이 표시되어 있는 좌표 저장 
			}
		}

		move(hedgehogY, hedgehogX, beaverY, beaverX);
		if (minTime != 2500)
			System.out.println(minTime);
		else
			System.out.println("KAKTUS");

	}

	// 갈 수 있는 경로 찾기 
	public static void move(int hedgehogY, int hedgehogX, int beaverY, int beaverX) {
		visited = new boolean[R][C];
		visited[hedgehogY][hedgehogX] = true;
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] {hedgehogY, hedgehogX, 0});
		int turn = -1;
		
		while(!q.isEmpty()) {
			Integer[] info = q.poll();
			int y = info[0];
			int x = info[1];
			int currentTime = info[2];
			map[y][x] = "S";
			if(y == beaverY && x == beaverX) {
				minTime = Math.min(minTime, currentTime);
				return;
			}
			if(turn<currentTime) {
				putWater();	// 다음 시간에 물이 찰 곳은 가면 안되기 때문에 미리 물로 채우기
				turn++;
			}
			
			for(int i=0; i<4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				if(cx<0 || cx>=C || cy<0 || cy>=R) continue;
				if(visited[cy][cx] || map[cy][cx].equals("*") || map[cy][cx].equals("X")) continue;
				visited[cy][cx] = true;
				q.offer(new Integer[] {cy, cx, currentTime + 1});
			}
			map[y][x] = ".";
		}
	}

	// map을 물로 채우는 함수
	public static void putWater() {
		List<Integer[]> addedSpot = new ArrayList<>();
		
		// 인접한 곳 map에 채우고 새롭게 채워진 곳을 addedSpot list에 저장.
		for(int i=0; i<waterSpot.size(); i++) {
			int wy = waterSpot.get(i)[0];
			int wx = waterSpot.get(i)[1];
			for(int d=0; d<4; d++) {
				int cy = wy + dy[d];
				int cx = wx + dx[d];
				if(cx<0 || cx>=C || cy<0 || cy>=R) continue;
				if(map[cy][cx].equals(".")) {
					addedSpot.add(new Integer[] {cy,cx});
					map[cy][cx] = "*";
				}
			}
		}
//		
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println("");
//		}
		// 물의 위치가 저장된 곳에 새롭게 저장된 위치 값도 더해줌. 바로 더해주면 값이 이상해지므로
		waterSpot.addAll(addedSpot);
	}

}