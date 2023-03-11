package code_test;

import java.util.*;
import java.io.*;


public class Main {
	
	static int N;
	static HashMap<Character,Integer> map = new HashMap<>();
	static List<Character> charList = new ArrayList<>();
	static int answer = 0;
	
	public static void main(String[] args) throws Exception{
		init();
		
		//가치를 기준으로 문자 정렬
		Collections.sort(charList, (o1,o2)->{
			return map.get(o2)-map.get(o1);
		});
		
		//숫자 더하기
		int num = 9; //9부터 시작
		for(char c:charList) {
			answer += num*map.get(c); //배정받은 숫자에 매핑되어 있는 값 곱해서 추가
			num--; //배정할 숫자 감소
		}
		
		System.out.println(answer);
		
		
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<input.length();j++) {
				char key = input.charAt(j);
				//처음 보는 문자면
				if(!map.keySet().contains(key)) { 
					map.put(key, 0); // 키 생성
					charList.add(key); // 문자 리스트에 추가
				}
				// 자리수에 맞게 10의 지수를 값에 더해준다.
				map.put(key, map.get(key)+(int)Math.pow(10, input.length()-j-1));
			}
		}
	}
}