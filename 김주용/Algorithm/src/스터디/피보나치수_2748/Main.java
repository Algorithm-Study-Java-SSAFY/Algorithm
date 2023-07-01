package 스터디.피보나치수_2748;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static long[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
	       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	       BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

	       int n = Integer.parseInt(in.readLine());
	       long answer = 0;
	       dp = new long[n+1];
	       dp[0] = 0; 
	       dp[1] = 1;
	       for(int i = 2; i < n+1; i++) {
	    	   dp[i] = dp[i-1] + dp[i-2];
	       }
	       
	       
	       out.write(Long.toString(dp[n]));
	       in.close();
	       out.close();
	       
	}

}
