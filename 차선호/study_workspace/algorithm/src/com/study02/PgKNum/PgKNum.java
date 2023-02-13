package com.study02.PgKNum;
// 프로그래머스 -k번째 수

import java.util.*;

public class PgKNum {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
        
		for(int m=0;m<commands.length;m++) {
			
			int i = commands[m][0];
			int j = commands[m][1];
			int k = commands[m][2];
			int[] slicedArr = new int[j-i+1];
			System.arraycopy(array, i-1, slicedArr, 0, j-i+1);
            Arrays.sort(slicedArr);
            //System.out.println(Arrays.toString(slicedArr));
            answer[m] = slicedArr[k-1];
		}
        return answer;
    }
}
