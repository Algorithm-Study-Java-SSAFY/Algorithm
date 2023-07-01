package 스터디.거짓말;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N;
	static int M;
	
	static Queue<Integer> knowTruePeople;
	static int[][] partyPeople;
	static int[][] graph;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		line = in.readLine().split(" ");
		knowTruePeople = new LinkedList<Integer>();
		visited = new boolean[51];
	
		for(int i = 0; i < Integer.parseInt(line[0]); i++) {
			knowTruePeople.add(Integer.parseInt(line[i+1]));
			visited[Integer.parseInt(line[i+1])] = true;
		}
		
		partyPeople = new int[M][];
		graph = new int[51][51];
		for(int i = 0; i < M; i++) {
			line = in.readLine().split(" ");
			partyPeople[i] = new int[Integer.parseInt(line[0])];
			for(int j = 1; j <= Integer.parseInt(line[0]); j++) {
				partyPeople[i][j-1] = Integer.parseInt(line[j]); 
				for(int k = j + 1; k <= Integer.parseInt(line[0]); k++) {
					graph[Integer.parseInt(line[j])][Integer.parseInt(line[k])] = 1;
					graph[Integer.parseInt(line[k])][Integer.parseInt(line[j])] = 1;
					
				}
			}
			
		}
		solution();
	}
	
	public static void solution() {
		int answer = 0;
		while(!knowTruePeople.isEmpty()) {
			int cur = knowTruePeople.poll();
			
			for(int i = 1; i <= N; i++) {
				if(!visited[i] && graph[cur][i] == 1) {
					knowTruePeople.add(i);
					visited[i] = true;
				}
			}
		}
		
		for(int i = 0; i < M; i++) {
			if(isCan(i)) {
				answer++;
			}
		}
		System.out.println(answer);
	}
	
	public static boolean isCan(int cur) {
		for(int i = 0; i < partyPeople[cur].length; i++) {
			if(visited[partyPeople[cur][i]]) {
				return false;
			}
		}
		return true;
	}
}
