import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		// 왼족 스택과 오른쪽 스택 사이가 커서
		Stack<String> sl = new Stack<>(); // 왼쪽 스택
		Stack<String> sr = new Stack<>(); // 오른쪽 스택
		String s = br.readLine();

		// 시작: 왼쪽 스택에 모든 값이 들어가 있다.
		for (int i = 0; i < s.length(); i++) {
			// 한 글자씩 스택에 추가
			sl.add(s.substring(i, i + 1));
		}

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String[] order = br.readLine().split(" ");

			if (order.length == 2) { // 데이터 추가: 왼쪽 스택에 데이터 추가
				sl.add(order[1]);
			} else if (order[0].equals("L")) { // 커서 왼쪽 이동: 왼쪽 pop -> 오른쪽 push
				if (sl.size() > 0) {
					sr.add(sl.pop());
				}
			} else if (order[0].equals("D")) { // 커서 오른쪽 이동: 오른쪽 pop -> 왼쪽 push
				if (sr.size() > 0) {
					sl.add(sr.pop());
				}
			} else { // 문자 삭제 : 왼쪽 스택 pop
				if (sl.size() > 0) {
					sl.pop();
				}
			}
		}

		// 결과
		for (String i : sl) {
			sb.append(i);
		}
		Collections.reverse(sr);
		for (String i : sr) {
			sb.append(i);
		}
		System.out.println(sb);
	}

}