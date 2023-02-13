import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
		int[] answer = new int[commands.length]; 
		
        for (int i=0; i<commands.length; i++){
        	int tlen = commands[i][1]-commands[i][0]+1;
        	int[] t = new int[tlen];

            System.arraycopy(array, commands[i][0]-1, t, 0, tlen); 
            Arrays.sort(t);

            answer[i]=t[commands[i][2]-1];

        }

        
        return answer;
    }
}