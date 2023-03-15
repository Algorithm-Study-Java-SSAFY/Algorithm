package 스터디.부분합_1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	
	static int N;
	static int S;
	
	static int[] nums;
	static int M;
	
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		S = line[1];
		
		nums = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		M = nums.length;
		
		solution();
	}
	
	public static void solution() {
		// 누적합 구하기
		int[] sums = new int[M];
		sums[0] = nums[0];
		for(int i = 1; i < M; i++) {
			sums[i] = sums[i-1] + nums[i];
		}
		// 계산 
		//System.out.println(Arrays.toString(sums));
		int window = 1;
		
		loop:
		while(window < M) {
			for(int right = M-1; right >= window; right--) {
				int left = right - window;
				System.out.println(left + " " + right);
				if(left < 0) { // 맨 앞 까지 
					left = 0;
				}
				int partSum = sums[right] - sums[left];
				System.out.println(partSum);
				if (partSum >= S) {
					answer = window;
					break loop;
				}
			}
			
			window++;
		}
	
		System.out.println(answer);
	}
}
