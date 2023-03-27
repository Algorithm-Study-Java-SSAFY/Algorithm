package 스터디.센서_2212;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	
	static int N;
	static int K;
	static int[] sensors; 

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());
		sensors = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		solution();
	}
	
	// 좌표 1,000,000 이하 
	// N <= 10,000 && K <= 1000
	// 각 센서의 거리차가 클 때 다음 기지국 설립 
	public static void solution() {
		Arrays.sort(sensors);
		int[] distances = new int[N-1];
		for(int i = 0; i < N - 1; i++) {
			distances[i] = sensors[i+1] - sensors[i];
		}
		Arrays.sort(distances);
		int answer = 0;
		for(int i = 0; i < N-K; i++) {
			answer += distances[i];
		}
		System.out.println(answer);
	}
}
