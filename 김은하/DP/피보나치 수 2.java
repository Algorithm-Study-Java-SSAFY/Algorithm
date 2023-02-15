import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] F = new long[n+1];
		
		for (int i = 0; i < F.length; i++) {
			if (i == 0) {
				F[i] = 0; 
			} else if (i == 1) {
				F[i] = 1;
			} else {
				F[i] = F[i-1] + F[i-2];
			}
		}
		
		System.out.println(F[n]);
	}
}
