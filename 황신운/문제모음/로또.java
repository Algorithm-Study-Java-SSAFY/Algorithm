package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 로또 {
	public static int k;
	public static int[] S, pArr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		pArr = new int[6];
		
		while(true) {
			String[] tmp = br.readLine().split(" ");
			k = Integer.parseInt(tmp[0]);
			if(k == 0) break;
			S = new int[k];
			for(int i=1, idx=0; i<=k; i++) S[idx++] = Integer.parseInt(tmp[i]);
			getPermutation(0, 0);
			System.out.println("");
		}
	}
	
	public static void getPermutation(int start, int level) {
		if(level == 6) {
			print();
			return;
		}
		for(int i=start; i<k; i++) {
			pArr[level] = S[i];
			getPermutation(i+1, level+1);
		}
	}
	
	public static void print() {
		for(int i=0; i<6; i++) System.out.print(pArr[i] + " ");
		System.out.println("");
	}
}
