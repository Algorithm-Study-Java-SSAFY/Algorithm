import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br; 
	static int N,K; 
	static int[] coin; 
	static int[] used; 
	public static void main(String[] args) throws Exception {
		init();
		find();
		System.out.println(used[K]);
	}
	
	static void find() {
		used[0] = 1;  // ex) 5원짜리 코인이 5원을 만들 수 있는 경우(used[5-5])는 한 번 뿐이다. 
		
		for(int i=1; i<=N; i++) { // 동전 
			for(int j=coin[i]; j<=K; j++) { // 금액 
				
				// 만약 j가 5이고, coin이 3원이라면 -> 2원에 3원을 더하면 5원을 만들 수 있음으로
				// 2원을 만들 수 있는 경우를 5원에 더해준다. 
				used[j] += used[j-coin[i]];
			}
//			System.out.println(Arrays.toString(used));
		}
	}
	
		
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		K = Integer.parseInt(s[1]);
		
		coin = new int[N+1]; 
		used = new int[K+1];
		
		for(int i=1; i<=N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coin);
	}

}