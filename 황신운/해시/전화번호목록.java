import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Map<String, Boolean> phoneNum = new HashMap<>();
        int min_len = 0;

        Arrays.sort(phone_book);

        for (String phone : phone_book) {
            phoneNum.put(phone, false);
            min_len = Math.min(min_len, phone.length());
        }

        for (int i = 0; i < phone_book.length; i++) {
            if (phone_book[i].length() == min_len)
                continue;
            else {
                for (int j = min_len; j < phone_book[i].length(); j++) {
                    if (phoneNum.containsKey(phone_book[i].substring(0, j))) {
                        return false;
                    }
                }
            }
        }
        return answer;
    }
}