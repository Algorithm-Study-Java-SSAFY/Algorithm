import java.util.*;
import java.io.*;


public class Main {

	static int N,M,H;
	static List<List<Integer>> students = new ArrayList<>();
	static int[] dp;
	static int[] now;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception{
		init();
		
		for(int i=0;i<N;i++) {
			List<Integer> row = students.get(i);
			now = new int[H+1];
			for(int j=0;j<row.size();j++) {
				for(int k=0;k<H+1;k++) {
					if(dp[k]>0) {
//						System.out.println(row.get(j)+"//"+k);
						if(row.get(j)+k<=H) {
//							System.out.println(row.get(j)+"//"+k+"//"+dp[k]);
							now[row.get(j)+k] +=dp[k]%10007;
						}
					}
				}
				
			}
//			
//			System.out.println(Arrays.toString(now));
			for(int k=0;k<H+1;k++) {
				dp[k] += now[k];
			}
//			System.out.println(Arrays.toString(dp));
//			System.out.println("---------------");
		}
		
		answer = dp[H];
		
		System.out.println(answer);
	}
	
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMH = br.readLine().split(" ");
		N = Integer.parseInt(NMH[0]);
		M = Integer.parseInt(NMH[1]);
		H = Integer.parseInt(NMH[2]);
		dp = new int[H+1];
		dp[0] = 1;
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			students.add(new ArrayList<>());
			for(int j=0;j<input.length;j++) {
				students.get(i).add(Integer.parseInt(input[j]));
			}
		}
		
	}
}