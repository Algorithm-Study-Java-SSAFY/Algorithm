package 동전0_11047;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		int N = Integer.parseInt(line[0]), K = Integer.parseInt(line[1]);
		int[] arr = new int[N];
		for(int i = N-1; i >= 0; i--) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		int answer = 0;
		for(int i = 0; i < N; i++) {
			int num = K / arr[i];
			answer += num;
			K -= arr[i] * num;
		}
		System.out.print(answer);
	}

}
