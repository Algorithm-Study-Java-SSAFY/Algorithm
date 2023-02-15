package codeTest;

import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			int K = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());
			
			int[][] dp = new int[K+1][N];
			
			for(int n=0; n<N; n++) {
				dp[0][n] = n+1;
			}
			
			for(int j=1;j<K+1;j++) {
				dp[j][0] = 1;
				for(int k=1;k<N;k++) {
					dp[j][k] = dp[j][k-1] + dp[j-1][k];
				}
			}
			
			sb.append(dp[K][N-1]+"\n");
		}
		
		System.out.println(sb.toString());
	}

}