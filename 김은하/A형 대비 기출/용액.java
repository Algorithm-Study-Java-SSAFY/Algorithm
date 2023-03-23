import java.io.*;
import java.util.*;

public class Main {
	static int[] solution;
	static int minValue = Integer.MAX_VALUE;
	static int[] minSolution = new int[2];
	static int plusStart;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		solution = new int[N];
		plusStart = N+1;
		
		st = new StringTokenizer(br.readLine());
		int v = 0;
		boolean plus = false;
		for (int i = 0; i < N; i++) {
			v = Integer.parseInt(st.nextToken());
			solution[i] = v;
			if (!plus && v >= 0) {
				plusStart = i;
				plus = true;
			}
		}

		findMinSolution();
		bw.write(minSolution[0] + " " + minSolution[1]);

		br.close();
		bw.close();
	}

	public static void findMinSolution() {
		int start = 0;
		int end = solution.length-1;
		int value = 0;
		
		while (start < end) {
			value = solution[start] + solution[end];
			updateMin(Math.abs(value), solution[start], solution[end]);
			if (value > 0) {
				end--;
			} else if (value < 0) {
				start++;
			} else {
				return;
			}
		}
	}
	
	public static void updateMin(int tempMin, int sol1, int sol2) {
		if (tempMin < minValue) {
			minValue = tempMin;
			minSolution[0] = sol1;
			minSolution[1] = sol2;
		}
	}
}
