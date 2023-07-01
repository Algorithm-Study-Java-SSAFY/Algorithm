package 스터디.공항;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int G;
	static int P;
	static int[] gates;
 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(in.readLine());
		P = Integer.parseInt(in.readLine());
		
		gates = new int[G + 1];
		for(int i = 1; i <= G; i++) {
			gates[i] = i;
		}
		
		int answer = 0;
	
		for(int i = 1; i <= P; i++) {
			int cur = Integer.parseInt(in.readLine());
			
			int retGate = find(cur); // 도킹된 게이트 번호 
			if(retGate == 0) {
				break;
			}
			union(cur); // 다음 도킹할 번호 마킹해주기 
			
			answer++;
		}
		System.out.println(answer);
	}
	
	// 폐기: 자기 번호에서 부터 감소하면서 가능한 가장 높은 게이트 먼저 도킹 -> 10^10 = 10 억 
	// 수정: 유니온 파인드 - 도킹된 게이트에 다음 도킹 가능한 게이트 표시 
	
    public static void union(int cur) {
        cur = find(cur);
        gates[cur] = cur - 1;
    }
    
	public static int find(int cur) {
		if(cur == gates[cur]) {
			return cur;
		}
		
		return gates[cur] = find(gates[cur]);
	}

}
