package Sort.가장큰수;


import java.net.Inet4Address;
import java.util.*;

class Solution {
    public static void main(String[] args) {
        new Solution().solution(new int[] {3, 30, 34, 5, 9});
    }
    public String solution(int[] numbers) {
        String answer = "";
        // 큰 수가 앞으로
        List<String> numList = new ArrayList<>();
        int length = 0;
        for(int num : numbers) {
            numList.add(Integer.toString(num));
            length += Integer.toString(num).length();
        }

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String n, String m) {
                int minLength = Math.min(n.length(), m.length());
                for (int j = 0; j < minLength; j++) {
                    return Integer.compare(m.charAt(j) - '0', n.charAt(j) - '0');
                }
                return n.length() - m.length();
            }
        };
        Collections.sort(numList, comparator);


        System.out.println(numList);


        return answer;
    }
}