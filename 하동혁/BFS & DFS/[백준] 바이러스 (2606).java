import java.util.*;
import java.io.*;

public class Main {
	
	static HashMap<Integer, List<Integer>> hm = new HashMap<>();
	static Deque<Integer> dq = new ArrayDeque<Integer>();
	static HashSet<Integer> visited = new HashSet<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int t = Integer.parseInt(br.readLine());
		

		for (int i=0; i<t; i++) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			
			if(!hm.containsKey(a)) {
				hm.put(a, new ArrayList<>());
			}
			hm.get(a).add(b);
			if(!hm.containsKey(b)) {
				hm.put(b, new ArrayList<>());
			}
			hm.get(b).add(a);
	
		}
		
//		System.out.println(hm);
		visited.add(1);
		dq.add(1);
		while(!dq.isEmpty()) {
			int out = dq.removeFirst();
			
			for (int i : hm.get(out)) {
				if(!visited.contains(i)) { // 방문하지 않았다면
					dq.add(i);
					visited.add(i);	
				}
			}
			
		}
		
		System.out.println(visited.size()-1); // 1 제외 
		
	}
	
}