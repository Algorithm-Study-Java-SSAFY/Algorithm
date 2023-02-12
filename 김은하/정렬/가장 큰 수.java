import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
      String[] result = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);
      Arrays.sort(result, new Comparator<String>() {

         @Override
         public int compare(String o1, String o2) {
            // TODO Auto-generated method stub
            return (o2+o1).compareTo(o1+o2);
         }
         
      });
      
      answer = Arrays.toString(result).replaceAll("[, \\[\\]]", "");
      if(answer.charAt(0) == '0') {
         answer = "0";
      }
        return answer;
    }
}
