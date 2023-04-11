import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	
	static int N; 
	static HashMap<Integer, Node> nodeMap; 
	static int[] costArr; 
	static int answer; 
	static class Node{
		int parent;
		int degree; 
		int cost; 
		List<Integer> timeList = new ArrayList<>();
		
	}
	
	public static void main(String[] args) throws IOException {

		init(); 
		start();
		System.out.println(answer);
		
	}
	
	static void start() {
		Deque<Integer> dq = new ArrayDeque<>();  // 자신을 가리키는 노드가 없는 노드들을 담는다. 
		
		for(int i=0; i<N; i++) {  // 자신을 가리키는 노드가 없는 == 리프노드부터 dq에 저장 
			if(nodeMap.get(i).degree == 0) {
				dq.add(i);
			}
		}
		
		while(!dq.isEmpty()) {
			int out = dq.removeFirst();
			
			// 자신을 가리키는 노드가 없음 -> 자식부터 자신까지 걸리는 최대 시간을 계산 
			nodeMap.get(out).cost = calculation(out);  // degree가 0일때 최대 시간 계산 
			
			int outParent = nodeMap.get(out).parent;
			if(outParent == -1) {  // 만약 민식이한테 도달했다면 stop
				answer = nodeMap.get(out).cost;
				return; 
			}
			
			nodeMap.get(outParent).timeList.add(nodeMap.get(out).cost); // 최대 시간을 부모의 timeList에 저장 
			
			if(nodeMap.get(outParent).degree > 1) { // 자신을 가리키는 자식이 있다면
				nodeMap.get(outParent).degree--; 
			}else { // 더이상 자신을 가리키는 자식이 없다면
				dq.add(outParent);
			}
			
		}
		
	}
	
	static int calculation(int nIdx) {
		List<Integer> timeList = nodeMap.get(nIdx).timeList;
		Collections.sort(timeList, Collections.reverseOrder());
		
		int maxTime = 0; 
		for(int i=0; i<timeList.size(); i++) {
			if(maxTime < timeList.get(i) + (i+1)) {
				maxTime = timeList.get(i) + i+1;
			}
		}
		
		return maxTime; 
		
	}



	static void init() throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String[] s = br.readLine().split(" ");
		
		nodeMap = new HashMap<>();
		costArr = new int[N];
		for(int i=0; i<N; i++) {
			nodeMap.put(i, new Node());
		}
		
		for(int i=0; i<N; i++) {
			int parent = Integer.parseInt(s[i]);
			if(parent != -1) {
				nodeMap.get(parent).degree++;
			}
			nodeMap.get(i).parent = parent;
		}
		
		
		
	}

}