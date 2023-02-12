import java.io.*;
import java.io.IOException;
import java.util.*;

public class Main {
	static int increase = 0;

	public static void combination(int[][] team, int[] arr, boolean[] visited, int depth, int n, int r) {
		if (r == 0) {
			for (int i = 0, j = 0; i < n; i++) {
				if(visited[i]) {
					team[increase][j] = arr[i];
					j++;
				}
			}
			increase += 1;
			return;
		}
		if (depth == n) {
			return;
		}
		
		visited[depth] = true;
		combination(team, arr, visited, depth+1, n, r-1);
		
		visited[depth] = false;
		combination(team, arr, visited, depth+1, n, r);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] S = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// team 배열에 팀 나눌 수 있는 경우의 수 저장
		int[] value = new int[N / 2];
		int index = 0;
		long team_count = N;
		for (int i = N - 1; i > N / 2; i--) {
			team_count *= i;
		}
		for (int i = 2; i <= N / 2; i++) {
			team_count /= i;
		}
		int[][] team = new int[(int)team_count][N / 2];

		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		boolean[] visited = new boolean[N];
		combination(team, arr, visited,0, N, N/2);
		int answer = 5500;

		// team 경우마다 능력치 계산
		for (int i = 0; i < team_count / 2; i++) {
			// team 설정
			boolean[] already = new boolean[N];
			for (int j = 0; j < N / 2; j++) {
				already[team[i][j]] = true;
			}

			int[] start_team = new int[N / 2];
			int[] link_team = new int[N / 2];
			int s_count = 0;
			int l_count = 0;
			for (int j = 0; j < already.length; j++) {
				if (already[j]) {
					start_team[s_count] = j;
					s_count++;
				} else {
					link_team[l_count] = j;
					l_count++;
				}
			}

			// 능력치 계산
			int start_team_strong = 0;
			int link_team_strong = 0;
			for (int x = 0; x < N / 2; x++) {
				for (int y = x + 1; y < N / 2; y++) {
					start_team_strong += S[start_team[x]][start_team[y]];
					start_team_strong += S[start_team[y]][start_team[x]];
					link_team_strong += S[link_team[x]][link_team[y]];
					link_team_strong += S[link_team[y]][link_team[x]];
				}
			}
			int temp = 0;

			if (start_team_strong > link_team_strong) {
				temp = start_team_strong - link_team_strong;
			} else {
				temp = link_team_strong - start_team_strong;
			}

			if (temp < answer) {
				answer = temp;
			}

		}
		System.out.println(answer);

	}
}
