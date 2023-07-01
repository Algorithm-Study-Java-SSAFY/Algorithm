package 스터디.Moo게임_59042;

import java.util.*;
import java.io.*;


public class Main {
    
	/*
	 * K는 1보다 크거나 같다. 
	 */
	
	static BufferedReader br;
    static int N; 
    static int L,M,R; 
    static int Len; 
    static char answer;
    
    public static void main(String[] args) throws IOException {
    	init();
    	makeMOO();
    	if(Len-R-M+1==N) {
    		System.out.println('m');
    	}else if(Len-R>=N && Len-R-M+1<N) {
    		System.out.println('o');
    	}else {
    		find();
    	}
    	
    	System.out.println(answer);
    }
    
    static void makeMOO() {
    	while(Len < N) {
    		L = Len; 
    		R = Len; 
    		M = M+1; 
    		Len += Len + M;
    	}
   
    }

    
    static void find() {
    	if(M>4) {
        	M = M-1; 
        	L = (R-M)/2;
        	R = L; 
    	}


    	
    	
    	if(Len-R < N) {
 
        	if(M==4) { // 더이상 나눌 수 없을 때 
        		if(Len-R+1 == N) answer='m';
        		else answer = 'o';
        		return; 
        	}    		
    		find();
    		
    	}
    	else if(Len-R-M < N && N <= Len-R) {
  
    		
    		if(Len-R-M+1 == N) answer = 'm';
    		else answer ='o';
    		return; 
    	}
    	else if(Len-R-M-L < N && N <= Len-R-M){
    		
        	if(M==4) { // 더이상 나눌 수 없을 때 
        		if(Len-R-M-L+1 == N) answer='m';
        		else answer = 'o';
        		return; 
        	}    		
    		Len = Len-M-R;
    		find();
    	}
    	

    }

    

    
    static void init() throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        L = 3; 
        M = 4; 
        R = 3;
        Len = 10; 
    }
}