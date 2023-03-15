package 백준.백트래킹.N으로만들기_17255;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	
	static String N;
	static char[] number;
	static int answer;
	
	static HashSet<String> set = new HashSet<String>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = in.readLine();
		number = N.toCharArray();
		
		solution();
	}

	public static void solution() {
		
		for (int i = 0; i < number.length; i++) {
			boolean[] visited = new boolean[N.length()];
			visited[i] = true;
			dfs(visited, String.valueOf(number[i]), String.valueOf(number[i]));
			visited[i] = false;
		}
		System.out.println(answer);

	}
	
	public static void dfs(boolean[] visited, String curNum, String path) {
		//System.out.println(curNum);
		if(curNum.length() == N.length()) {
			if(curNum.equals(N) && !set.contains(path)) {
				//System.out.println(path);
				set.add(path);
				answer++;
			}
			return;
		}
		
		//System.out.println(curNum);
		for (int i = 0; i < number.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(visited, curNum + number[i], path + curNum + number[i]);
				visited[i] = false;
				
				visited[i] = true;
				dfs(visited, number[i] + curNum, path + number[i] + curNum);
				visited[i] = false;
			}
		}
	}
}
