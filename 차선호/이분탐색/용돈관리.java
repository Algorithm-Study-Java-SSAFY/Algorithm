package code_test;

import java.io.*;
import java.util.*;

public class Solution {
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strNM = br.readLine().split(" ");
		int N = Integer.parseInt(strNM[0]);
		int M = Integer.parseInt(strNM[1]);
		
		int[] Nlist = new int[N];
		int maxN = 0;
		for(int i=0;i<N;i++) {
			Nlist[i] = Integer.parseInt(br.readLine());
			if(Nlist[i] > maxN) {
				maxN = Nlist[i];
			}
		}
		int start=maxN;
		int end=1000000000;
		int answer = end;
		while(true) {
			if(start>end) {
				break;
			}
			int mid = (start+end)/2;
			int K = mid;
			int cnt = check(K, Nlist);
			if (cnt > M) {
				start = mid+1;
			}else {
				answer = K;
				end = mid-1;
			}
		}
		System.out.println(answer);
	}
	
	static int check(int K, int[] Nlist ) {
		int cnt=0;
		int sum=0;
		for(int i=0;i<Nlist.length;i++) {
			if(sum<Nlist[i]) {
				sum = K-Nlist[i];
				cnt++;
			}else {
				sum -= Nlist[i];
			}
			if (sum < 0) {
				return 1;
			}
		}
		
		return cnt;
		
	}
	
	
}