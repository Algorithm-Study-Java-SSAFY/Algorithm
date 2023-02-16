package 수열_2491;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int acend = 1, decend = 1;
		int[] acendDp = new int[N+1];
		int[] decendDp = new int[N+1];
		acendDp[0] = 1;
		decendDp[0] = 1;
		for(int i = 1; i < N; i++) {
			if(line[i] >= line[i-1]) {
				acendDp[i] = acendDp[i-1] + 1;
				acend = Math.max(acend, acendDp[i]);
			} else {
				acendDp[i] = 1;
			}
			if(line[i] <= line[i-1]) {
				decendDp[i] = decendDp[i-1] + 1;
				decend = Math.max(decend, decendDp[i]);
			} else {
				decendDp[i] = 1;
			}
		}
		System.out.print(Math.max(acend, decend));
	}

}
