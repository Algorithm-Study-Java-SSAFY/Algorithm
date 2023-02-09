import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
				int[] person1 = {1, 2, 3, 4, 5};
		int[] person2 = {2, 1, 2, 3, 2, 4, 2, 5};
		int[] person3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
		int[] cnt = new int[3];
		
		for(int i=0; i<answers.length; i++) {
			if(answers[i] == person1[Math.floorMod(i, 5)]) {
				cnt[0] += 1;
			}
			if(answers[i] == person2[Math.floorMod(i, 8)]) {
				cnt[1] += 1;
			}
			if(answers[i] == person3[Math.floorMod(i, 10)]) {
				cnt[2] += 1;
			}
		}
		
		int[] cnt2 = cnt.clone();
		Arrays.sort(cnt2);
		int maxNum = cnt2[2];
        

		List<Integer> res = new ArrayList<>();
		for(int i=0; i<cnt.length; i++) {
			if(maxNum == cnt[i]) {
				res.add(i+1);
			}
		}
		
		int[] answer = new int[res.size()];
		for (int i=0; i<res.size(); i++) {
			answer[i] = res.get(i);
		}
		
        return answer;
    }
}