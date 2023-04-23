package 스터디.좋다_1253.이분탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

	static int N;
	static ArrayList<Long> nums;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		nums = new ArrayList<>();
		String[] line = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			nums.add(Long.parseLong(line[i]));
		}
		solution();
	}

	// 2000 * (2000 * log2000) 
	public static void solution() {
		Collections.sort(nums);
		int answer = 0;
		for (int i = 0; i < N; i++) {
			long cur = nums.get(i);
			for(int j = 0; j < N; j++) {
				if(i == j) {
					continue;
				}
				long selected = nums.get(j);
				long find = cur - selected;
				if(binarySearch(i, j, find)) {
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);
	}
	
	public static boolean binarySearch(int idx1, int idx2, long find) {
		long num1 = nums.get(idx1);
		long num2 = nums.get(idx2);
		int idx = -1;
		if(idx1 > idx2) {
			nums.remove(idx1);
			nums.remove(idx2);
			idx = Collections.binarySearch(nums, find);
			nums.add(idx2, num2);
			nums.add(idx1, num1);
		} else {
			nums.remove(idx2);
			nums.remove(idx1);
			idx = Collections.binarySearch(nums, find);
			nums.add(idx1, num1);
			nums.add(idx2, num2);
		}
		return idx >= 0;
	}
}
