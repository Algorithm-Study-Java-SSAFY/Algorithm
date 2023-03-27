package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class 바이러스 {
	public static int N;
	public static boolean[] visited;
	public static Map<Integer, List<Integer>> path;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		path = new HashMap<>();
		
		for(int i=0; i<M; i++) {
			String[] tmp = br.readLine().split(" ");
			int from = Integer.parseInt(tmp[0]);
			int to = Integer.parseInt(tmp[1]);
			if(!path.containsKey(from)) path.put(from, new ArrayList<>());
			path.get(from).add(to);
			if(!path.containsKey(to)) path.put(to, new ArrayList<>());
			path.get(to).add(from);
		}
		
		visited[1] = true;
		int answer = getSpread(1);
		System.out.println(answer-1);	// 1번 빼고 
	}
	
	public static int getSpread(int start) {
		int cnt = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		while(!q.isEmpty()) {
			int comp = q.poll();
			cnt++;
			for(int i=0; i<path.get(comp).size(); i++) {
				if(!visited[path.get(comp).get(i)]) {
					q.offer(path.get(comp).get(i));
					visited[path.get(comp).get(i)] = true;
				}
			}
		}
		return cnt;
	}
}
