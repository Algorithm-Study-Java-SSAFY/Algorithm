import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] temp = new int[3];
      int[] student1 = {1,2,3,4,5};
      int[] student2 = {2,1,2,3,2,4,2,5};
      int[] student3 = {3,3,1,1,2,2,4,4,5,5};
      int score1 = 0;
      int score2 = 0;
      int score3 = 0;
      int count = 0;
      int index = -1;
      
      for (int i = 0; i < answers.length; i++) {
         if(answers[i] == student1[i%5]) 
            score1++;
         if(answers[i] == student2[i%8])
            score2++;
         if(answers[i] == student3[i%10])
            score3++;
      }
      
      int[] result = {score1, score2, score3};
      Arrays.sort(result);
      
      if (result[2] == score1) {
         temp[0] = 1;
         count++;
      }
      if (result[2] == score2) {
         temp[1] = 2;
         count++;
      }
      if (result[2] == score3) {
         temp[2] = 3;
         count++;
      }
      Arrays.sort(temp);
      for (int i = 0; i < temp.length; i++) {
         if(temp[i] == 0)
            index = i;
      }
      
      int[] answer = new int [count];
      System.arraycopy(temp, index+1, answer, 0, count);
        return answer;
    }
}
