package 스터디.숫자고르기_2668;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


public class Main {
	
	static int N;
	static int[] nums;
	static boolean[][] graph;
	static boolean[] visited;
	
	static int sameSize = 0;
	static ArrayList<Integer> same = new ArrayList<>();
	
	static int answerSize = 0;
	static ArrayList<Integer> answer = new ArrayList<>();
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		nums = new int[N + 1];
		graph = new boolean[N+1][N+1];
		visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(in.readLine());
		}
		
		solution();
	}
	
	public static void solution() {
		
		for(int i = 1; i <= N; i++) {
			visited[i] = true;
			dfs(i, i);
			visited[i] = false;
		}
		
		Collections.sort(answer);
		System.out.println(answer.size());
		for(int cur : answer) {
			System.out.println(cur);
		}
	}
	
	// 사이클이 완성되면 맞는 숫자 
	public static void dfs(int start, int cur) {
		if(start == nums[cur]) {
			answer.add(start);
			return;
		}
		
		if(!visited[nums[cur]]) {
			visited[nums[cur]] = true;
			dfs(start, nums[cur]);
			visited[nums[cur]] = false;
		}
	}
}
