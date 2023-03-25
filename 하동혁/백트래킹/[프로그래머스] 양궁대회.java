import java.io.*;
import java.util.*;

public class Solution {

	/*
	 * 한 점수에 대해 어파치와 라이언의 적중한 화살 수가 같으면 어파치가 점수를 얻는다. K점에 여러발을 맞춰도 얻는 점수는 K점이다. 최종
	 * 점수가 같은 경우 어파치가 승리
	 * 
	 * 어파치는 n발을 다 쏜 상태 (라이언도 n발을 다 사용해야함) 라이언이 n발의 화살을 어느 점수에 맞혀야 가장 큰 점수차로 이길 수
	 * 있을까? (라이언이 우승할 수 없는 경우 [-1] 출력) (라이언이 가장 큰 점수차이로 우승할 수 있는 방법이 여러가지인 경우, 가장 낮은
	 * 점수를 더 많이 맞힌 경우를 정답으로)
	 */

	static int N; // 화살 개수
	static int[] APACH;
	static int[] RIAN;
	static int apachScore = 0, rianScore = 0;
	static int[] score = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

	static int maxDif = 0;
	static int maxRian[];

	public static void main(String[] args) throws Exception {
		int n = 9;
		int[] info = {0,0,1,2,0,1,1,1,1,1,1}; // 점수: 10,9,8 ~ 0점

		N = n;
		APACH = info;
		RIAN = new int[11];
		maxRian = new int[11];

		for (int i = 0; i <= 10; i++) {
			if (APACH[i] > 0)
				apachScore += score[i];
		}

		backTracking(0, 0);

		if (maxDif <= 0) {
			maxRian = new int[] { -1 };
			System.out.println(Arrays.toString(maxRian));
			// return maxRian;
		} else {
			System.out.println(Arrays.toString(maxRian));
			// return maxRian;
		}

	}

	static void backTracking(int deep, int rianS) {

		if (N == 0 || deep > 10) {
			rianScore = 0; 
			for (int i = 0; i <= 10; i++) {
				if (RIAN[i] > 0)
					rianScore += score[i];
			}
			int dif = rianScore - apachScore;
			
			// N이 남았으면 가장 낮은 점수에 남은 N을 추가 
			if(N>0) { 
				RIAN[10] += N;
			}
			

			if (maxDif < dif) {
				maxDif = dif;
				maxRian = RIAN.clone();
			} 
			else if (maxDif == dif) { // 점수가 같다면 작은 점수부터 비교 
				for (int i = 10; i >= 0; i--) {
					if (maxRian[i] < RIAN[i]) {
						maxRian = RIAN.clone();
						break;
					}else if(maxRian[i] > RIAN[i]){
						break;
					}
				}
			}

			RIAN[10] -= N;
			return;
		}

		// 1. 아파치를 이기는 경우
		if (APACH[deep] < N) {

			RIAN[deep] = APACH[deep] + 1;
			N -= (APACH[deep] + 1);
			if (APACH[deep] > 0) apachScore -= score[deep]; // 아파치가 취득한 점수를 라이언이 이길 경우 아파치 점수를 변경

			backTracking(deep + 1, rianS + score[deep]);

			if (APACH[deep] > 0) apachScore += score[deep];
			RIAN[deep] = 0;
			N += (APACH[deep] + 1);
		}

		// 2. 아파치에게 지고 다음으로 뛰어넘는 경우
		backTracking(deep + 1, rianS);

	}

}