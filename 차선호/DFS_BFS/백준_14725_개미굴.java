import java.util.*;
import java.io.*;


class Node{
	int depth;
	String data;
	PriorityQueue<Node> childPq = new PriorityQueue<>((o1,o2)->{
		return o1.data.compareTo(o2.data);
	});
	Node(String data, int depth){
		this.data = data;
		this.depth = depth;
	}
}

public class Main {
	
	static int N;
	static StringBuffer sb;
	static Node rootNode;
	
	public static void main(String[] args) throws Exception{
		init();
		dfs(rootNode);
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	static void dfs(Node node) {
		while(!node.childPq.isEmpty()){
			Node child = node.childPq.poll();
			String s = "";
			for(int i=0;i<child.depth-1;i++) {
				s += "--";
			}
			s+= child.data+"\n";
			sb.append(s);
			dfs(child);
		}
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rootNode = new Node("root",0);
		for(int i=0;i<N;i++) {
			Node root = rootNode;
			String[] input = br.readLine().split(" ");
			int depth = Integer.parseInt(input[0]);
			for(int j=1;j<depth+1;j++) {
				int find = 0;
				for(Node child: root.childPq) {
					if(child.data.equals(input[j])) {
						root = child;
						find = 1;
						break;
					}
				}
				if(find==0) {
					Node newChild = new Node(input[j],j);
					root.childPq.add(newChild);
					root = newChild;
				}
			}
		}
		sb = new StringBuffer();
	}
}