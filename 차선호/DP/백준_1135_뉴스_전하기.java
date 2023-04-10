import java.util.*;
import java.io.*;


class Node{
	int parent;
	int time;
	int degree;
	int data;
	List<Integer> childTimes = new ArrayList<>();
}

public class Main {
	
	static int N;
	static int[] child;
	static HashMap<Integer,Node> map;
	static Queue<Node> queue;
	static int answer;
	
	public static void main(String[] args) throws Exception{
		init();
		solution();
		System.out.println(answer);
	}
	
	static void solution() {
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
//			System.out.println("node : "+node.data+"--> childes : "+node.childTimes);
			node.time = calculation(node);
			if(node.parent==-1) { //민식이라는 뜻
				answer = node.time;
				break;
			}
			map.get(node.parent).childTimes.add(node.time); // 부모의 childTimes에 내 time 추가
			if(--map.get(node.parent).degree==0) queue.add(map.get(node.parent)); // 부모의 degree 감소 이 때 0 되면 큐에 추가
		}
	}
	
	static int calculation(Node node) {
		int result = 0;
		if(!node.childTimes.isEmpty()) {
			//자식들 시간 내림차순 정렬
			Collections.sort(node.childTimes, Collections.reverseOrder());
			int size = node.childTimes.size();
			result += size; // 우선 자식의 수만큼 추가
			// 자식들 시간 중 가장 큰 놈이랑 (자식 수 -1)이랑 비교해서 추가
			result += (node.childTimes.get(0)-(--size)>=0) ? node.childTimes.get(0)-size : 0; 
			int pre = node.childTimes.get(0); // 
			for(int i=1;i<node.childTimes.size();i++) {
				int m = Math.max(--size, --pre); // 남은 가지수와 현재 자식들 남은 시간 중 가장 큰 놈 둘 중 누가 더 큰지(카바쳐줄라고)
				result += (node.childTimes.get(i)-m>=0) ? node.childTimes.get(i)-m : 0;
				pre = Math.max(node.childTimes.get(i),pre); 
			}
//			System.out.println("final result : "+result);
		}
		
		return result;
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		child = new int[N];
		map = new HashMap<>();
		for(int i=0;i<N;i++) {
			map.put(i, new Node());
			map.get(i).data = i;
		}
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			int parent = Integer.parseInt(input[i]);
			map.get(i).parent = parent;
			if(parent!=-1) map.get(parent).degree++; // 부모 자식 수 증가
		}
		queue = new LinkedList<>();
		for(int i=0;i<N;i++) { // 현재 말단 노드들 추가
			if(map.get(i).degree==0) queue.add(map.get(i));
		}
		answer = 0;
	}
}