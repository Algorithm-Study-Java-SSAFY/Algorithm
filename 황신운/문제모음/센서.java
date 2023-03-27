package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 센서 {
	public static int N, K;
	public static int[] sensorSpot;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		sensorSpot = new int[N];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			sensorSpot[i] = Integer.parseInt(str[i]);
		}
		
		Arrays.sort(sensorSpot);	// 센서들 위치 정렬	
		int answer = solve();	
		System.out.println(answer);
	}
	
	public static int solve() {
		int[] distance = getDis();	// 센서들 사이 거리 
		
		Arrays.sort(distance);	// 걸리 값을 오름차순 정렬 
		// k -> n-1-(k-1)	k로 묶으면 k-1만큼 거리 제거. 이때 가장 거리가 긴 것들 중 k-1개를 제거. 나머지 값 합 = 최소 거리 합 
		int cnt = 0;
		for(int i=0; i<N-K; i++) cnt += distance[i];
		return cnt;
	}
	
	// 센서들 사이 거리 구하기 
	public static int[] getDis() {
		int[] dis = new int[N-1];
		
		for(int i=0; i<N-1; i++) {
			dis[i] = sensorSpot[i+1] - sensorSpot[i];
		}
		return dis;
	}
}
