package codeTest;

import java.util.*;
import java.io.*;



public class Main {
	
	static int H,W;
	static int[][] graph;
	static int answer = 0; // 최종 정답
	static int[] dx = {0,0};
	static int[] dy = {1,-1};
	static boolean[][] visited;
	static int cnt = 0;
	static boolean leftBlock = false; //왼쪽 벽 부딪혔나
	static boolean rightBlock = false; //오른쪽 벽 부딪혔나
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] HW = br.readLine().split(" ");
		H = Integer.parseInt(HW[0]);
		W = Integer.parseInt(HW[1]);
		graph = new int[H][W];
		String[] input = br.readLine().split(" ");
		for(int i=0;i<W;i++) {
			int height = Integer.parseInt(input[i]);
			for(int j=H-1;j>H-1-height;j--) {
				graph[j][i] = 1;
			}
		}
		
//		for(int[] row:graph) {
//			System.out.println(Arrays.toString(row));
//		}
		
		visited = new boolean[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(!visited[i][j] && graph[i][j]==0) {
					cnt = 1;
					leftBlock = false;
					rightBlock = false;
					visited[i][j] = true;
					int[] start = {i,j};
					leftSearch(start);
					rightSearch(start);
					if(leftBlock && rightBlock) {
						answer += cnt;
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	static void leftSearch(int[] start) {
		
		Queue<int[]> needVisit = new LinkedList<>();
		needVisit.add(start);
		
		while(!needVisit.isEmpty()) {
			int[] node = needVisit.poll();
			int mx = node[0];
			int my = node[1]-1;
			if(0<=mx&&mx<H&&0<=my&&my<W && !visited[mx][my]) {
				if(graph[mx][my]==1) {//왼쪽 벽을 만나면
					leftBlock = true;
				}else {
					needVisit.add(new int[] {mx,my});
					visited[mx][my] = true;
					cnt++;
				}
			}
		}
	}
	
	static void rightSearch(int[] start) {
		
		Queue<int[]> needVisit = new LinkedList<>();
		needVisit.add(start);
		
		while(!needVisit.isEmpty()) {
			int[] node = needVisit.poll();
			int mx = node[0];
			int my = node[1]+1;
			if(0<=mx&&mx<H&&0<=my&&my<W && !visited[mx][my]) {
				if(graph[mx][my]==1) {//왼쪽 벽을 만나면
					rightBlock = true;
				}else {
					needVisit.add(new int[] {mx,my});
					visited[mx][my] = true;
					cnt++;
				}
			}
		}
	}
	
	
}