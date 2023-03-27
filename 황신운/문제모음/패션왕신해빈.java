package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 패션왕신해빈 {
	public static int N, possible;
	public static Map<String, List<String>> type;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		for(int i=0; i<TC; i++) {
			N = Integer.parseInt(br.readLine());
			possible = 1;
			type = new HashMap<>();
			for(int j=0; j<N; j++) {
				String[] tmp = br.readLine().split(" ");
				if(!type.containsKey(tmp[1])) type.put(tmp[1], new ArrayList<>());
				type.get(tmp[1]).add(tmp[0]);
			}
			solve();
			System.out.println(possible-1);
		}
	}
	
	public static void solve() {
		Set<String> key = type.keySet();
		Iterator iter = key.iterator();
		while(iter.hasNext()) {
			possible *= (type.get(iter.next()).size()+1);
		}
	}

}
