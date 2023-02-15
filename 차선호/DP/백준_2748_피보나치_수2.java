import java.io.*;
import java.util.*;

public class Main {
	
	static List<Integer []> startList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] dp = new long[n+1];
		dp[1] = 1;
		for(int i=2;i<n+1;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		//System.out.println(Arrays.toString(dp));
		System.out.println(dp[n]);
		
	}
}