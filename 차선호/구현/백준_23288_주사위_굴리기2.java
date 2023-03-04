package codeTest;

import java.util.*;
import java.io.*;



public class Main {

	static int N,M,K;
	static int[][] graph;
	static int[] dx = {-1,0,1,0}; // 상,좌,하,우
	static int[] dy = {0,-1,0,1};
	static boolean[][] visited;
	static int[][] marble = {{0,2,0}
							,{4,1,3}
							,{0,5,0}
							,{0,6,0}
							}; // 초기 주사위 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMK = br.readLine().split(" ");
		N = Integer.parseInt(NMK[0]);
		M = Integer.parseInt(NMK[1]);
		K = Integer.parseInt(NMK[2]);
		graph= new int[N][M];
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int dir = 3; // 시작 방향 -> 동쪽 :우
		int bottom = marble[3][1]; // 맨 처음 바닥 숫자
		int[] start = {0,0}; // 시작 위치 0,0
		int answer = 0;
		for(int i=0;i<K;i++) {
			int mx = start[0]+dx[dir];
			int my = start[1]+dy[dir];
			if(!(0<=mx&&mx<N&&0<=my&&my<M)) { // 그래프 벗어나면
				dir = (dir+2)%4; // 반대방향으로 바꿔주고 2번 이동
				mx += dx[dir]+dx[dir];
				my += dy[dir]+dy[dir];
				//continue;
			}
			rotate(dir); //주사위 회전
			bottom = marble[3][1]; //현재 바닥 숫자
//			for(int[] a:marble) {
//				System.out.println(Arrays.toString(a));
//			}
			//System.out.println("--mx/my : "+mx+"/"+my);
			visited = new boolean[N][M];
			visited[mx][my] = true;
			int score = bfs(new int[] {mx,my}); //점수 계산
			answer += score;
			
			//System.out.println("bottom : "+bottom+"--score : "+score);
			if(bottom>graph[mx][my]) { //A>B
				dir = (dir+3)%4;
			}else if(bottom<graph[mx][my]) {//B<A
				dir = (dir+1)%4;
			}
			
			//System.out.println("바뀐 방향 : "+dir);
			start[0] = mx;
			start[1] = my;
		}
		
		System.out.println(answer);
	}
	
	//점수 계산
	static int bfs(int[] start) {
		int result = graph[start[0]][start[1]];
		Queue<int[]> needVisit = new LinkedList<>();
		visited[start[0]][start[1]] = true;
		needVisit.add(start);
		
		while(!needVisit.isEmpty()) {
			int[] node = needVisit.poll();
			for(int i=0;i<4;i++) {
				int mx = node[0]+dx[i];
				int my = node[1]+dy[i];
				if(0<=mx&&mx<N&&0<=my&&my<M && !visited[mx][my] 
						&& graph[node[0]][node[1]]==graph[mx][my]) {
					needVisit.add(new int[] {mx,my});
					visited[mx][my] = true;
					result += graph[mx][my];
				}
			}
		}
		
		return result;
	}
	
	
	//주사위 굴렸을 때 주사위 배치 변화
	static void rotate(int dir) {
		if(dir==0) { // 위로 굴려라
			int temp = marble[0][1];
			for(int i=0;i<3;i++) {
				marble[i][1] = marble[i+1][1];
			}
			marble[3][1] = temp;
		}else if(dir==1) { // 좌로 굴려라
			for(int j=0;j<2;j++) {
				marble[1][j] = marble[1][j+1];
			}
			marble[1][2] = 7-marble[1][0];
			marble[3][1] = 7-marble[1][1];
		}else if(dir==2) { // 밑으로 굴려라
			int temp = marble[3][1];
			for(int i=3;i>0;i--) {
				marble[i][1] = marble[i-1][1];
			}
			marble[0][1] = temp;
		}else { // 우로 굴려라
			for(int j=2;j>0;j--) {
				marble[1][j] = marble[1][j-1];
			}
			marble[1][0] = 7-marble[1][2];
			marble[3][1] = 7-marble[1][1];
		}
	}
}