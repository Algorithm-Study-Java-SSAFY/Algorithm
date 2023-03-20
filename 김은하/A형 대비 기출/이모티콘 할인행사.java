import java.util.*;
class Solution {
    static int totalSales, joinCount;
    public int[] solution(int[][] users, int[] emoticons) {
        List<Integer> emoSale = new ArrayList<>();
		int[] sales = { 10, 20, 30, 40 };

		combination(users, emoticons, emoSale, sales, emoticons.length);

		int[] answer = { joinCount,totalSales };
        return answer;
    }
    
    public static void combination(int[][] users, int[] emoticons, List<Integer> emoSale, int[] sales, int depth) {
		if (depth == 0) {
			findMax(users, emoticons, emoSale);
			return;
		}

		for (int i = 0; i < 4; i++) {
			emoSale.add(sales[i]);
			combination(users, emoticons, emoSale, sales, depth - 1);
			emoSale.remove(emoSale.size() - 1);
		}
	}

	public static void findMax(int[][] users, int[] emoticons, List<Integer> emoSale) {
		int[] userInfo = new int[users.length];
		int tempCnt = 0;
		int tempTotal = 0;

		for (int i = 0; i < users.length; i++) {

			for (int j = 0; j < emoSale.size(); j++) {
				if (users[i][0] <= emoSale.get(j)) {
					userInfo[i] += emoticons[j] * (100 - emoSale.get(j)) / 100;
				}
				if (userInfo[i] >= users[i][1]) {
					userInfo[i] = 0;
					tempCnt += 1;
					break;
				}
			}

		}
		
		if (tempCnt > joinCount) {
			joinCount = tempCnt;
			totalSales = Arrays.stream(userInfo).sum();
		} else if (tempCnt == joinCount) {
			tempTotal = Arrays.stream(userInfo).sum();
			if (tempTotal > totalSales) {
				totalSales = tempTotal;
			}
		}
	}
}
