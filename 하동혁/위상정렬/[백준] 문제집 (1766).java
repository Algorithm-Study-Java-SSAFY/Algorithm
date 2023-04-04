import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br; 
    static StringBuilder sb; 

    static int N,M; 
    static HashMap<Integer, List<Integer>> linkMap; 
    static int[] inputCnt;
    
    static PriorityQueue<Integer> pq = new PriorityQueue<>(); // 조건3. 가능하면 쉬운 문제부터 풀기 
    
    public static void main(String[] args) throws IOException {
        init();
        find();

        System.out.println(sb);
    }
    
    static void find() {
    	for(int i=1; i<=N; i++) {  // 조건2. 자신을 가리키는 노드가 없다면 (먼저 풀어야할 문제가 없다면)
    		if(inputCnt[i] == 0) { 
    			pq.add(i);
    		}
    	}
    	
    	while(!pq.isEmpty()) {
    		int out = pq.remove();
    		sb.append(out + " ");
    		
    		for(int i=0; i<linkMap.get(out).size(); i++) {
        		int node= linkMap.get(out).get(i); 
//        		System.out.println("out:"+ out +"  node: "+node);
        		if(inputCnt[node]>1) {
        			inputCnt[node]--; 
        		}else {   // 조건2. 자신을 가리키는 노드가 없다면 (먼저 풀어야할 문제가 없다면)
        			pq.add(node);
        		}
    		}
    	}
    	
    	
    }
   

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        
        inputCnt = new int[N+1];
        linkMap = new HashMap<>();
        
        for(int i=1; i<=N; i++) {
        	linkMap.put(i, new ArrayList<>());
        }
        
        
        for(int i=0; i<M; i++) {
        	String[] s2 = br.readLine().split(" ");
        	int from = Integer.parseInt(s2[0]);
        	int to = Integer.parseInt(s2[1]);
        	inputCnt[to]++;
        	linkMap.get(from).add(to);
        }

        	
       
           
    }
}