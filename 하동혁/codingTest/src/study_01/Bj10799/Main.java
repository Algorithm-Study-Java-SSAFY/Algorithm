package study_01.Bj10799;

import java.awt.print.Printable;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data = br.readLine();


		Stack<Character> st = new Stack<>();
		int answer = 0;

		for (int i = 0; i < data.length(); i++) {
			char check = data.charAt(i);
			if (check == '(') { // ö�� �߰�
				st.push('(');
				answer += 1;
			} else if (check == ')' && data.charAt(i-1) == ')') { // ö�� ����
				st.pop();
			} else if (check == ')' && st.peek() == '(') { // �������϶� -> '(' �ϳ� ���� && ö�� ������ ����
				st.pop();
				answer -= 1;
				answer += st.size();
			}
		}
		System.out.println(answer);

	}

}
