package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 부분수열의합 {
	public static int N, S, subsequence;
	public static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		S = Integer.parseInt(tmp[1]);
		
		tmp = br.readLine().split(" ");
		arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(tmp[i]);
		
		solve();
		System.out.println(subsequence);
	}
	
	public static void solve() {
		for(int i=1; i<(1<<N); i++) {
			int sum = 0;
			for(int j=0; j<N; j++) {
				if((i & (1<<j))!= 0) sum += arr[j];
			}
			if(sum == S) subsequence++;
		}
	}
}
