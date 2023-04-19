import java.util.*; 
import java.io.*;

public class Main {
    static BufferedReader br;

    static int N; 
    static int[] numArr; 
    static int[] dp;
    static int maxlen; 
    
    public static void main(String[] args) throws IOException {

        init();
        find();
        System.out.println(N-maxlen);
    }

    
    static void find() {
    	maxlen = 0; // 최장 부분수열 
    	dp[0] = 1; 
    	
    	for(int i=1; i<N; i++) {
    		dp[i] = 1; 
    		for(int j=0; j<i; j++) {
    			if(numArr[i] > numArr[j]) dp[i] = Math.max(dp[i], dp[j]+1);
    		}
//    		System.out.println(Arrays.toString(dp));
    		maxlen = Math.max(maxlen, dp[i]);
    	}
//    	System.out.println(Arrays.toString(dp));
    }

    
    
    static void init() throws NumberFormatException, IOException {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        numArr = new int[N];
        dp = new int[N];
        for(int i=0; i<N; i++) {
        	numArr[i] = Integer.parseInt(br.readLine());
        }
        
    }

    
} 
