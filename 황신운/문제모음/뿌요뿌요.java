package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 뿌요뿌요 {
	public static int N = 12;
	public static int M = 6;
	public static String[][] map;
	public static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new String[N][M];
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = "" + tmp.charAt(j);
			}
		}
		
		int turn = 0;
		while(true) {			
		
			visited = new boolean[N][M];
			int cnt = 0;
			
			for(int i=N-1; i>=0; i--) {
				for(int j=0; j<M; j++) {
					if(!map[i][j].equals(".") && visited[i][j] == false) {
						visited[i][j] = true;
						cnt += bfs(i,j,map[i][j]);
					}
				}
			}
			if(cnt != 0) {
				turn++;
				modifyMap();

			} else {
				break;
			}
		}
		
		System.out.println(turn);
	}
	
	public static int bfs(int y, int x, String color) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		int cnt = 1;
		
		Queue<Integer[]> que = new LinkedList<>();
		que.offer(new Integer[] {x,y});
		List<Integer[]> point = new ArrayList<>();
		point.add(new Integer[] {x,y});
		
		while(!que.isEmpty()) {
			Integer[] xy = que.poll();
			x = xy[0];
			y = xy[1];
			
			for(int i=0; i<4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				
				if(cx<0 || cx>=M || cy<0 || cy>=N) continue;
				if(map[cy][cx].equals(color) && visited[cy][cx] == false) {
					que.offer(new Integer[] {cx,cy});
					point.add(new Integer[] {cx,cy});
					visited[cy][cx] = true;
					cnt++;
//					System.out.println(cnt);
				}
			}
		}
		
		if(cnt >= 4) {
			for(int i=0; i<point.size(); i++) {
				map[point.get(i)[1]][point.get(i)[0]] = " ";
			}
			return 1;
		}
		
		return 0;
	}
	
	public static void modifyMap() {
		
		for(int i=0; i<M; i++) {
			for(int j=N-1; j>=0; j--) {
				if(map[j][i].equals(" ")) {
					int idx = j, cnt = 0;
					while(map[idx][i].equals(" ")) {
						idx--;
						if(idx == -1)
							break;
						cnt++;
					}
					for(int k=j; k>=0; k--) {
						if(k-cnt>=0)
							map[k][i] = map[k-cnt][i];
						else
							map[k][i] = ".";
					}
				}
			}
		}
	}
}
