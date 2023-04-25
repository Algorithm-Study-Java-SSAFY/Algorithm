import java.util.*; 
import java.io.*;


public class Main {
    static BufferedReader br;
    static int N;
    static int[] numArr;
    static int[] used; 
    static int[] visit; 
    static int end; 
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        find();
        
        Collections.sort(answer);
        
        System.out.println(answer.size());
        for(int i=0; i<answer.size(); i++) {
        	System.out.println(answer.get(i));
        }
        
    }
    
    /*
     * A : 1 2 3 4 5 6 7
     * B : 3 1 1 5 5 4 6  
     */
    static void find() {
    	
    	System.out.println(Arrays.toString(numArr));
    	
    	for(int i=1; i<=N; i++) { // A == B 인 상황을 만나면 결국 자기 자신으로 돌아오지 못한다. 
    		if(numArr[i] == i) {
    			used[i] = 1; 
    			answer.add(numArr[i]);
    		}
    	}
    	
    	for(int i=1; i<=N; i++) { // A
    		if(used[i] == 0) {
    			end = numArr[i]; 
    	    visit[i] = 1;
    			Tracking(i);
    			visit[i] = 0; 
    		}
    	}
    	
    }
    
    static void Tracking(int from) {
    	System.out.println(from+" "+Arrays.toString(visit));
    	
    	
    	if(end == from) {
    		System.out.println("good  " + Arrays.toString(visit));
    		for(int i=1; i<=N; i++) {
    			if(visit[i] == 1) {
        			used[i] = 1; 
        			answer.add(i);	
    			}
    		}
    		return; 
    	}
    	
    	
    	int cnt = 0;
    	for(int i=1; i<=N; i++) { // B 
    		if(used[i]==0 && from==numArr[i]) {
    			visit[i] = 1;
    			Tracking(i);
    			visit[i] = 0; 
    			cnt++;
    		}
    	}
    	
    	if(cnt == 0) {
    		System.out.println("cut  "+Arrays.toString(visit));
    		return; 
    	}
    }
    
    
    static void init() throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        numArr = new int[N+1];
        used = new int[N+1];
        visit = new int[N+1];
        for(int i=1; i<=N; i++) {
        	numArr[i] = Integer.parseInt(br.readLine()); 
        }
    }
    

}