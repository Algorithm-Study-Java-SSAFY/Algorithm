package code_test;


import java.util.*;
import java.io.*;


public class Main {
	
	static StringBuffer sb = new StringBuffer();
	static int L,C;
	static char[] charArr;
	static List<String> password = new ArrayList<>();
	static HashSet<Character> mSet = new HashSet<>(Arrays.asList('a','e','i','o','u'));
	public static void main(String[] args) throws Exception {
		init();
		
		Arrays.sort(charArr); //a,b,c 순으로 정렬
		
		char[] arr = new char[L];
		combination(arr,0,0,0,0);
		
		System.out.println(sb);
		
	}
	
	static void combination(char[] arr, int start, int depth, int mCnt, int jCnt) {
		if(depth==L) {
			if(mCnt>=1 && jCnt>=2) { //모음 1개 이상, 자음 2개 이상이면
				sb.append(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "")+"\n");
			}
			return;
		}
		
		for(int i=start;i<C;i++) {
			arr[depth] = charArr[i];
			if(mSet.contains(charArr[i])) combination(arr,i+1,depth+1,mCnt+1,jCnt);
			else combination(arr,i+1,depth+1,mCnt,jCnt+1);
		}
		
	}
	
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] LC = br.readLine().split(" ");
		L = Integer.parseInt(LC[0]);
		C = Integer.parseInt(LC[1]);
		charArr = new char[C];
		String[] input = br.readLine().split(" ");
		for(int i=0;i<C;i++) {
			charArr[i] = input[i].charAt(0);
		}
		
	}
}