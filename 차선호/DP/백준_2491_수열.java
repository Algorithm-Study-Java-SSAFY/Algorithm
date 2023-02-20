package codeTest;

import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] NList = new int[N];
		int[] dp = new int[N*2]; //앞에 반은 증가, 뒤에 반은 감소
		String[] str = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			NList[i] = Integer.parseInt(str[i]);
		}
		
		dp[0]=1;
		for(int i=1;i<N;i++) {
			if(NList[i] >= NList[i-1]) { //연속해서 커진다면
				dp[i] = dp[i-1]+1;
			}else {
				dp[i] = 1;
			}
		}
		dp[N] = 1;
		for(int i=N+1;i<N*2;i++) {
			if(NList[i-N] <= NList[i-1-N]) { //연속해서 작아진다면
				dp[i] = dp[i-1]+1;
			}else {
				dp[i] = 1;
			}
		}
		//System.out.println(Arrays.toString(dp));
		Arrays.sort(dp);
		//System.out.println(Arrays.toString(dp));
		System.out.println(dp[2*N-1]);
		
	}

}