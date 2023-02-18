import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Main {

	static int n;
	static int m;
	static StringBuffer sb;
	static int[] arr;
	static int[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		
		visited = new int[n + 1];
		arr = new int[m];

		find(0); // arr 에 추가할 index 0번부터 시작..
		System.out.println(sb);
	}

	static void find(int k) {

		if (k == m) {
			for (int i : arr) {
				sb.append(i + " ");
			}
			sb.append("\n");
			return ;
		}
	
		for (int i = 1; i <= n; i++) {
			if (visited[i] == 0) { // 아직 방문하지 않았다면..
				visited[i] = 1;
				arr[k] = i;
				find(k+1);
				
				visited[i] = 0;
			}
		}

	}

}