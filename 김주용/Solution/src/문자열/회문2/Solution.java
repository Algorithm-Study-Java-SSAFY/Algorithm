package 문자열.회문2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(in.readLine());
			char[][] board = new char[100][100];
			for(int i = 0; i < 100; i++) {
				board[i] = in.readLine().toCharArray();
			}
			
			int answer = solution(board);
			System.out.printf("#%d %d\n", t, answer);
		}
	}
	
	public static int solution(char[][] board) {
		int ret = 0;
		for(int N = 100; N > 0; N--) {
			
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j <= 100 - N; j++) {
					char[] strArr = rowSlice(board, N, i, j);
					if(isPalindrome(strArr, N)) {
						return N;
					}
				}
			}
			
			for(int i = 0; i <= 100 - N; i++) {
				for(int j = 0; j < 100; j++) {
					char[] strArr = colSlice(board, N, i, j);
					if(isPalindrome(strArr, N)) {
						return N;
					}
				}
			}
		}
		
		return ret;
	}
	
	public static char[] rowSlice(char[][] board, int N, int y, int x) {
		char[] newRowStr = new char[N];
		for(int i = 0; i < N; i++) {
			newRowStr[i] = board[y][x+i];
		}
		return newRowStr;
	}
	
	public static char[] colSlice(char[][] board, int N, int y, int x) {
		char[] newColStr = new char[N]; 
		for(int i = 0; i < N; i++) {
			newColStr[i] = board[y+i][x];
		}
		return newColStr;
	}
	
	
	public static boolean isPalindrome(char[] str, int N) {
		int left = 0, right = N-1;
		while(left <= N/2 || right >= N/2) {
			if(str[left] != str[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}