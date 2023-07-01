package 수업.트리.중위순회;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Node {
	String data;
	int left;
	int right;

	public Node(String data) {
		this.data = data;
		this.left = 0;
		this.right = 0;
	}
}

public class Solution {
	static Node[] tree;
	static boolean[] visited; 
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			tree = new Node[N + 1];
			visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				String[] info = in.readLine().split(" ");
				Node node = new Node(info[1]);
				if (info.length > 2) {
					node.left = Integer.parseInt(info[2]);
				}
				if (info.length > 3) {
					node.right = Integer.parseInt(info[3]);
				}
				tree[i] = node;
			}
			System.out.printf("#%d ", test_case);
			inorder(1);
			System.out.println(" ");
		}
	}
	
	public static void inorder(int idx) {
		Node cur = tree[idx];
		if(cur == null) {
			return;
		}
		inorder(cur.left);
		System.out.print(cur.data);
		inorder(cur.right);
	}
}
