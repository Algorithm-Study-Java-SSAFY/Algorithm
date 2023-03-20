package code_test;

import java.util.*;
import java.io.*;


public class Main {
	
	static int R,C;
	static char[][] graph;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static List<int[]> gos = new ArrayList<>();
	static List<int[]> waters = new ArrayList<>();
	static int arrive = 0;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception{
		init();
		
		while(true) {

			
			if(!check()) { //고슴도치가 더 이상 이동할 수 없다면
				System.out.println("KAKTUS"); 
				break; 
			}

			answer++;
			bfsWaters(); // 물 확산
			bfsGos(); // 고슴도치 이동
			
			if(arrive==1) {
				System.out.println(answer);
				break;
			}
		}
		
		
	}
	
	static void bfsGos() {
		List<int[]> result = new ArrayList<>();
		for(int[] w:gos) {
			for(int i=0;i<4;i++) {
				int mx = w[0]+dx[i];
				int my = w[1]+dy[i];
				if(0<=mx&&mx<R&&0<=my&&my<C) {
					if(graph[mx][my]=='D') {
						arrive = 1;
						return;
					}
					if(graph[mx][my]=='.') {
						result.add(new int[] {mx,my});
						graph[mx][my] = 'S';
					}
				}
			}
		}
		gos = result;
	}
	
	static void bfsWaters() {
		List<int[]> result = new ArrayList<>();
		for(int[] w:waters) {
			for(int i=0;i<4;i++) {
				int mx = w[0]+dx[i];
				int my = w[1]+dy[i];
				if(0<=mx&&mx<R&&0<=my&&my<C) {
					if(graph[mx][my]=='.' || graph[mx][my]=='S') {
						result.add(new int[] {mx,my});
						graph[mx][my] = '*';
					}
				}
			}
		}
		
		waters = result;
	}
	
	
	static boolean check() {
		
		for(int[] go:gos) {
			for(int i=0;i<4;i++) {
				int mx = go[0]+dx[i];
				int my = go[1]+dy[i];
				if(0<=mx&&mx<R&&0<=my&&my<C) {
					if(graph[mx][my]=='.' || graph[mx][my]=='D') return true;
				}
			}
		}
		
		return false;
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] RC = br.readLine().split(" ");
		R = Integer.parseInt(RC[0]);
		C = Integer.parseInt(RC[1]);
		graph = new char[R][C];
		for(int i=0;i<R;i++) {
			String input = br.readLine();
			for(int j=0;j<C;j++) {
				graph[i][j] = input.charAt(j);
				if(graph[i][j] == '*') waters.add(new int[] {i,j});
				if(graph[i][j] == 'S') gos.add(new int[] {i,j});
			}
		}
	}
}