package code_test;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N,M;
	static char[][] graph;
	static boolean[][] successVisited; //성공한 좌표 visited check
	static boolean[][] failVisited; //싪패한 좌표 visited check
	static boolean[][] totalVisited; //전체 좌표 visited check
	static final int[] UP = {-1,0};
	static final int[] RIGHT = {0,1};
	static final int[] DOWN = {1,0};
	static final int[] LEFT = {0,-1};
	static List<int[]> path; // 현재 경로
	static boolean success; // 성공 경로인지 실패 경로인지
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		graph = new char[N][M];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				graph[i][j] = input.charAt(j);
			}
		}
		
		successVisited = new boolean[N][M];
		failVisited = new boolean[N][M];
		totalVisited = new boolean[N][M];
		int[] start = new int[2];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!totalVisited[i][j]) {  // 방문하지 않은 점이면 
					start[0] = i;
					start[1] = j;
					path = new ArrayList<>(); // 여기서부터 경로 생성
					success = false; // 성공 여부 우선 실패로 세팅
					search(start); // 미로찾기 시작
					if(success) { // 성공 경로면
						addSuccessPath(path);
					}else { // 실패 경로면
						addFailPath(path);
					}
				}
			}
		}
		
		int answer = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(successVisited[i][j]) answer++;
			}
		}
		System.out.println(answer);
	}
	
	static void search(int[] start) {
		int x = start[0];
		int y = start[1];
		totalVisited[x][y] = true;
		path.add(new int[] {x,y});
		
		if(graph[x][y] == 'U') { // U 밟으면 
			x += UP[0];
			y += UP[1];
			if(0<=x&&x<N&&0<=y&&y<M) { // 그래프 내부라면(미로찾기 중)
				if(successVisited[x][y]) { //  성공 경로에 해당하는 땅 밟으면 이후 볼 필요 없음
					success = true;
					return;
				}else if(failVisited[x][y]) { //  실패 경로에 해당하는 땅 밟으면 이후 볼 필요 없음
					success = false;
					return;
				}else if(!totalVisited[x][y]){
					search(new int[] {x,y});
				}
			}else { // 그래프 벗어남 (미로찾기 성공)
				success = true;
				return;
			}
		}else if(graph[x][y] == 'R') {
			x += RIGHT[0];
			y += RIGHT[1];
			if(0<=x&&x<N&&0<=y&&y<M) {
				if(successVisited[x][y]) {
					success = true;
					return;
				}else if(failVisited[x][y]) {
					success = false;
					return;
				}else if(!totalVisited[x][y]) {
					search(new int[] {x,y});
				}
			}else {
				success = true;
				return;
			}
			
		}else if(graph[x][y] == 'D') {
			x += DOWN[0];
			y += DOWN[1];
			if(0<=x&&x<N&&0<=y&&y<M) {
				if(successVisited[x][y]) {
					success = true;
					return;
				}else if(failVisited[x][y]) {
					success = false;
					return;
				}else if(!totalVisited[x][y]) {
					search(new int[] {x,y});
				}
			}else {
				success = true;
				return;
			}
		}else if(graph[x][y] == 'L') {
			x += LEFT[0];
			y += LEFT[1];
			if(0<=x&&x<N&&0<=y&&y<M) {
				if(successVisited[x][y]) {
					success = true;
					return;
				}else if(failVisited[x][y]) {
					success = false;
					return;
				}else if(!totalVisited[x][y]) {
					search(new int[] {x,y});
				}
			}else {
				success = true;
				return;
			}
		}
		
	}
	
	static void addSuccessPath(List<int[]> path) {
		for(int[] node:path) {
			successVisited[node[0]][node[1]] = true;
		}
	}
	static void addFailPath(List<int[]> path) {
		for(int[] node:path) {
			failVisited[node[0]][node[1]] = true;
		}
	}
}