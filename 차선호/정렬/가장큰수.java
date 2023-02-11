package code_test;

import java.io.*;
import java.util.*;

public class Solution {
	
	
	public static void main(String[] args) throws Exception {
		//int[] numbers = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int[] numbers = {383,38};
		
		// 문자열 타입으로 바꿔서 저장
		String[] strArr = new String[numbers.length];
		for(int i=0;i<strArr.length;i++) {
			strArr[i] = String.valueOf(numbers[i]);
		}
		
		// [숫자, strArr상에서 인덱스] 추가할 리스트
		List<String []> sortArr = new ArrayList<>();

		// 뒤에 9 추가해서 4자리수로 다 맞춰준다.
		for(int i=0; i<strArr.length;i++) {
			String str = strArr[i];
			
			if(str.length()==1) {
				str += String.valueOf(str.charAt(0))+String.valueOf(str.charAt(0))+String.valueOf(str.charAt(0));
			}else if(str.length()==2) {
				str += String.valueOf(str.charAt(0))+String.valueOf(str.charAt(1));
			}else if(str.length()==3) {
				str += String.valueOf(str.charAt(0));
			}
			String[] item = {str, String.valueOf(i)};
			sortArr.add(item);
		}
		
		// 숫자 내림차순으로 정렬
		Collections.sort(sortArr, new Comparator<String []>() {
			@Override
			public int compare(String[] s1, String[] s2) {
				return Integer.parseInt(s2[0]) - Integer.parseInt(s1[0]);
			}
		});
		
		String answer = "";
		for(int i=0;i<sortArr.size();i++) {
			System.out.println(Arrays.toString(sortArr.get(i)));
			answer += strArr[Integer.parseInt(sortArr.get(i)[1])];
		}
		
		if(answer.charAt(0) == '0') {
			answer = String.valueOf(Integer.parseInt(answer));
		}
		System.out.println(answer);
		
	}
}