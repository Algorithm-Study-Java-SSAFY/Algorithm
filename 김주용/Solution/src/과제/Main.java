package 과제;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();

        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        System.out.println(getDaysInMonth(year, month));
    }

    public static int getDaysInMonth(int year, int month) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // 윤년인 경우 2월은 29일로 설정
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        return daysInMonth[month - 1];
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
