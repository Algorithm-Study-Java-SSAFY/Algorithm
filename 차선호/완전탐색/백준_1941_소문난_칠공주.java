package code_test;


import java.util.*;
import java.io.*;


public class Main {

	static char[][] graph = new char[5][5];
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] visited = new boolean[5][5];
	static HashSet<String> routes = new HashSet<>();
	static int answer = 0;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for(int i=0;i<5;i++) {
			String input = br.readLine();
			for(int j=0;j<5;j++) {
				graph[i][j] = input.charAt(j);
			}
		}
		
		answer = 0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				visited[i][j] = true;
				String route = ""+i+j;
				if(graph[i][j] == 'S') {
					combinations(route,1,1);
				}else {
					combinations(route,1,0);
				}
				route = "";
				visited[i][j] = false;
			}
		}

		System.out.println(routes.size());
	}
	
	static void combinations(String route, int depth, int cnt) {
		if(depth == 7) {
			
			if(cnt >= 4) {
				String result = "";
				for(int i=0;i<5;i++) {
					for(int j=0;j<5;j++) {
						if(visited[i][j]) {
							result = result+i+j;
						}
					}
				}
				routes.add(result);
//				System.out.println(routes.size());
//				System.out.println("----------------");
			}
			return;
		}
		

		//System.out.println(route);
		for(int i=0;i<route.length();i+=2) { //00-01이 들어오면 00 한번 01 한번 각각 상하좌우 보고 붙여준다.
			int x = Integer.parseInt(String.valueOf(route.charAt(i)));
			int y = Integer.parseInt(String.valueOf(route.charAt(i+1)));
			for(int k=0;k<4;k++) {
				int mx = x+dx[k];
				int my = y+dy[k];
				if(0<=mx&&mx<5&&0<=my&&my<5 && !visited[mx][my]) {
					visited[mx][my] = true;
					if(graph[mx][my] == 'S') {
						combinations(route+mx+my,depth+1,cnt+1);
					}else {
						combinations(route+mx+my,depth+1,cnt);
					}
					visited[mx][my] = false;
				}
			}
		}
		
	}
	
	
}