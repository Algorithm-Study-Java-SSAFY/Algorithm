package 스터디.계보복원가호석_21276;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Main {

	static int N;
	static int M;
	
	static String[] curPeople;
	static Map<String, Integer> nameToNum;
	static Map<Integer, String> numToName;
	
	
	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<ArrayList<Integer>> retGraph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		curPeople = in.readLine().split(" ");
		nameToNum = new HashMap<>();
		numToName = new HashMap<>();
		
		graph = new ArrayList<>();
		retGraph = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			nameToNum.put(curPeople[i], i);
			numToName.put(i, curPeople[i]);
			graph.add(new ArrayList<Integer>());
			retGraph.add(new ArrayList<Integer>());
			
		}
		
		M = Integer.parseInt(in.readLine());
		for(int i = 0; i < M; i++) {
			String[] line = in.readLine().split(" ");
			int X = nameToNum.get(line[0]);
			int Y = nameToNum.get(line[1]);
			graph.get(X).add(Y); // X에 대한 부모 추가 
		}
		solution();
	}
	
	public static void solution() {
		ArrayList<Integer> roots = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			if(graph.get(i).size() == 0) {	// root
				roots.add(i);
			}
			
			for(int j : graph.get(i)) {
				int depthX = graph.get(i).size(); // 현재 depth
				int depthY = graph.get(j).size(); // 조상 depth
				if(depthX - 1 == depthY) {
					retGraph.get(j).add(i);
				}
			}	
		}
		print(roots);
	}
	
	public static void print(ArrayList<Integer> roots) {
		StringBuffer sb = new StringBuffer();
		sb.append(roots.size() + "\n");
		
		//System.out.println(roots.size());
		
		ArrayList<String> retRoots = new ArrayList<>();
		for(int root : roots) {
			retRoots.add(curPeople[root]);
		}
		Collections.sort(retRoots);
		for(String root : retRoots) {
			//System.out.print(root + " ");
			sb.append(root + " ");
		}
		//System.out.println(" ");
		sb.append("\n");
		
		Arrays.sort(curPeople);
		for(String name : curPeople) {
			//System.out.print(name + " ");
			sb.append(name + " ");
			ArrayList<Integer> children = retGraph.get(nameToNum.get(name));
			ArrayList<String> childrenName = new ArrayList<>();
			for(int num : children) {
				childrenName.add(numToName.get(num));
			}
			Collections.sort(childrenName);
			
			sb.append(children.size() + " ");
			//System.out.print(children.size() + " ");
			for(String cName : childrenName) {
				//System.out.print(cName + " ");
				sb.append(cName + " ");
			}
			sb.append("\n");
			//System.out.println("");
		}
		System.out.print(sb);
	}

}
