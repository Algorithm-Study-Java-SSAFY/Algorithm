import java.util.*;
import java.io.*;


public class Main {

	static int N,M;
	static String[] peoples;
	static int[] depth;
	static HashMap<String, Integer> nameMap = new HashMap<>();
	static HashMap<Integer, List<Integer>> map = new HashMap<>();
	static List<String> result = new ArrayList<>();
	static StringBuffer sb = new StringBuffer();
	static Queue<Integer> primes = new LinkedList<>();
	
	
	public static void main(String[] args) throws Exception{
		init();
		
		getPrimes();
		
		search();
		
		Collections.sort(result);
		for(String s:result) {
			sb.append(s+"\n");
		}
		System.out.println(sb);
		
	}
	
	
	static void search() {
		while(!primes.isEmpty()){
			int parent = primes.poll();
			String childs = "";
			int cntChild = 0;
			for(int child:map.get(parent)) {
				depth[child] --;
				
				if(depth[child]==0) { //이때 parent가 child의 부모
					if(cntChild == 0) childs += peoples[child];
					else childs += " "+peoples[child];
					cntChild++;
					primes.add(child);
				}
			}
			result.add(peoples[parent]+" "+cntChild+" "+childs);
		}
	}
	
	//대빵 조상 찾기
	static void getPrimes() {
		String prime = "";
		int cntPrime = 0;
		for(int i=0;i<N;i++) {
			if(depth[i]==0) {
				primes.add(i);
				if(cntPrime==0) prime += peoples[i];
				else prime += " "+peoples[i];
				cntPrime++;
			}
		}
		sb.append(cntPrime+"\n");
		sb.append(prime+"\n");
	}
	
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		peoples = new String[N];
		depth = new int[N];
		String[] inputPeoples = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			peoples[i] = inputPeoples[i];
		}
		
		//사람들 알파벳순으로 오름차순해서 인덱스 매기기
		Arrays.sort(peoples);
		for(int i=0;i<N;i++) {
			nameMap.put(peoples[i], i);
			map.put(i, new ArrayList<>());
		}
		
		//매핑 정보 
		M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int child = nameMap.get(input[0]);
			int parent = nameMap.get(input[1]);
			depth[child]++; //자식 레벨 증가
			map.get(parent).add(child);
		}
		// 자식들도 미리 오름차순 정렬한다.
		for(int i=0;i<N;i++) {
			Collections.sort(map.get(i));
		}
		
	}
}