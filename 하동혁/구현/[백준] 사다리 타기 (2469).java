import java.util.*;
import java.io.*;



public class Main {
	
	static char[] alpa = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q'
			,'R','S','T','U','V','W','X','Y','Z'};

	static BufferedReader br; 
	static int K, N;
	static char[] start; 
	static char[] arrive;
	static char[][] board;
	
	static char[] mStart;
	static char[] mArrive; 
	
	static String answer = ""; 
	
	public static void main(String[] args) throws Exception {
	
		init();
		start();
		boolean tf = compareMiddle();
		
		if(tf) {
			makeAnswer();
			System.out.println(answer);
		}else {
			for(int i=0; i<K-1; i++) {
				answer += "x";
			}
//			System.out.println();
			System.out.println(answer);
		}
	}
	
	static void makeAnswer() {
		for(int i=0; i<K-1; i++) {
			if(mStart[i] == mArrive[i+1] || mStart[i+1] == mArrive[i]) answer += "-";
			else answer += "*";
		}
	}
	
	static boolean compareMiddle() {
		for(int i=0; i<K-2; i++) {
			if(mStart[i] == mArrive[i+1] && mStart[i+1] == mArrive[i+2]) {
//				System.out.println("false 1");
				return false;
			}
		}
		
		for(int i=2; i<K; i++) {
			if(mStart[i] == mArrive[i-1] && mStart[i-1] == mArrive[i-2]) {
//				System.out.println("false 2");
				return false;
			}
		}
		
		for(int i=0; i<K; i++) {
			if(i==0 && mStart[i] != mArrive[i+1] && mStart[i] != mArrive[i]) {
//				System.out.println("false 3");
				return false;
			}
			if(i==K-1 && mStart[i] != mArrive[i-1] && mStart[i] != mArrive[i]) {
//				System.out.println("false 4");
				return false;
			}
			if(i!=0 && i!=K-1 && mStart[i] != mArrive[i-1] && mStart[i] != mArrive[i+1] && mStart[i] != mArrive[i]) {
//				System.out.println("false 5");
				return false; 
			}
		}
		
		return true;
	}
	
	static void start() {
		for(int i=0; i<K; i++) {
			find(i);
			reverseFind(i);
		}
		
//		System.out.println(Arrays.toString(mStart));
//		System.out.println(Arrays.toString(mArrive));
		
	}
	
	static void find(int idx) {
		int deep = 0; // 행 위치 
		char traget = start[idx];
		int posi = idx; // 열 위치 
		
		while(true) {
			if(board[deep][0] == '?') break;
			
			if(posi==0) {
				if(board[deep][posi] == '-') posi++;
			}else if(posi==K-1) {
				if(board[deep][posi-1] == '-') posi--;
			}else {
				if(board[deep][posi] == '-') posi++;
				else if(board[deep][posi-1] == '-') posi--; 
			}
			deep++;
		}
		
		mStart[posi] = traget;
	}
	
	static void reverseFind(int idx) {
		int deep = N-1; // 행 위치 
		char traget = arrive[idx];
		int posi = idx; // 열 위치 
		
		while(true) {
			if(board[deep][0] == '?') break;
			
			if(posi==0) {
				if(board[deep][posi] == '-') posi++;
			}else if(posi==K-1) {
				if(board[deep][posi-1] == '-') posi--;
			}else {
				if(board[deep][posi] == '-') posi++;
				else if(board[deep][posi-1] == '-') posi--; 
			}
			deep--;
		}
		
		mArrive[posi] = traget;
	}
	
	
		
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		start = new char[K];
		arrive = new char[K];
		board = new char[N][K-1];
		
		mStart = new char[K];
		mArrive = new char[K];
		
		String s = br.readLine();
		for(int i=0; i<K; i++) {
			start[i] = alpa[i];
			arrive[i] = s.charAt(i);
		}
		
		for(int i=0; i<N; i++) {
			String s2 = br.readLine();
			for(int j=0; j<K-1; j++) {
				board[i][j] = s2.charAt(j);
			}
		}
		
	
		
		
	}
		
		
	

}