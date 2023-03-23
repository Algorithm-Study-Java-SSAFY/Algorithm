import java.util.*;
import java.io.*;


public class Main {

	static int[][] changeCnt = {
			{0,4,3,3,4,3,2,3,1,2},
			{4,0,5,3,2,5,6,1,5,4},
			{3,5,0,2,5,4,3,4,2,3},
			{3,3,2,0,3,2,3,2,2,1},
			{4,2,5,3,0,3,4,3,3,2},
			{3,5,4,2,3,0,1,4,2,1},
			{2,6,3,3,4,1,0,5,1,2},
			{3,1,4,2,3,4,5,0,4,3},
			{1,5,2,2,3,2,1,4,0,1},
			{2,4,3,1,2,1,2,3,1,0}
	};
	static int N,K,P,X;
	static String S="";
	static HashSet<String> set = new HashSet<>();
	static List<int[]> combs = new ArrayList<>();
	static int answer = 0;
	
	public static void main(String[] args) throws Exception{
		init();
		dfs(0,"",0);
		System.out.println(answer);
		
	}
	static void dfs(int idx, String s, int cnt) {
		if(idx==K) { 
			int result = Integer.parseInt(s);
			if(1<=result && result<=N && result!=X) {
//				System.out.println(s);
				answer++;
			}
			return;
		}
		int num = S.charAt(idx)-'0'; //현재 자리수의 숫자
		for(int i=0;i<10;i++) {
			if(cnt+changeCnt[num][i]<=P) { //num->i로 바꾸는 횟수 추가해도 P이하라면 가능
				dfs(idx+1,s+i,cnt+changeCnt[num][i]);
			}
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NKPX = br.readLine().split(" ");
		N = Integer.parseInt(NKPX[0]);
		K = Integer.parseInt(NKPX[1]);
		P = Integer.parseInt(NKPX[2]);
		X = Integer.parseInt(NKPX[3]);
		String SX = String.valueOf(X);
		for(int i=0;i<K;i++) { // 앞에 빈 자리 0으로 채워주는 것!
			if(i>=K-SX.length()) S += SX.charAt(i-(K-SX.length()));
			else S += "0";
		}
	}
}