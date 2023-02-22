import java.util.*;
import java.io.*;

public class Main {
	
	static HashMap<Integer, List<Integer>> hm = new HashMap<>();
	static Deque<Integer> dq = new ArrayDeque<Integer>();
	static HashSet<Integer> visited = new HashSet<>();
	static int cnt=0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		String[] s1 = br.readLine().split(" ");
		int n = Integer.parseInt(s1[0]);
		int m = Integer.parseInt(s1[1]);
		
		for (int i=1; i<=n; i++) {
			hm.put(i, new ArrayList<>());
		}
		
		for (int i=0; i<m; i++) {
			String[] s2 = br.readLine().split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			hm.get(a).add(b);
			hm.get(b).add(a);
	
		}

		for (int i=1; i<=n; i++) {
			if(!visited.contains(i)) {
				dq.add(i);
				cnt += find();
			}
		}

		System.out.println(cnt); // 1 제외 
		
	}
	
	static int find() {
		while(!dq.isEmpty()) {
			int out = dq.removeFirst();
			
			for (int i : hm.get(out)) {
				if(!visited.contains(i)) { // 방문하지 않았다면
					dq.add(i);
					visited.add(i);	
				}
			}
			
		}

		return 1;
	}
	
}