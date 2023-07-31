import java.util.*;
import java.io.*;



public class Main {
	
	static int K,P,N;
	static long ANSWER;
	
	public static void main(String[] args) throws Exception {
		

		init();
		
		solution();
		
		System.out.println(ANSWER);
	}
	
	static void solution() {
		
		for(int i=0;i<N;i++) {
			ANSWER = (ANSWER*P)%1000000007;
		}
		
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] KPN = br.readLine().split(" ");
		K = Integer.parseInt(KPN[0]);
		P = Integer.parseInt(KPN[1]);
		N = Integer.parseInt(KPN[2]);
		
		ANSWER = K;
	}
}