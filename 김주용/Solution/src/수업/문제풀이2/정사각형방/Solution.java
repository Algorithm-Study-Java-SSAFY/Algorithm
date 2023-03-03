package 수업.문제풀이2.정사각형방;

import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Solution {
	
	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };
	
	static int[][] roomMap;
	
	static int N;
	static int[][] board;
	static int[][] path;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(in.readLine());
			board = new int[N][N];
			path = new int[N][N];
			for(int i = 0; i < N; i++) {
				board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			String answer = solution();
			System.out.printf("#%d %s\n", test_case, answer);
			solution();
		}
	}
	
	public static String solution() {
		int ret = 0;
		int roomNum = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(path[i][j] == 0) {
					bfs(i, j);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				ret = Math.max(ret, path[i][j]);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(path[i][j] != ret) {
					continue;
				}
				roomNum = Math.min(roomNum, board[i][j]);
			}
		}
		return roomNum + " " + ret;
	}
	
	public static void bfs(int y, int x) {
		Queue<int []> queue = new LinkedList<int[]>();
		queue.add(new int[] {y, x});
		boolean[][] visited = new boolean [N][N];
		visited[y][x] = true;
		path[y][x] = 1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
				if(-1 < ny && ny < N && -1 < nx && nx < N && !visited[ny][nx]) {
					if(board[cur[0]][cur[1]] - 1 == board[ny][nx]) {
						queue.add(new int[] {ny, nx});
						visited[ny][nx] = true;
						path[ny][nx] = Math.max(path[ny][nx], path[cur[0]][cur[1]] + 1);
					}
				}
			}
		}
		//debug(y + " " + x);
	}
	
	public static void debug(String msg) {
		System.out.println(msg);
		for(int [] i : path) {
			System.out.println(Arrays.toString(i));
		}
		System.out.println(" ");
	}
}
