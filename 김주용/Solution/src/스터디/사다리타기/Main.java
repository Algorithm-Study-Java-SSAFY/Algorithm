package 스터디.사다리타기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
	static int K;
	static int N;
	static char[][] board;

	static String starts;
	static HashMap<Character, Integer> results;

	static int hide;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(in.readLine());
		N = Integer.parseInt(in.readLine());
		results = new HashMap<>();
		char[] line = in.readLine().toCharArray();
		for (int i = 0; i < K; i++) {
			results.put(line[i], i);
		}

		starts = "";
		for (int i = 0; i < K; i++) {
			starts += (char) (i + 'A');
		}

		board = new char[N][2 * K - 1];
		for (int i = 0; i < N; i++) {
			line = in.readLine().toCharArray();
			int idx = 0;
			for (int j = 0; j < 2 * K - 1; j++) {
				if (j % 2 == 0) {
					board[i][j] = '.';
				} else {
					board[i][j] = line[idx++];
				}
				if (board[i][1] == '?') {
					hide = i;
				}
			}
		}
		System.out.println(soluton());
	}

	// 2 ^ 26 -> 불가
	public static String soluton() {
		String answer = "";
		for (int i = 0; i < K; i++) {
			char cur = starts.charAt(i);
			int startY = 0, startX = i * 2;
			int sx = goDown(startY, startX);
			
			
			int destIdx = results.get(cur);
			int destY = N-1, destX = destIdx * 2;
			int dx = goUp(destY, destX);
			
			if(sx == dx) {
				if(sx - 1 > -1 && board[hide][sx - 1] != '-') {
					board[hide][sx - 1] = '*';
				}
				if(sx + 1 < 2 * K - 1 && board[hide][sx + 1] != '-') {
					board[hide][sx + 1] = '*';
				}
			} else if(sx - dx == 2) { // 왼쪽 
				if(sx - 1 > -1 && board[hide][sx - 1] != '*') {
					if(sx + 1 >= 2 * K - 1) {
						board[hide][sx - 1] = '-';
					} else if(board[hide][sx + 1] != '-') {
						board[hide][sx - 1] = '-';
					}
				}
				if(sx + 1 < 2 * K - 1 && board[hide][sx + 1] != '-') {
					board[hide][sx + 1] = '*';
				}
				
			} else if(dx - sx == 2) { // 오른쪽 
				if(sx - 1 > -1 && board[hide][sx - 1] != '-') {
					board[hide][sx - 1] = '*';
				}
				if(sx - 1 > -1 && sx + 1 < 2 * K - 1 && board[hide][sx + 1] != '*') {
					if(sx - 1 < 0) {
						board[hide][sx + 1] = '-';
					} else if(board[hide][sx - 1] != '-') {
						board[hide][sx + 1] = '-';
					}
				}
			} 
		}
		String fail = "";
		for(int j = 1; j < K; j++) {
			fail += "x";
		}
		
		for(int i = 1; i < 2 * K - 1; i+=2) {
			answer += board[hide][i];
			if(board[hide][i] == '?') {
				return fail;
			}
		}
		return answer;
	}

	public static int goDown(int y, int x) {
		while (y <= hide) {
			if (x - 2 > -1 && board[y][x - 1] == '-') {
				x -= 2;
			} else if (x + 2 < 2 * K - 1 && board[y][x + 1] == '-') {
				x += 2;
			}
			y += 1;
		}
		return x;
	}

	public static int goUp(int y, int x) {
		while (y >= hide) {
			if (x - 2 > -1 && board[y][x - 1] == '-') {
				x -= 2;
			} else if (x + 2 < 2 * K - 1 && board[y][x + 1] == '-') {
				x += 2;
			}
			y -= 1;
		}
		return x;
	}
	
	public static void debug() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println();
	}
}
