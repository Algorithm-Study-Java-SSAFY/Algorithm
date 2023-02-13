package com.study02.PgMTest;
//프로그래머스-모의고사

import java.util.*;

public class PgMTest {
	public int[] solution(int[] answers) {
		int cnt = 0;
		int[] student1 = {1,2,3,4,5};
		int[] student2 = {2,1,2,3,2,4,2,5};
		int[] student3 = {3,3,1,1,2,2,4,4,5,5};
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		while(cnt<answers.length) {
			int check = answers[cnt];
			
			if(student1[Math.floorMod(cnt,5)] == check) {
				result1++;
			}
			if(student2[Math.floorMod(cnt,8)] == check) {
				result2++;
			}
			if(student3[Math.floorMod(cnt,10)] == check) {
				result3++;
			}
			
			cnt += 1;
		}
		
		List<Integer> result = new ArrayList<>();
		int maxResult = result1;
		if (result2 > maxResult) {
			result.add(2);
			maxResult = result2;
		}else if(result2 == result1) {
			result.add(1);
			result.add(2);
		}else {
			result.add(1);
		}
		
		if (result3>maxResult) {
			result.clear();
			result.add(3);
		}else if(result3 == maxResult) {
			result.add(3);
		}
		
        int[] answer = new int[result.size()];
        for(int i=0;i<answer.length;i++) {
        	answer[i] = result.get(i);
        }
        return answer;
    }
}
