package 스터디.행복유치원_13164;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

	static int N;
	static int K;
	static int[] heights;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);		
		heights = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		solution();
	}
	
	// 센서 
	// 키 차이가 커질 때 그룹화 
	public static void solution() {
		int[] adjHeights = new int[N-1];
		for(int i = 0; i < N-1; i++) {
			adjHeights[i] = heights[i+1] - heights[i];
		}
		
		Arrays.sort(adjHeights);
		int answer = 0;
		for(int i = 0; i < N - K; i++) { // 뒤에 최대 사잇값 제거 
			answer += adjHeights[i];
		}
		System.out.println(answer);
	}
}
