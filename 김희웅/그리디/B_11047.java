package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_11047 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] arr = br.readLine().split(" ");
		int N = Integer.parseInt(arr[0]);
		int K = Integer.parseInt(arr[1]);
		
		int[] pay = new int[N];
		
		for (int i = 0; i < N; i++) {
			pay[i] = Integer.parseInt(br.readLine());
		}
		
		int cnt = 0;
		int index = N - 1;
		while (K != 0) {
			if (K >= pay[index]) {
				cnt += K / pay[index];
				K = K % pay[index];
			}
			index--;
		}
		System.out.println(cnt);
	}

}
