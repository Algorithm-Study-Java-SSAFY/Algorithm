import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] arr = new long[n+1];
		
		if(n==0) {
			System.out.println(0);
		}else if(n==1) {
			System.out.println(1);
		}else {
			arr[1] = 1;
			
			for(int i=2; i<n+1; i++) {
				arr[i] = arr[i-1] + arr[i-2];
			}
			System.out.println(arr[n]);
		}
		
	}

}