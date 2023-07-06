import java.util.*;
import java.io.*;


public class Main {
	
	static int N,ANSWER;
	static int[] BEFORE,AFTER,CHANGE,PRESS;
	
	public static void main(String[] args) throws Exception {
		
		init();
	
		solution();
		
		System.out.println(ANSWER);
	}
	
	static void solution() {
		
		makeChange();
		
//		System.out.println(Arrays.toString(CHANGE));
//		System.out.println();
	
		if(N==2) checkN2();
		else makePress();
		
	}
	
	static void checkN2() {
		if(CHANGE[0]==0 && CHANGE[1]==0) ANSWER = 0;
		if(CHANGE[0]!=CHANGE[1]) ANSWER = -1;
		if(CHANGE[0]==1 && CHANGE[1]==1) ANSWER = 1;
	}
	
	static void makePress() {
		PRESS = new int[N];
		int result = -1;
		
		PRESS[0]=0;
		PRESS[1]=0;
		result = checkRemain();
		if(result != -1) ANSWER = Math.min(ANSWER, result);
//		System.out.println(Arrays.toString(PRESS));
//		System.out.println(result);
		
		PRESS[0]=0;
		PRESS[1]=1;
		result = checkRemain();
		if(result != -1) ANSWER = Math.min(ANSWER, result);
//		System.out.println(Arrays.toString(PRESS));
//		System.out.println(result);
		
		PRESS[0]=1;
		PRESS[1]=0;
		result = checkRemain();
		if(result != -1) ANSWER = Math.min(ANSWER, result);
//		System.out.println(Arrays.toString(PRESS));
//		System.out.println(result);
		
		PRESS[0]=1;
		PRESS[1]=1;
		result = checkRemain();
		if(result != -1) ANSWER = Math.min(ANSWER, result);
//		System.out.println(Arrays.toString(PRESS));
//		System.out.println(result);
	}
	
	static int checkRemain() {
		
		for(int i=1;i<N-1;i++) {
			if(CHANGE[i]==0) {
				if(PRESS[i-1]==0 && PRESS[i]==0) PRESS[i+1] = 0;
				if(PRESS[i-1]==0 && PRESS[i]==1) PRESS[i+1] = 1;
				if(PRESS[i-1]==1 && PRESS[i]==0) PRESS[i+1] = 1;
				if(PRESS[i-1]==1 && PRESS[i]==1) PRESS[i+1] = 0;
			}else {
				if(PRESS[i-1]==0 && PRESS[i]==0) PRESS[i+1] = 1;
				if(PRESS[i-1]==0 && PRESS[i]==1) PRESS[i+1] = 0;
				if(PRESS[i-1]==1 && PRESS[i]==0) PRESS[i+1] = 0;
				if(PRESS[i-1]==1 && PRESS[i]==1) PRESS[i+1] = 1;
			}
		}
		
		//가능하면 횟수 리턴, 불가능하면  -1 리턴
		if (checkPossible()) return countPress();
		else return -1;
	}
	
	static int countPress() {
		int cnt = 0;
		for(int i=0;i<N;i++) {
			if(PRESS[i]==1) cnt++;
		}
		return cnt;
	}
	
	static boolean checkPossible() {
		
		if(CHANGE[0]==0) {
			if(PRESS[0]!=PRESS[1]) return false;
		}
		else {
			if(PRESS[0]==PRESS[1]) return false;
		}
		
		
		if(CHANGE[1]==0) {
			if(PRESS[0]==1 && PRESS[1]==1 && PRESS[2]==1) return false;
			if(PRESS[0]==1 && PRESS[1]==0 && PRESS[2]==0) return false;
			if(PRESS[0]==0 && PRESS[1]==1 && PRESS[2]==0) return false;
			if(PRESS[0]==0 && PRESS[1]==0 && PRESS[2]==1) return false;
		}
		else {
			if(PRESS[0]==0 && PRESS[1]==0 && PRESS[2]==0) return false;
			if(PRESS[0]==0 && PRESS[1]==1 && PRESS[2]==1) return false;
			if(PRESS[0]==1 && PRESS[1]==0 && PRESS[2]==1) return false;
			if(PRESS[0]==1 && PRESS[1]==1 && PRESS[2]==0) return false;
		}
		
		if(CHANGE[N-1]==0) {
			if(PRESS[N-2]!=PRESS[N-1]) return false;
		}
		else {
			if(PRESS[N-2]==PRESS[N-1]) return false;
		}
		
		return true;
	}
	
	static void makeChange() {
		CHANGE = new int[N];
		for(int i=0;i<N;i++) {
			CHANGE[i] = Math.abs(BEFORE[i]-AFTER[i]);
		}
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		BEFORE = new int[N];
		AFTER = new int[N];
		String before = br.readLine();
		String after = br.readLine();
		for(int i=0;i<N;i++) {
			BEFORE[i] = before.charAt(i)- 0;
			AFTER[i] = after.charAt(i) - 0;
		}
		ANSWER = Integer.MAX_VALUE;
	}
}