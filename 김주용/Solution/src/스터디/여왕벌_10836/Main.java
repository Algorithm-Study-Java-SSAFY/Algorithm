package 스터디.여왕벌_10836;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	
	static int M;
	static int N;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[M][M];
		
		// N * 2(M-1) = 2800 * 1,000,000 = 1,400,000,000
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			// 제일 왼쪽 열 애벌레 키우기 
			for(int j = M-1; j > 0; j--) { 
				if(zero != 0) {
					zero--;
				} else if(one != 0) {
					one--;
					board[j][0] += 1;
				} else if(two != 0) {
					two--;
					board[j][0] += 2;
				}
			}

			// 제일 위쪽 행 애벌레 키우기 
			for(int j = 0; j < M; j++) {
				if(zero != 0) {
					zero--;
				} else if(one != 0) {
					one--;
					board[0][j] += 1;
				} else if(two != 0) {
					two--;
					board[0][j] += 2;
				}
			}
		}
		solution();
		
	}
	
	public static void solution() {
		// 700 * 700 = 490000
		StringBuffer answer = new StringBuffer();
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				if(i == 0 || j == 0) {
					answer.append(++board[i][j] + " ");
					if(i == 0 && j == M-1) {
						answer.append("\n");
					}
					continue;
				}
				
				board[i][j] = getMax(i, j);
				answer.append(board[i][j] + " ");
				if(j == M-1) {
					answer.append("\n");
				}
			}
		}
		System.out.println(answer);
	}
	
	public static int getMax(int y, int x) {
		return Math.max(board[y][0], board[0][x]);
	}
	
//	// 
//	public static void print() {
//		StringBuffer answer = new StringBuffer();
//		for(int i = 0; i < M; i++) {
//			for(int j = 0; j < M; j++) {
//				answer.append(board[i][j] + " ");
//			}
//			answer.append("\n");
//		}
//		System.out.println(answer);
//	}
}
