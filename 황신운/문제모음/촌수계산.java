package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class 촌수계산 {
	public static int N, M, p1, p2;
	public static Map<Integer, List<Integer>> relation;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		String[] tmp = br.readLine().split(" ");
		p1 = Integer.parseInt(tmp[0]);
		p2 = Integer.parseInt(tmp[1]);
		
		M = Integer.parseInt(br.readLine());
		relation = new HashMap<>();
		for(int i=0; i<M; i++) {
			tmp = br.readLine().split(" ");
			int from = Integer.parseInt(tmp[0]);
			int to = Integer.parseInt(tmp[1]);
			
			if(!relation.containsKey(from)) relation.put(from, new ArrayList<>());
			relation.get(from).add(to);
			if(!relation.containsKey(to)) relation.put(to, new ArrayList<>());
			relation.get(to).add(from);
		}
		
		int answer = getRelation(p1);
		System.out.println(answer);
	}
	
	public static int getRelation(int start) {
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] {start, 0});

		while(!q.isEmpty()) {
			Integer[] info = q.poll();
			int p = info[0];
			int degree = info[1];
			
			if(p == p2) return degree;
			for(int i=0; i<relation.get(p).size(); i++) {
				int nextP = relation.get(p).get(i);
				if(!visited[nextP]) {
					visited[nextP] = true;
					q.offer(new Integer[] {nextP, degree+1});
				}
			}
		}
		
		return -1;
	}
}
