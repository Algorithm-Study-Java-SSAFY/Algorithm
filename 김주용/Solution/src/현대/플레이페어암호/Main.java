package 현대.플레이페어암호;

import java.util.*;
import java.io.*;

public class Main {

	static String message;
	static String key;
	static String newMessage = "";
	

	static char[][] board;
	static HashMap<Character, Integer> idxMap;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		message = in.readLine();
		key = in.readLine();

		solution();
	}

	public static void solution() {
		makeBoard();
		divideMessage(0);
		//System.out.println(newMessage);
		String answer = "";
		for(int i = 0; i < newMessage.length(); i += 2) {
			answer += encryption(newMessage.substring(i, i+2));
		}
		System.out.println(answer);
	}

	// 5x5 배열로 변환
	public static void makeBoard() {
		board = new char[5][5];
		idxMap = new HashMap<>();
		// 중복제거
		int point = 0;
		for (char cur : key.toCharArray()) {
			if (!idxMap.containsKey(cur)) {
				board[point / 5][point % 5] = cur;
				idxMap.put(cur, point);
				point++;
			}
		}
		// 남은 알파벳 넣기.
		for (int i = 0; i < 26; i++) {
			char cur = (char) (65 + i);
			if (idxMap.containsKey(cur) || cur == 'J') {
				continue;
			}
			board[point / 5][point % 5] = cur;
			idxMap.put(cur, point);
			point++;
		}

//		for (char[] a : board) {
//			System.out.println(Arrays.toString(a));
//		}
	}

	// 메시지 2글자씩 나누기
	public static void divideMessage(int beginIdx) {
		if (beginIdx >= message.length()) {
			return;
		}

		String alpha1 = String.valueOf(message.charAt(beginIdx));
		if(beginIdx == message.length() - 1) {
			newMessage += (alpha1 + 'X');
			return;
		}
		
		String alpha2 = String.valueOf(message.charAt(beginIdx + 1));

		if (!alpha1.equals(alpha2)) {
			newMessage += (alpha1 + alpha2);
			divideMessage(beginIdx + 2);
		} else if (alpha1.equals("X") && alpha2.equals("X")) {
			newMessage += (alpha1 + "Q");
			divideMessage(beginIdx + 1);
		} else {
			newMessage += (alpha1 + "X");
			divideMessage(beginIdx + 1);
		}

	}
	
	public static String encryption(String cur) {
		String result = "";
		char alpha1 = cur.charAt(0);
		char alpha2 = cur.charAt(1);
		
		int point1 = idxMap.get(alpha1);
		int point2 = idxMap.get(alpha2);
	
		// 1. 같은 행 조회 
		if(point1 / 5 == point2 / 5) {
			result += board[point1 / 5][(point1 % 5 + 1) % 5];
			result += board[point2 / 5][(point2 % 5 + 1) % 5];
			return result;
		}
		// 2. 같은 열 조회 
		if(point1 % 5 == point2 % 5) {
			result += board[(point1 / 5 + 1) % 5][point1 % 5];
			result += board[(point2 / 5 + 1) % 5][point2 % 5];
			return result;
		}
		// 3. 그 외 
		result += board[point1 / 5][point2 % 5];
		result += board[point2 / 5][point1 % 5];
		return result;
	}
}
