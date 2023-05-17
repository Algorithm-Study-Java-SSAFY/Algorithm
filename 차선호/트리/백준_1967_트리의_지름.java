import java.util.*;
import java.io.*;

class Node{
	int data;
	int parent;
	int cost;
	int cntChild;
	List<Integer> costList;
	int maxCost;
	
	Node(){
		data = 0;
		parent = 0;
		cntChild = 0;
		cost = 0;
		costList = new ArrayList<>();
		maxCost = 0;
	}
}

public class Main{
	
	static int N;
	static HashMap<Integer, Node> MAP;
	static Queue<Node> QUEUE;
	static int ANSWER;
	
	public static void main(String[] args) throws Exception {
		init();
		
		initQueue();
		
		searchQueue();
		
		System.out.println(ANSWER);
		
	}
	
	static void searchQueue() {
		while(!QUEUE.isEmpty()) {
			Node node = QUEUE.poll();
			node.maxCost = getMaxCost(node);
//			System.out.println(node.data +"--->"+ node.costList);
			ANSWER = Math.max(ANSWER, getMaxLength(node.costList));
			if(node.parent==0) continue;
			MAP.get(node.parent).costList.add(node.maxCost+node.cost);
			if(--MAP.get(node.parent).cntChild == 0) QUEUE.add(MAP.get(node.parent));
		}
	}
	
	static int getMaxLength(List<Integer> costList) {
		int result = 0;
		
		Collections.sort(costList, Collections.reverseOrder());
		
		if(costList.size()>0) result = costList.get(0);
		if(costList.size()>1) result += costList.get(1);
		
		return result;
	}
	
	static int getMaxCost(Node node) {
		int result = 0;
		
		for(int i=0;i<node.costList.size();i++) {
			if(node.costList.get(i)>result) {
				result = node.costList.get(i);
			}
		}
		
		return result;
	}
	
	static void initQueue() {
		for(int i=1;i<N+1;i++) {
			if(MAP.get(i).cntChild==0) QUEUE.add(MAP.get(i));
		}
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		MAP = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			MAP.put(i, new Node());
			MAP.get(i).data = i;
		}
		
		for(int i=0;i<N-1;i++) {
			String[] input = br.readLine().split(" ");
			int parent = Integer.parseInt(input[0]);
			int child = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);
			MAP.get(parent).cntChild++;
			MAP.get(child).cost = cost;
//			MAP.get(parent).costList.add(cost);
			MAP.get(child).parent = parent;
		}
		QUEUE = new LinkedList<>();
		ANSWER = 0;
	}
}