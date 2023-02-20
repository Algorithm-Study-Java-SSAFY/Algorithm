package codeTest;

import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);
		
		Integer[] coins = new Integer[N];
		for(int i=0;i<N;i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coins, Collections.reverseOrder());
		//System.out.println(Arrays.toString(coins));
		
		int answer = 0; //정답 동전 개수
		int idx = 0; //꺼낼 동전 index
		while(K>0) {
			if(K >= coins[idx]) { //현재 꺼낸 동전으로 처리되면
				answer += (K/coins[idx]);
				K -= (K/coins[idx])*coins[idx];
			}else {
				idx += 1;
			}
		}
		System.out.println(answer);
	}

}