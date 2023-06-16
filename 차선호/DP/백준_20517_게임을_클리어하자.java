import java.util.*;
import java.io.*;



public class Main{
	
	static int N,M;
	static HashMap<Integer,PriorityQueue<int[]>> MAP;
	static int[][][] DP; //현재 회차/첫 번째 선택/두 번째 선택
	static int ANSWER;
	
	
	public static void main(String[] args) throws Exception {
		init();
		
		solution();
		
		
//		for(int i=1;i<N+1;i++) {
//			System.out.println(Arrays.toString(DP[i][0]));
//			System.out.println(Arrays.toString(DP[i][1]));
//			System.out.println("------------------------");
//		}
		
		ANSWER = Math.min(DP[N][0][1], DP[N][1][1]);
		
		System.out.println(ANSWER);
		
	}
	
	
	static void solution() {
		DP[0][0][0] = -1;
		DP[0][0][0] = 0;
		DP[0][1][0] = -1;
		DP[0][1][1] = 0;
		for(int i=1;i<N+1;i++) {
			int[] data1 = MAP.get(i).poll(); //첫 번째 데이터
			int[] data2 = MAP.get(i).poll(); //두 번째 데이터
			
			int minIdx = 0; //첫 번째가 더 작다
			
			if(DP[i-1][0][1]>DP[i-1][1][1]) { //이전에 dp 중 두 번째가 더 작으면
				minIdx = 1;//두 번째가 더 작다
			}
				
			//첫 번째 선택 시
			if(DP[i-1][minIdx][0]!=data1[0]) { //i-1번째 첫 번째 선택 시와 무기 번호 안겹치면
				DP[i][0][0] = data1[0];
				DP[i][0][1] = DP[i-1][minIdx][1]+data1[1];
			}else { //겹치면
				DP[i][0][0] = data1[0];
				DP[i][0][1] = DP[i-1][(minIdx+1)%2][1]+data1[1];
			}
			
			//두 번째 선택 시
			if(DP[i-1][minIdx][0]!=data2[0]) { //i-1번째 첫 번째 선택 시와 무기 번호 안겹치면
				DP[i][1][0] = data2[0];
				DP[i][1][1] = DP[i-1][minIdx][1]+data2[1];
			}else { //겹치면
				DP[i][1][0] = data2[0];
				DP[i][1][1] = DP[i-1][(minIdx+1)%2][1]+data2[1];
			}
		}
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		MAP = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
				return o1[1]-o2[1];
			});
			String[] input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				pq.add(new int[] {j+1,Integer.parseInt(input[j])});
			}
			MAP.put(i, pq);
		}
		DP = new int[N+1][2][2];
		
		ANSWER = 0;
	}
	
}