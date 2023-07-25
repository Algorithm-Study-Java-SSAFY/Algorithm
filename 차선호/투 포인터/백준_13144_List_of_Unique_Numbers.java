import java.util.*;
import java.io.*;



public class Main {
	
	static int N;
	static long ANSWER;
	static int[] ARR, CNT;
	static HashSet<Integer> SET;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		
		solution();
		
		System.out.println(ANSWER);
		
	}
	
	static void solution() {
		
		CNT = new int[100001];
		ANSWER = 0;
		SET = new HashSet<>();
		
		int start = 1;
		int end = 0;
		
		while(start<=N) {
			
//			System.out.println(start+"/"+end+"--->"+ANSWER);
			
			if(CNT[ARR[start-1]]>0) CNT[ARR[start-1]]--;
			
		
			while (end+1 <= N && CNT[ARR[end+1]] <= 0) {
//				System.out.println("end : "+end);
                CNT[ARR[++end]]++;
            }
			
//			System.out.println(end+"-"+start);
			ANSWER += end-start+1;
			
			start++;
			
		}
	}
	
	
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		ARR = new int[N+1];
		String[] arr = br.readLine().split(" ");
		for(int i=1;i<N+1;i++) ARR[i] = Integer.parseInt(arr[i-1]);
	}
}