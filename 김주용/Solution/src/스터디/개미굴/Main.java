package 스터디.개미굴;

import java.io.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	String value;
	int depth;
	ArrayList<Node> childs;
	
	public Node(String value) {
		this.value = value;
		this.depth = 0;
		this.childs = new ArrayList();
	}

	@Override
	public int compareTo(Node o) {
		return this.value.compareTo(o.value);
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", depth=" + depth + "]";
	}
	
	
	
}

public class Main {

	
	static int N;
	static HashMap<String, ArrayList<String>> hashMap;
	static StringBuilder answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		answer = new StringBuilder();
		// 인접 리스트 구조로 만들기 
		Node root = new Node(null);
		for(int i = 0; i < N; i++) {
			Node curNode = root;
			String[] line = in.readLine().split(" ");
			int K = Integer.parseInt(line[0]);
			
			for(int j = 1; j <= K; j++) {
				String cur = line[j];
				boolean isNew = true;
				// 현재 노드의 자식 노드와 현재 인풋 노드 중 같은 것이 있다면 -> 넘어가고 현재 노드 변경 
				for(Node child : curNode.childs) {
					if(child.value.equals(cur)) {
						curNode = child;
						isNew = false;
						break;
					}
				}
				// 새로운 노드면 추가 
				if(isNew) {
//					System.out.println(curNode);
					Node childNode = new Node(cur);
					childNode.depth = curNode.depth + 1;
//					System.out.println(childNode);
//					System.out.println(" ");
					curNode.childs.add(childNode);
					curNode = childNode;
				}
				
			}
		}
		in.close();
		solution(root, 0);
		
	}
	// dfs 
	public static void solution(Node cur, int depth) {
		Collections.sort(cur.childs);
		for(Node child : cur.childs) {
		
	for(int i = 0; i < depth; i++) {
				System.out.print("--");
			}
			System.out.println(child.value);
			solution(child, depth + 1);
		}
	}
}