import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		boolean increase = true;
		boolean decrease = true;
		int answer = 0;
		int increase_count = 1;
		int decrease_count = 1;
		for (int i = 0; i < arr.length - 1; i++) {
			// 증가 상황
			if (increase) {
				if (arr[i] <= arr[i + 1]) {
					increase_count += 1;
				} else {
					increase = false;
				}
			} else {
				if (arr[i] <= arr[i + 1]) {
					increase_count = 2;
					increase = true;
				}
			}

			// 감소 상황
			if (decrease) {
				if (arr[i] >= arr[i + 1]) {
					decrease_count += 1;
				} else {
					decrease = false;
				}
			} else {
				if (arr[i] >= arr[i + 1]) {
					decrease_count = 2;
					decrease = true;
				}
			}

			// 가장 긴 길이
			if (increase_count > answer) {
				answer = increase_count;
			}
			if (decrease_count > answer) {
				answer = decrease_count;
			}
		}
		if (N == 1) {
			answer = 1;
		}

		System.out.println(answer);
	}

}
