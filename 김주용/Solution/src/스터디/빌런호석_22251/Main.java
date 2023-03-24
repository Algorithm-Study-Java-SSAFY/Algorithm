package 스터디.빌런호석_22251;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

	static int N;
	static int K;
	static int P;
	static String X;

	static int[][] countBoard;
	static boolean[] visited;

	static HashSet<Integer> set = new HashSet<>();
	static int answer = 0;

	static HashMap<Integer, String> map = new HashMap<Integer, String>() {
		{
			put(0, "1110111");
			put(1, "0010001");
			put(2, "0111110");
			put(3, "0111011");
			put(4, "1011001");
			put(5, "1101011");
			put(6, "1101111");
			put(7, "0110001");
			put(8, "1111111");
			put(9, "1111011");

		}
	};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		P = Integer.parseInt(line[2]);
		X = line[3];
		solution();
	}

	public static void solution() {
		getDisplay();
		getReverseCount();
		char[] number = X.toCharArray();
		dfs(number, 0, 0);
		System.out.println(set.size());
	}

	public static void dfs(char[] number, int cur, int reverseCnt) {
		if(reverseCnt > 0) {
			String numStr = String.valueOf(number);
			int numValue = Integer.parseInt(numStr);
			if (1 <= numValue && numValue <= N && !numStr.equals(X)) {
				System.out.println(numStr+"//"+reverseCnt);
				set.add(numValue);
			}	
		}
		
		if (cur >= number.length) {
			return;
		}

		for (int j = 0; j < 10; j++) {
			int curNum = X.charAt(cur) - '0';
			int cnt = countBoard[curNum][j];
			if (cnt + reverseCnt <= P) {
				number[cur] = (char) (j + '0');
				dfs(number, cur + 1, reverseCnt + cnt);
			}

		}
	}

	// X가 보여지는 모습
	public static void getDisplay() {
		// X를 K 자리 수로 변환
		String zero = "";
		for (int i = 0; i < K - X.length(); i++) {
			zero += "0";
		}
		X = zero + X;
		visited = new boolean[X.length()];
	}

	// 숫자를 다른 숫자로 변환하는데 필요한 반전 횟수
	public static void getReverseCount() {
		countBoard = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = i + 1; j < 10; j++) {
				String a = map.get(i);
				String b = map.get(j);
				int cnt = 0;
				for (int k = 0; k < 7; k++) { // XOR
					if (a.charAt(k) != b.charAt(k)) {
						cnt++;
					}
				}
				countBoard[i][j] = cnt;
				countBoard[j][i] = cnt;
			}
		}
	}
}