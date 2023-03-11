import java.util.*;
import java.io.*;



public class Main {

	static int N,M; 
	static HashMap<Integer,List<Integer>> hm = new HashMap<>();
	static List<Integer> route = new ArrayList<Integer>();
	static HashSet<Integer> linked = new HashSet<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) hm.put(i, new ArrayList<>());
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				int node = Integer.parseInt(s[j]);
				if(node == 1) {
					hm.get(i).add(j);
				}
				
			}
		}
		
		String[] r = br.readLine().split(" ");
		for(int i=0; i<r.length; i++) {
			route.add(Integer.parseInt(r[i])-1);
		}
		


		
		// 시작점을 기준으로 연결된 모든 노드를 찾는다.
		findLinked();
		

		boolean tf = checkRoute();
		if(tf) System.out.println("YES");
		else System.out.println("NO");
		
	}
	
	// 경로 노드 하나씩 연결노드Set에 포함되어 있는지 확인한다. 
	static boolean checkRoute() {
		for(int i=0; i<route.size(); i++) {
			if(!linked.contains(route.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	// 시작점으로 부터 연결된 모든 노드를 찾는다.
	static void findLinked() {
		Deque<Integer> dq = new ArrayDeque<Integer>();
		dq.add(route.get(0)); 
		linked.add(route.get(0)); // 첫 번째 방문 도시 
		
		while(!dq.isEmpty()) {
			int out = dq.removeFirst();
			for(int i=0; i<hm.get(out).size(); i++) {
				int p = hm.get(out).get(i);
				if(!linked.contains(p)) {
					linked.add(p);
					dq.add(p);
				}
			}
		}
	}


}