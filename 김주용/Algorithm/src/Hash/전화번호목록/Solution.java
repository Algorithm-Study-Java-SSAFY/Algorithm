package Hash.전화번호목록;

import java.util.HashMap;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Boolean> hash = new HashMap<>();
        int minLength = Integer.MAX_VALUE;

        for (String num : phone_book) { // 최대 1,000,000
            hash.put(num, true);
            minLength = Integer.min(minLength, num.length());
        }

        for (String num : phone_book) {
            for (int i = minLength; i < num.length(); i++) {
                String key = num.substring(0, i);
                Boolean isExist = hash.getOrDefault(key, false);
                if (isExist) {
                    return false;
                }
            }
        }
        return answer;
    }
}