import java.util.*;
import java.io.*;

public class Main{
	
	static int N;
	static HashMap<Integer, Integer> MAP;
	static HashSet<Integer> SET;
	static List<Integer> NUMS;
	static int ANSWER;
	
	public static void main(String[] args) throws Exception {
		init();
		if(NUMS.get(0)!=1) System.out.println(1);
		else {
			solution();
			System.out.println(ANSWER);
		}
	}
	
	static void solution() {
		int max = 1;
		for(int i=1;i<N;i++) {
			if(NUMS.get(i)<=max+1) {
				max += NUMS.get(i);
//				System.out.println(max);
			}else {
//				System.out.println("break : "+(max+1));
				ANSWER = max+1;
				return;
			}
		}
		ANSWER = max+1;
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" "); 
		NUMS = new ArrayList<>();
		for(int i=0;i<N;i++) {
			NUMS.add(Integer.parseInt(input[i]));
		}
		Collections.sort(NUMS);
		ANSWER = 0;
	}
}