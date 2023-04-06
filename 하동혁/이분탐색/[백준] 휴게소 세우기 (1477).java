import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br; 
    static int N, M, L;  
    static List<Integer> restArea;
    static int range; 
    static int answer = Integer.MAX_VALUE; 
    
    public static void main(String[] args) throws IOException {
        init();
        binarySearch();
        
        System.out.println(answer);
    }
    

    static void binarySearch() {
    	int start = 1; 
    	int end = range; 
    	int mid = 0; 
    	
    	while(start <= end) {
    		mid = (start+end)/2;
    		
    		int m = compareDis(mid); 
    		
//    		System.out.println("mid: "+mid +" / m: "+ m +" /start:"+start +" /end: "+end);
    		
    		if(m>M) {  // 휴개소 최대 설치 개수를 넘어서는 경우 -> 거리가 좁기 떄문에 거리를 늘려서 다시 탐색 
     			start = mid+1;
    		}
    		else if(m<=M) { // 휴개서 최대 설치 개수보다 적은 경우 -> 거리가 넓기 때문에 거리를 좁혀서 다시 탐색
    			end = mid-1; 
    			answer = mid; 
    		}

    	}
    }
    
    static int compareDis(int mid) {
    	int cnt=0; 
    	
    	for(int i=0; i<N+1; i++) {
    		
    		int dis = restArea.get(i+1) - restArea.get(i);

    		while(dis>mid) {
    			cnt++;
    			dis -=mid;
    		}

    	}
    	
    	return cnt; 
    }

        
    
    
    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        L = Integer.parseInt(s[2]);
        
        range = L*2; 
        restArea = new ArrayList<>(); 
        restArea.add(0);
        
        String[] s2= br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            restArea.add(Integer.parseInt(s2[i]));
        }
        restArea.add(L);
        Collections.sort(restArea);
//        System.out.println(restArea);
        
        
        
    }

}