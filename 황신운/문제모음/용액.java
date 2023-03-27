package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 용액 {
	public static int N, minDiff;
	public static int[] liquid, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		minDiff = Integer.MAX_VALUE;
		liquid = new int[N];
		result = new int[2];
		String[] inputStr = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			liquid[i] = Integer.parseInt(inputStr[i]);
		}
		
		solve();
		System.out.println(result[0] + " " + result[1]);
	}
	
	public static void solve() {
		int start = 0, end = N-1;
		minDiff = Math.abs(liquid[start] + liquid[end]);
		result[0] = liquid[start];
		result[1] = liquid[end];
		int diff =0;
		
		while(start<end) {
			diff = Math.abs(liquid[start] + liquid[end]);
			if(diff < minDiff) {
				minDiff = diff;
				result[0] = liquid[start];
				result[1] = liquid[end];
			}
			if(diff == 0) break;
			else if(Math.abs(liquid[start])> Math.abs(liquid[end])) start++;
			else end--;
		}
	}
}
