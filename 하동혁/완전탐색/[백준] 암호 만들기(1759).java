import java.util.*;
import java.io.*;

/*
 * 암호는 최소 한개의 모음 (a,e,i,o,u)
 * 최소 두개의 자음으로 구성 
 */

public class Main {

	static int L, C; // 암호 길이  , 주어지는 문자 개수 
	static String[] data; 
	static HashSet<String> moum = new HashSet<String>(); 
//	static List<String> resList = new ArrayList<String>(); 
	
	static HashSet<String> Mset = new HashSet<>(Arrays.asList("a","e","i","o","u"));
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] LC = br.readLine().split(" ");
		L = Integer.parseInt(LC[0]);
		C = Integer.parseInt(LC[1]);
		
		data = br.readLine().split(" ");
		for(int i=0; i<C; i++) {
			if(Mset.contains(data[i])) moum.add(data[i]);
		}
		Arrays.sort(data);
		
		combination(0,"");
	}
	
	// 모음 하나 조합 나머지 자음 조합 , 모음 두개 조합 나머지 자음 조합 ..... 
	static void combination(int deep, String pw) {
		if(pw.length() == L) {
			int cnt = 0; 
			for(int i=0; i<L; i++) {
				if(moum.contains(String.valueOf(pw.charAt(i)))){
					cnt++;  
				}
			}
			// 모음이 1개 이상이며 && 모음을 제외한 자음이 최소 개수를 만족
			if(cnt >= 1 && L-cnt >= 2) { 
				System.out.println(pw);
			}
			
		}
		
		for(int i=deep; i<C; i++) {
			combination(i+1, pw+data[i]);
		}
	}
	
	


}