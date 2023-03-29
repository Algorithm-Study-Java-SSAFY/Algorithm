package project1;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N,K;
	static int[] data;
	static long[] dp;
	static int start, end, sum;
	
	public static void main(String[] args) throws Exception {
		init();
		search();
		System.out.println(dp[N]);
	}
	
	static void search() {
		while(true) {
			if(sum >= K) {
				dp[end] = Math.max(dp[start]+sum-K,dp[end]);
				sum -= data[start++];
			}else {
				dp[end] = Math.max(dp[end], dp[end-1]);
				if(end==N) break;
				sum += data[end++];
			}
		}
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		N = Integer.parseInt(NK[0]);
		K = Integer.parseInt(NK[1]);
		data = new int[N];
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			data[i] = Integer.parseInt(input[i]);                                            
		}
		dp = new long[N+1];
		start = 0;
		end = 1;
		sum = data[0];
	}
}