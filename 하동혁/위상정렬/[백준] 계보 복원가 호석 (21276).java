
import java.util.*;
import java.io.*;

public class Main {

	static int N, M; 
	static String[] nameArr; 
	static HashMap<String, Integer> linkCnt = new HashMap<>();
	static HashMap<String, List<String>> nameMap = new HashMap<>();  //X의 조상 중에 Y가 있다 => <조상, 자손> 조상이 자손을 가리키는 것
	static List<String> rootList = new ArrayList<>(); 
	static HashMap<String, List<String>> resMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException { 
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		N = Integer.parseInt(br.readLine());
		nameArr = br.readLine().split(" ");
		M = Integer.parseInt(br.readLine());
		
		
		
		Arrays.sort(nameArr);
		
		for(int i=0; i<N; i++) {
			nameMap.put(nameArr[i], new ArrayList<>());
			linkCnt.put(nameArr[i], 0);
			resMap.put(nameArr[i], new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			String[] XY = br.readLine().split(" ");
			nameMap.get(XY[1]).add(XY[0]);
			linkCnt.put(XY[0], linkCnt.get(XY[0])+1);  
		}
		
		// 루트 노트 찾기 
		for(int i=0; i<N; i++) {
			if(linkCnt.get(nameArr[i]) == 0) {
				rootList.add(nameArr[i]);
			}
		}
		
		

		
		for(int i=0; i<rootList.size(); i++) {
			find(rootList.get(i));
		}

		
		// 출력
		sb.append(rootList.size()+"\n");
		
		Collections.sort(rootList);
		for(int i=0; i<rootList.size(); i++) {
			sb.append(rootList.get(i) + " ");
		}sb.append("\n");
		
		for(int i=0; i<resMap.size(); i++) {
			List<String> nList = resMap.get(nameArr[i]);
			Collections.sort(nList);
			
			sb.append(nameArr[i] + " " + nList.size() + " ");
			for(int j=0; j<nList.size(); j++) {
				sb.append(nList.get(j) + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}

	static void find(String root) {
		
		Deque<String> dq = new ArrayDeque<>();
		dq.add(root);
		
		while(!dq.isEmpty()) {
			String out = dq.removeFirst();
			List<String> sArr = nameMap.get(out);
			
			for(int i=0; i<sArr.size(); i++) {
				String name = sArr.get(i);
				
				linkCnt.put(name, linkCnt.get(name)-1);
				
				if(linkCnt.get(name) == 0) { // 해당 노드를 가리키는게 하나 뿐이라면 
					resMap.get(out).add(name);
					dq.add(name);
				}
				
			}
		}
		
	}
	

	

	

}












