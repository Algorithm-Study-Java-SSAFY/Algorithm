package 스터디.Moo게임_5904;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static String moo = "moo";
	char a = 0;
	char b = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		solution(N, 3, 1);
	}
	
	
	
	
	// S(k) = [S(k-1)] + [M + O*(k+2)] + [S(k-1)]
	public static void solution(int n, int length, int k) {
		if(n <= 3) {
			System.out.println(moo.charAt(n-1));
			return;
		}
		int newLength = length + (1 + k + 2) + length;
		
		if(newLength < n) { // 더 진행 
			solution(n, newLength, k + 1);
		} else { 	
			if(n == length + 1) {	// 새로 생기는 구간 'M'
				System.out.println("m");
				return;
			}
			else if(length < n && n <= length + (1 + k + 2)) { // 새로 생기는 구간 'O'
				System.out.println("o");
				return;
			} else {	// 뒷 구간 
				solution(n - (newLength - length),  3, 1);
			}
		}
		
	}
}
