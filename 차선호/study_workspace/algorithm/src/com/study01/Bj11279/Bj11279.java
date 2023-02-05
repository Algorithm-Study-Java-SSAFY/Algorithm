package com.study01.Bj11279;

import java.util.*;
import java.io.*;

public class Bj11279 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		PriorityQueue maxHip = new PriorityQueue(Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<count;i++) {
			int op = Integer.parseInt(br.readLine());
			if (op == 0) {
//				if (maxHip.size()==0)System.out.println(0);
//				else System.out.println(maxHip.poll());
				sb.append(maxHip.size() == 0 ? 0 : maxHip.poll());
			}else {
				maxHip.add(op);
			}
			
			System.out.println(sb.toString());
		}
	}
}
