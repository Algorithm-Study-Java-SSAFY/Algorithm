import java.util.*;
import java.io.*;



public class Main {
	
	static int N;
	static long B,ANSWER;
	static int[] ARR;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(ANSWER);
	}
	
	static void solution() {
		
		binarySearch();
		
	}
	
	static void binarySearch() {
		long start = 0;
		long end = (long) Math.pow(10, 18);
		
		while(start<=end) {
			long mid = (start+end)/2;
			
//			System.out.println(mid +"--->"+check(mid));
			
			if(check(mid)) { //가능하다면 -> 가치 더 올려봐라
				start = mid+1;
				ANSWER = Math.max(ANSWER, mid);
			}else { //불가능하면 -> 가치 내려라
				end = mid-1;
			}
		}
	}
	
	static boolean check(long value) {
		
		long sum = 0;
		
		for(int i=0;i<N;i++) {
			if(ARR[i]<value) {
				sum += Math.pow(value-ARR[i], 2);
			}
		}
		
		if(sum<=B) return true;
		else return false;
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NB = br.readLine().split(" ");
		N = Integer.parseInt(NB[0]);
		B = Long.parseLong(NB[1]);
		
		ARR = new int[N];
		String[] arr = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			ARR[i] = Integer.parseInt(arr[i]);
		}
		
		
		ANSWER = 0;
		
	}
}