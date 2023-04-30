import java.util.*;
import java.io.*;



public class Main {
	
	static int N;
	static HashMap<Integer,Integer> GRAPH;
	static HashSet<Integer> UPSET, DOWNSET;
	static boolean[] VISITED;
	static int CNT;
	static StringBuffer sb;
	
	
	public static void main(String[] args) throws Exception{
		init();
		for(int i=1;i<N+1;i++) {
			if(!VISITED[i]) { //이미 확정된 애가 아니면
				search(i); 
			}
		}
		sb.append(CNT+"\n");
		for(int i=1;i<N+1;i++) {
			if(VISITED[i]) sb.append(i+"\n");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	static void search(int start) {
		boolean[] visited = new boolean[N+1]; //이번 탐색에서 방문 체크
		UPSET = new HashSet<>(); //위의 칸 숫자들
		DOWNSET = new HashSet<>(); //아래 칸 숫자들
		while(true) {
			if(visited[start]) break; //이번 탐색에서 처음 가면
			visited[start] = true; //방문처리
			UPSET.add(start); //위 숫자 집합에 추가
			DOWNSET.add(GRAPH.get(start)); //아래 숫자 집합에 추가
			start = GRAPH.get(start); //start 갱신
		}
		if(checkSame(UPSET,DOWNSET)) { //이번 탐색이 끝나면 위아래가 같은 집합인지 확인
			for(int n:UPSET) { //같다면 최종 VISITED에 넣으면서 CNT 추가
				VISITED[n] = true;
				CNT++;
			}
		}
	}
	
	//두 집합이 같은 지 확인
	static boolean checkSame(HashSet<Integer> set1, HashSet<Integer> set2) {
		if(set1.size()!=set2.size()) return false;
		else {
			for(int data:set1) {
				if(!set2.contains(data)) return false;
			}
		}
		return true;
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		GRAPH = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			int input = Integer.parseInt(br.readLine());
			GRAPH.put(i, input);
		}
		VISITED = new boolean[N+1];
		sb = new StringBuffer();
	}
}