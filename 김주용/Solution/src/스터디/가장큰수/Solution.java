package 가장큰수;


import java.util.*;
import java.util.stream.Stream;

class Solution {
    public static void main(String[] args) {
        new Solution().solution(new int[] {0, 0, 0, 0});
    }
    public String solution(int[] numbers) {
        String answer = "";
        List<String> numList = new ArrayList<>();
        for(int num : numbers) {
            numList.add(Integer.toString(num));
        }
        // {3, 34} -> 334 vs 343 -
        numList.sort((s1, s2) -> {
            return Integer.parseInt(s2 + s1) - Integer.parseInt(s1 + s2);
        });

        if(numList.get(0).equals("0")) {
            return "0";
        }
        for(String num: numList) {
            answer += num;
        }
        return answer;
    }
}