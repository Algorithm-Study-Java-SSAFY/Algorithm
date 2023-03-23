package 스터디.용액_2467;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	
	static int N;
	static long[] values;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		values = Stream.of(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		solution();
	}
	
	public static void solution() {
		long min = Integer.MAX_VALUE;
		long right = -1;
		long left = -1;
		for(int i = 0; i < N-1; i++) {
			long[] minValueIndex = find(values[i], i + 1);
			if(minValueIndex[0] < min) {
				min = minValueIndex[0];
				right = values[(int) minValueIndex[1]];
				left = values[i];
			}
		}
		System.out.println(left + " "  + right);
	}
	
	// i+1 ~ n 안에서 뽑아서 더했을 때 가장 작은 수 
	public static long[] find(long value, int left) {
		int right = N-1;
		long minSum = Integer.MAX_VALUE; // 0과 차이
		int minLeft = 0, minRight = 0;
		while(left <= right) {
			int mid = (left + right) / 2;
			long sum = values[mid] + value;
			if(Math.abs(sum) < minSum) {	// 합이 0에 가까운 경우 
				minSum = Math.abs(sum);
				minRight = mid;
			}
			if(sum < 0) {	// 합이 0보다 작으면 -> 더 큰 수를 더해야 함 -> 오른쪽 범위로 이동 
				left = mid + 1;
			} else {
				right = mid - 1;	// 0보다 크면 -> 더 작은 수를 더해야 함 -> 왼쪽 범위로 이동 
			}
	
		}

		return new long[] {minSum, minRight};
	}
	
	/*
	 * 다른 풀이!! 
	 * 1. 합이 0 이 되는 수는 절댓값을  기준으로 정렬 시 가장 인접한 위치에 존재.
	 * 2. 투 포인터 해보기 
	 */
}
