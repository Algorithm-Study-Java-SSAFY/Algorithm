import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static int N, K; 
	static int[] Arr; 
	static PriorityQueue<int[]> pq; // [위치, 차이값]
	static PriorityQueue<Integer> splitPoint;
	static int answer; 

    public static void main(String[] args) throws IOException {
    	init();
    	findPoint();
    	findDistance(); 
    	System.out.println(answer);
    }
    
    static void findDistance() {
    	int bePoint = 0; 
    	while(!splitPoint.isEmpty()) {
    		int point = splitPoint.poll();
    		answer += ( Arr[point] - Arr[bePoint] );
    		bePoint = point+1; 
    	}
    	
    	answer += ( Arr[N-1] - Arr[bePoint] );
    }
    
    static void findPoint() {
    	for(int i=0; i<K-1; i++) {
    		int[] point = pq.poll();
    		splitPoint.add(point[0]);
    	}

    }


    static void init() throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);

        
        Arr = new int[N];
        pq = new PriorityQueue<>((o1,o2)->{ // 차이 값을 내림차순으로 정렬해서 차이가 큰 것 부터 쪼개기 
        	return o2[1]-o1[1];
        });
        
        splitPoint = new PriorityQueue<>((o1,o2)->{
        	return o1-o2;
        }); 
        
        
        String[] s2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
        	Arr[i] = Integer.parseInt(s2[i]);
        }
        
        for(int i=0; i<N-1; i++) {
        	pq.add(new int[] {i, (Arr[i+1]-Arr[i])});
        }

        
    }
    
    

    

        
}