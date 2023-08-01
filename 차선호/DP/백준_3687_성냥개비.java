import java.util.*;
import java.io.*;



public class Main {
	
	static int T, N;
	static long ANSWER_MIN;
	static String ANSWER_MAX;
	static BufferedReader BR;
	static StringBuffer SB;
	static HashMap<Integer, Integer> MAP;
	static long[] DP;
	static int[] ARR = {1,7,4,2,0,8};
	static PriorityQueue<Integer> PQ;
	
	public static void main(String[] args) throws Exception {
		
		BR = new BufferedReader(new InputStreamReader(System.in));
		SB = new StringBuffer();
		T = Integer.parseInt(BR.readLine());
		
		initHashMap();
		initDP();
		
		for(int i=0;i<T;i++) {
		
			init();
			solution();
		
		}
		System.out.println(SB);
	}
	
	static void solution() {
		
		
		//최솟값 구하기
		getMinAnswer();
		
		//최댓값 구하기
		getMaxAnswer();
		
		SB.append(ANSWER_MIN+" "+ANSWER_MAX+"\n");
	}
	
	static void getMaxAnswer() {
		if(N%2==0){
			ANSWER_MAX = convertMax(N/2);
		}
		else{
		    ANSWER_MAX = "7"+convertMax((N-3)/2);
		}
	}
	
	private static String convertMax(int n){
		String result = "";
	    for(int i=0; i<n; i++){
	        result += "1";
	    }
	    return result;
	}
	
	static void getMinAnswer() {
		makeDP();
		ANSWER_MIN = DP[N];
	}
	
	static void makeDP() {
		for(int i=9; i<=100; i++){
            for(int j=2; j<=7; j++){
                String temp = String.valueOf(DP[i-j])+String.valueOf(ARR[j-2]);
                DP[i] = Math.min(Long.parseLong(temp),DP[i]);
            }
        }
	}
	
	static void initDP() {
		DP = new long[101];
		
		Arrays.fill(DP,Long.MAX_VALUE);
		 
		DP[2]=1;
		DP[3]=7;
		DP[4]=4;
		DP[5]=2;
		DP[6]=6;
		DP[7]=8;
		DP[8]=10;
	}
	
	static void initHashMap() {
		MAP = new HashMap<>();
		
		MAP.put(1, 2);
		MAP.put(2, 5);
		MAP.put(3, 5);
		MAP.put(4, 4);
		MAP.put(5, 5);
		MAP.put(6, 6);
		MAP.put(7, 3);
		MAP.put(8, 7);
		MAP.put(9, 6);
		MAP.put(0, 6);
	}
	
	//초기 입력
	static void init() throws Exception{
		N = Integer.parseInt(BR.readLine());
	}
}