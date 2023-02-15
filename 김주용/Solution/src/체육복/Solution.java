package 체육복;

import java.util.Arrays;

public class Solution {
    static int[] students = new int[32];
    
    public static void main(String[] args) {
		new Solution().solution(5, new int[] {2, 4}, new int[] {1, 3, 5});
	}
    
	public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        
        for(int num : reserve) {	// 여벌 체육복 
        	students[num] = 2;
        }
        
        for(int num : lost) {	//여벌 체육복을 도난 당한 놈 
        	if(students[num] == 2) {
        		answer += 1;
        		students[num] -= 1;
        	}
        }
        Arrays.sort(lost);	//앞 번호 먼저 순차적으로 
        for(int num : lost) {
        	if(students[num] == 0) {
            	if(students[num - 1] > 1) {	// 앞
            		students[num - 1] -= 1;
            		answer += 1;
            	} else if(students[num + 1] > 1) {	// 뒤
            		students[num+1] -= 1;
            		answer += 1;
            	}
        	}
        	
        }
        
        return answer;
    }

}
