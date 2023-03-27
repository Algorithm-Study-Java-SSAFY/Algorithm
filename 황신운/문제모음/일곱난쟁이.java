package testBaek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 일곱난쟁이 {
	public static int[] dwarves, realDwarves, tmpDwarves;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dwarves = new int[9];
		realDwarves = new int[7];
		tmpDwarves = new int[7];
		for(int i=0; i<9; i++) dwarves[i] = Integer.parseInt(br.readLine());
		
		findDwarves(0,0);
		Arrays.sort(realDwarves);
		for(int i=0; i<7; i++) System.out.println(realDwarves[i]);
	}
	
	public static void findDwarves(int start, int level) {
		if(level == 7) {
			if(checkHeight()) realDwarves = tmpDwarves.clone();
			return;
		}
		
		for(int i=start; i<9; i++) {
			tmpDwarves[level] = dwarves[i];
			findDwarves(i+1, level+1);
		}
	}
	
	public static boolean checkHeight() {
		int sum = 0;
		for(int i=0; i<7; i++) sum += tmpDwarves[i];
		
		if(sum == 100) return true;
		return false;
	}
}
