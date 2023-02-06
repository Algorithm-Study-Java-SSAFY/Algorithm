import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        int i = 1;
        while (i < phone_book.length) {
            if (phone_book[i].indexOf(phone_book[i - 1]) == 0) {
                return false;
            }
            i++;
        }
        return true;
    }
}