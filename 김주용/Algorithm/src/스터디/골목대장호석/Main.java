package 스터디.골목대장호석;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

import org.omg.CosNaming._NamingContextExtStub;

class Point implements Comparable<Point> {
	int value;
	int cost;
	
	public Point(int value, int cost) {
		this.value = value;
		this.cost = cost;
	}

	@Override
	public int compareTo(Point o) {
		return this.cost - o.cost;
	}
		
	
}

public class Main {
	static int N;
	static int M;
	static int A;
	static int B;
	static int C; 
	
	static ArrayList<Point>[] graph;
	static boolean[] visited;
	
	static int minShame = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		A = Integer.parseInt(line[2]);
		B = Integer.parseInt(line[3]);
		C = Integer.parseInt(line[4]);
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			line = in.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			int c = Integer.parseInt(line[2]);
			graph[a].add(new Point(b, c));
			graph[b].add(new Point(a, c));
		}
		solution(A, 0, 0);
		if(minShame == Integer.MAX_VALUE) {
			minShame = -1;
		} 
		System.out.println(minShame);
	}
	
	public static void solution(int cur, int cost, int shame) {
		if(cur == B) {
			minShame = Math.min(minShame, shame);
			return;
		}
		
		for(Point next : graph[cur]) {
			if(!visited[next.value] && cost + next.cost <= C && next.cost < minShame) {
				visited[next.value] = true;
				int sh = (next.cost > shame) ? (next.cost) : shame;
				solution(next.value, cost + next.cost, sh);
				visited[next.value] = false;
			}
		}
	}
}
