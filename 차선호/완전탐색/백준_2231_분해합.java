package codeTest;

import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		int N = Integer.parseInt(s);
		
		int i =0;
		for(i=0;i<1000000;i++) {
			int cnt = String.valueOf(i).length();
			if(check(i,cnt) == N) {
				System.out.println(i);
				break;
			}
		}
		if(i == 1000001) {
			System.out.println(0);
		}
	
		
	}
	
	static int check(int data, int cnt) {
		int result=data;
		
		for(int d=0;d<cnt;d++) {
			result += (int)(data/Math.pow(10, d))%10; 
		}
		
		return result;
	}

}