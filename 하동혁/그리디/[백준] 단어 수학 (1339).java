package codingTest3;

import java.util.*;
import java.io.*;

/*
 * 숫자 : 0~9 
 */

public class Main {

	static int N;  
	static String[][] data; 
	static HashMap<String, Integer> hm = new HashMap<>();
	static List<String> alphabet = new ArrayList<>(); 
	static int[] num;
	static int res = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		data = new String[N][]; 
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split("");
			data[i] = s;
			for(int j=0; j<s.length; j++) {
				if(!hm.containsKey(s[j])) {
					hm.put(s[j], 0);
					alphabet.add(s[j]);
				}
				hm.put(s[j], hm.get(s[j]) + (int) Math.pow(10, s.length-j-1));
			}
		}
		
		Collections.sort(alphabet, (o1,o2)->{
			return hm.get(o2) - hm.get(o1); 
		});
		

		
		num = new int[alphabet.size()];
		int n = 9; 
		for(int i=0; i<alphabet.size(); i++) {
			num[i] = n;
			n--; 
		}
		
		calculation();
		System.out.println(res);
		
		
	}
	
	static void calculation() {
		for(int i=0; i<data.length; i++) {
			String numS = "";
			for(int j=0; j<data[i].length; j++) {
				int p = alphabet.indexOf(data[i][j]);
				numS += num[p];
			}
			res += Integer.parseInt(numS);
		}
	}


}