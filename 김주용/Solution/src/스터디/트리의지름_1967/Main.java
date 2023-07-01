package 스터디.트리의지름_1967;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


class Node {
	int num;
	int weight;

	public Node(int num, int weight) {
		this.num = num;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Node [num=" + num + ", weight=" + weight + "]";
	}
	
	
}

public class Main {

	static int N;
	static ArrayList<Node>[] graph;
	static boolean[] visited;
	static boolean[] leaf;
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			String[] line = in.readLine().split(" ");
			int parent = Integer.parseInt(line[0]);
			int child = Integer.parseInt(line[1]);
			int weight = Integer.parseInt(line[2]);
			graph[parent].add(new Node(child, weight));
			graph[child].add(new Node(parent, weight));
		}
	
		solution();
	}
	
	public static void solution() {
		Node maxNode = bfs(1); // 임의의 노드에서 가장 먼 노드 
		//System.out.println(maxNode);
		Node ret = bfs(maxNode.num);
		//System.out.println(ret);
		
		System.out.println(ret.weight);
	}

	/*
	 * 폐기 : 리프노드에서 부터 높이를 다 구한다음 [공통 부모 찾아서 결과값 찾기]
	 * 수정 : 
	 * 임의 노드에서 가장 멀리 떨어진 노드는 지름을 구성하는 노드 1 
	 * 가장 멀리 떨어진 노드에서 가장 멀리 있는 노드가 지름을 구성하는 노드 2 
	 */
	
	public static Node bfs(int start) {
		Node retNode = new Node(-1, -1);
		
		visited = new boolean[N+1];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(start, 0));
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(retNode.weight < cur.weight) {
				retNode = cur;
			}
			
			for(Node next : graph[cur.num]) {
				if(!visited[next.num]) {
					visited[next.num] = true;
					queue.add(new Node(next.num, cur.weight + next.weight));
				}
			}
			
		}
		
		return retNode;
		
	}

}
