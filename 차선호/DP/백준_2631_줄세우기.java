import java.util.*;
import java.io.*;



public class Main {
	
	static int N;
	static int[] NUMS;
	static int MAX;
	static int[] DP;
	
	public static void main(String[] args) throws Exception{
		init();
		makeDP();
//		System.out.println(Arrays.toString(DP));
		System.out.println(N-MAX);
	}
	
	static void makeDP() {
		MAX = 1;
		DP[0] = 1;
		for(int i=1;i<N;i++) {
			int max = 0;
			for(int j=0;j<i;j++) {
				if(NUMS[j]<NUMS[i]) max = Math.max(max, DP[j]);
			}
			DP[i] = max+1;
			MAX = Math.max(MAX, DP[i]);
		}
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		NUMS = new int[N];
		for(int i=0;i<N;i++) {
			NUMS[i] = Integer.parseInt(br.readLine());
		}
		MAX = 0;
		DP = new int[N];
	}
}