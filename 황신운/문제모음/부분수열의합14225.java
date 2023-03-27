package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class 부분수열의합14225 {
	public static int N;
	public static int[] S;
	public static Set<Integer> sumList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		sumList = new TreeSet<>();
		
		String[] tmp = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			S[i] = Integer.parseInt(tmp[i]);
		}
		
		solve();
	}
	public static void solve() {
		getSumList();
		int answer = findMinNum();
		System.out.println(answer);
	}
	
	public static void getSumList() {
		for(int i=1; i< (1<<N); i++) {
			int sum = 0;
			for(int j=0; j<N; j++) {
				if((i&(1<<j)) != 0) sum += S[j];
			}
			sumList.add(sum);
		}
	}
	
	public static int findMinNum() {
		int num = 1;
		Iterator iter = sumList.iterator();
		while(iter.hasNext()) {
			if(num != (int)iter.next()) return num;
			num++;
		}
		return num;
	}
}
