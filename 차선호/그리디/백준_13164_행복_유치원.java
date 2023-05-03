import java.util.*;
import java.io.*;

public class Main{
	
	static int N, K;
	static List<Integer> NUMS;
	static List<int[]> DIFF;
	static List<Integer> SLICED_IDX;
	static int ANSWER;
	
	public static void main(String[] args) throws Exception {
		init();
		
		Collections.sort(NUMS);
		makeDiff();
		
		slice();
		
		getAnswer();
		
		System.out.println(ANSWER);
	}
	
	static void getAnswer() {
		int startIdx = 0;
		for(int destIdx:SLICED_IDX) {
			ANSWER += NUMS.get(destIdx)-NUMS.get(startIdx);
			startIdx = destIdx+1;
		}
		ANSWER += NUMS.get(N-1)-NUMS.get(startIdx);
	}
	
	static void slice() {
		for(int i=0;i<K-1;i++) {
			SLICED_IDX.add(DIFF.get(i)[0]);
		}
	}
	
	static void makeDiff() {
		for(int i=0;i<N-1;i++) {
			DIFF.add(new int[] {i,NUMS.get(i+1)-NUMS.get(i)});
		}
		Collections.sort(DIFF,(o1,o2)->{
			return o2[1]-o1[1];
		});
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		N = Integer.parseInt(NK[0]);
		K = Integer.parseInt(NK[1]);
		NUMS = new ArrayList<>();
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			NUMS.add(Integer.parseInt(input[i]));
		}
		DIFF = new ArrayList<>();
		SLICED_IDX = new ArrayList<>();
		ANSWER = 0;
	}
}