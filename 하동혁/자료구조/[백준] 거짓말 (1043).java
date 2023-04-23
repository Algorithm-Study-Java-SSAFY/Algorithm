
import java.util.*;
import java.io.*;


public class Main {
	
	static BufferedReader br; 
	static int N,M; 
	static HashSet<Integer> avoid;  // 지민이가 피해야 할 사람들을 저장
	static List<List<Integer>> party;  // 각 파티마다 참여하는 사람들을 저장 
	static int[] visited; // 이미 지민이가 갈 수 없는 파티들을 표시 
	
	public static void main(String[] args) throws Exception {
		init();
		
		for(int i=0; i<M; i++) {
			findAvoid();	
		}
		
		int answer = 0; 
		for(int i=0; i<M; i++) {
			if(visited[i] == 0) {
				answer++; 
			}
		}
		
		System.out.println(answer);
		
	}
	
	// 지민이가 피해야할 파티를 탐색 
	static void findAvoid() {
		for(int i=0; i<M; i++) {
			boolean tf = false; 
			
			if(visited[i] == 1) continue; 
			
			// 각각의 파티마다 지민이가 피해야할 사람들이 있는지 확인 
			for(int j=0; j<party.get(i).size(); j++) { 
				int p = party.get(i).get(j);
				
				if(avoid.contains(p)) tf = true; 
			}
			
			if(tf) { // 피해야할 사람이 파티에 속해 있다면, 그 파티에 참여한 모든 인원도 피해야 함 
				avoid.addAll(party.get(i));
				visited[i] = 1; 
			}
			
		}
	}
	

		
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		
		avoid = new HashSet<>(); 
		party = new ArrayList<>();
		visited = new int[M];
		
		String[] s2 = br.readLine().split(" "); 
		for(int i=1; i<s2.length; i++) {
			avoid.add(changeInt(s2[i])); // 지민이가 피해야할 사람들을 저장 
		}
		
		// 각 파티 마다 참여자들을 List에 저장 
		for(int i=0; i<M; i++) { 
			party.add(new ArrayList<>());
			String[] s3 = br.readLine().split(" ");
			
			for(int j=1; j<s3.length; j++) {
				int p = changeInt(s3[j]);
				party.get(i).add(p);
			}
		}
		
		
	}
	
	static int changeInt(String n) {
		return Integer.parseInt(n);
	}
		
		
	

}