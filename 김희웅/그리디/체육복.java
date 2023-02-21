package 그리디;

public class 체육복 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	class Solution {
		public int solution(int n, int[] lost, int[] reserve) {
			
			int[] mark = new int[n]; // 채육복을 마킹할 배열
			for (int i = 0; i < lost.length; i++) { // 체육복이 필요한 사람 마킹(도난당한 사람)
				mark[lost[i] - 1] -= 1;
			} // 0, -1, 0, -1, 0
			
			for (int i = 0; i < reserve.length; i++) {
				mark[reserve[i] - 1] += 1;
			} // 0, -1, 1, -1, 0
			
			for (int i = 0; i < n - 1; i++) {
				// 인접한 사람이 0(이것도 저것도 아닌사람)이면 아무 동작도 안취해도됨.
				// 둘이 1 or -1 일때만 동작
				if (mark[i] != 0 && mark[i + 1] != 0) {
					//둘이 다르다 ? 그럼 체육복을 줄 수 있는상황.
					if (mark[i] != mark[i + 1]) {
						mark[i] = 0;
						mark[i + 1] = 0;
					}
				}
			}
			
			// -1인 경우만 빼고 체육복을 가지고 있음
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				if (mark[i] >= 0) {
					cnt++;
				}
			}
			
			return cnt;
		}
	}
}
