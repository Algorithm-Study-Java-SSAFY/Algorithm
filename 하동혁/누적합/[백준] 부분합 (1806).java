import java.util.*;
import java.io.*;

public class Main {

	static int N, S; // 길이, 합
	static int[] data;
	static int minLen = Integer.MAX_VALUE;
	static int start = 0;
	static int end = 0;
	static int sum = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		S = Integer.parseInt(s[1]);

		data = new int[N + 1];
		String[] s2 = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(s2[i]);
		}

		while (start <= N && end <= N) {
			if (sum >= S && minLen > end - start) {
				minLen = end - start;
			}

			if (sum < S)
				sum += data[end++]; 
			else
				sum -= data[start++];
		}

		if (minLen == Integer.MAX_VALUE)
			System.out.println("0");
		else
			System.out.println(minLen);

	}

}