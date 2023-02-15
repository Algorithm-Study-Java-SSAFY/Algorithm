import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arrN = new int[N];
		String[] strN = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			arrN[i] = Integer.parseInt(strN[i]);
		}
		Arrays.sort(arrN);
		
		int M = Integer.parseInt(br.readLine());
		int[] arrM = new int[M];
		String[] strM = br.readLine().split(" ");
		for(int i=0;i<M;i++) {
			arrM[i] = Integer.parseInt(strM[i]);
		}
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<M;j++) {
			if(binarySearch(arrN,arrM[j]) != -1) {
				sb.append(1).append('\n');
			}else {
				sb.append(0).append('\n');
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static boolean checkNum(int[] arr, int data) {
		int result = Arrays.binarySearch(arr, data);
		if (result >= 0){
			return true;
		}else {
			return false;
		}
	}
	static int binarySearch(int[] arr, int data) {
        
        int end = arr.length - 1;
        int start = 0;
        int mid = 0;
        
        while(start <= end) {
            mid = (start + end) / 2; 
    
            if(arr[mid] == data) return mid;        
            else if(arr[mid] < data) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }
}