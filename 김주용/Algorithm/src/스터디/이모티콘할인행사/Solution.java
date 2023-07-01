package 스터디.이모티콘할인행사;

public class Solution {
	
	static int retNum = 0;
	static int retMoney = 0;

	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution(new int[][] { { 40, 10000 }, { 40, 10000 } }, new int[] { 7000, 8000, 9000, 10000, 20000, 30000 });

	}

	public int[] solution(int[][] users, int[] emoticons) {
		int[] answer = {};
		int N = users.length;
		int M = emoticons.length;

		permutation(users, emoticons, new int[] { 10, 20, 30, 40 }, new int[M], 0, M);

		answer = new int[] { retNum, retMoney };
		return answer;
	}

	// 중복 조합
	public static void permutation(int[][] users, int[] emoticons, int[] rates, int[] result, int depth, int r) {
		if (depth == r) {
			getResult(users, emoticons, result);
			return;
		}

		for (int i = 0; i < 4; i++) {
			result[depth] = rates[i];
			permutation(users, emoticons, rates, result, depth + 1, r);
		}
	}

	public static void getResult(int[][] users, int[] emoticons, int[] curRates) {
		// 할인한 가격 
		int M = emoticons.length;
		int[] prices = new int[M];
		for (int i = 0; i < M; i++) {
			prices[i] = (100 - curRates[i]) * emoticons[i] / 100;
		}
		// 이모티콘 플러스 가입자 수, 총 금액 
		int num = 0;
		int money = 0;
		for (int[] user : users) {
			int sum = 0;
			for (int i = 0; i < M; i++) {
				if (curRates[i] >= user[0]) {
					sum += prices[i];
				}
			}
			if (sum >= user[1]) {
				num += 1;
			} else {
				money += sum;
			}
		}

		if (num > retNum || (num == retNum && money > retMoney)) {
			retNum = num;
			retMoney = money;
		}
	}
}
