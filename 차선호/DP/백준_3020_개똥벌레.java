package code_test;


import java.util.*;
import java.io.*;


public class Main {
	
	static int N,H;
	static int[] sArr;
	static int[] jArr;
	static int[] totalArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NH = br.readLine().split(" ");
		N = Integer.parseInt(NH[0]);
		H = Integer.parseInt(NH[1]);
		sArr = new int[H+1];
		jArr = new int[H+1];
		totalArr = new int[H+1];
		
		
		for(int i=0;i<N;i++) {
			int height = Integer.parseInt(br.readLine());
			if(i%2==0) {
				sArr[height]++;
			}else {
				jArr[H-height+1]++;
			}
		}
		
		for(int i=H-1;i>0;i--) {
			sArr[i] += sArr[i+1];
		}
		for(int i=2;i<H+1;i++) {
			jArr[i] += jArr[i-1]; 
		}
		
//		System.out.println(Arrays.toString(sArr));
//		System.out.println(Arrays.toString(jArr));
		
		for(int i=0;i<H+1;i++) {
			totalArr[i] = sArr[i]+jArr[i];
		}
		
		//System.out.println(Arrays.toString(totalArr));
		int min = N;
		int cnt = 1;
		for(int i=1;i<H+1;i++) {
			if(totalArr[i] < min) {
				min = totalArr[i];
				cnt = 1;
			}else if(totalArr[i] == min) {
				cnt++;
			}
		}
		
		System.out.println(min+" "+cnt);
		
	}
	
	
}