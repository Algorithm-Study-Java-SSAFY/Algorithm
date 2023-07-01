package 수업.트리.사칙연산;

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

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			tree = new Node[N + 1];
			for (int i = 1; i <= N; i++) {
				String[] info = in.readLine().split(" ");
				Node node = new Node(info[1]);
				if (info.length > 2) {
					node.left = Integer.parseInt(info[2]);
					node.right = Integer.parseInt(info[3]);
				}
				tree[i] = node;
			}
			System.out.printf("#%d %d\n", test_case, (int)calculate(tree[1]));
		}

	}

	public static double calculate(Node cur) {
		int leftChild = cur.left;
		int rightChild = cur.right;
		if (leftChild == 0 && rightChild == 0) { // 리프 노드
			return Double.parseDouble(cur.data);
		}
		// 연산자 노드
		double left = calculate(tree[cur.left]);
		double right = calculate(tree[cur.right]);
		return operate(cur.data, left, right);
	}

	public static double operate(String operator, double a, double b) {
		switch (operator) {
		case "+":
			return a + b;
		case "-":
			return a - b;
		case "*":
			return a * b;
		case "/":
			return a / b;
		}
		return -1;
	}
}
