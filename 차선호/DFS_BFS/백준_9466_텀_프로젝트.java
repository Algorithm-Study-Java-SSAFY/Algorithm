import java.util.*;
import java.io.*;



public class Main {
	
	static BufferedReader BR;
	static int T,N,CNT;
	static int[] ARR;
	static boolean[] VISITED, TOTAL_VISITED;
	static HashSet<Integer> SET;
	static StringBuffer SB;
	
	public static void main(String[] args) throws Exception {
		

		BR = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(BR.readLine());
		SB = new StringBuffer();
		
		for(int i=0;i<T;i++) {
			init();
			solution();
			SB.append(N-CNT+"\n");
		}
		
		System.out.println(SB);
	}
	
	static void solution() {
		
		for(int i=1;i<N+1;i++) {
			if(TOTAL_VISITED[i]) continue;
			System.out.println(i+"---------------------------");
			search(i);
		}
	}
	
	static void search(int current) {
		
		System.out.println(current);
		
		if(TOTAL_VISITED[current]) return;
		
		if(VISITED[current]) { //사이클에 해당한다는 뜻
			TOTAL_VISITED[current] = true;
			CNT++;
		}
		VISITED[current] = true;
		search(ARR[current]);
		TOTAL_VISITED[current] = true; //성공이든 실패든 visited 처리	
	}
	
	
	
	//초기 입력
	static void init() throws Exception{
		N = Integer.parseInt(BR.readLine());
		ARR = new int[N+1];
		CNT = 0;
		VISITED = new boolean[N+1];
		TOTAL_VISITED = new boolean[N+1];
		String[] arr = BR.readLine().split(" ");
		for(int i=1;i<N+1;i++) ARR[i] = Integer.parseInt(arr[i-1]);
	}
}