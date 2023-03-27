package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 계보복원가호석 {
	public static int N;
	public static Map<String, Integer> name, parent;
	public static Map<String, List<String>> children;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		name = new HashMap<>();
		parent = new HashMap<>();
		children = new HashMap<>();
		N = Integer.parseInt(br.readLine());
		int idx = 1;
		String[] tmp = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			name.put(tmp[i], idx);
			parent.put(tmp[i], 0);
			children.put(tmp[i], new ArrayList<>());
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			tmp = br.readLine().split(" ");
			parent.put(tmp[0], name.get(tmp[1]));
		}
		
		solve();
		
	}
	
	public static void solve() {
		Set<String> key = name.keySet();
		for(int i=0; i<key.size(); i++) {
			
		}
		
	}
	
	public static void getParent(String node) {
		
	}

}
