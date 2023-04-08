import java.util.*;
import java.io.*;


public class Main {
	
	static int N,K;
	static int[] dp;
	static int answer;
	
	public static void main(String[] args) throws Exception{
		init();
		dijkstra();
		System.out.println(dp[K]);
	}
	
	static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			return o1[1]-o2[1];
		});
		pq.add(new int[] {N,0});
		dp[N] = 0;
		
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			int dest = node[0];
			int cnt = node[1];
			if(dest-1>=0 && dp[dest-1]>cnt+1) {
				dp[dest-1] = cnt+1;
				pq.add(new int[] {dest-1,cnt+1});
			}
			if(dest+1<100_001 && dp[dest+1]>cnt+1) {
				dp[dest+1] = cnt+1;
				pq.add(new int[] {dest+1,cnt+1});
			}
			if(dest*2<100_001 && dp[dest*2]>cnt) {
				dp[dest*2] = cnt;
				pq.add(new int[] {dest*2,cnt});
			}
		}
		
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		N = Integer.parseInt(NK[0]);
		K = Integer.parseInt(NK[1]);
		dp = new int[100_001];
		for(int i=0;i<100_001;i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		answer = Integer.MAX_VALUE;
	}
}