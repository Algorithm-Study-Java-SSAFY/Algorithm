import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        List<String> tmpNum = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            tmpNum.add("" + numbers[i]);
        }
        Comparator<String> cp = new Comparator<>() {
            public int compare(String num1, String num2) {
                return Integer.parseInt(num2 + num1) - Integer.parseInt(num1 + num2);
            }
        };
        Collections.sort(tmpNum, cp);

        if (tmpNum.get(0).equals("0")) {
            return "0";
        } else {
            answer = String.join("", tmpNum);
        }
        return answer;
    }

}