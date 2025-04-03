package com.kosi.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static int getDiffDayStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static int getDiffTimeStartTimeAndEndTime(LocalTime startTime, LocalTime endTime) {
        return (int) ChronoUnit.MINUTES.between(startTime, endTime);
    }

    public static String formatMinutesToDaysHoursMinutes(int totalMinutes) {
        int days = totalMinutes / (24 * 60); // 1일 = 24시간 * 60분
        int hours = (totalMinutes % (24 * 60)) / 60; // 남은 분을 시간으로 변환
        int minutes = totalMinutes % 60; // 남은 분

        if (minutes == 0) {
            if (days == 0) {
                return String.format("%d시간", hours);
            }
            return String.format("%d일 %d시간", days, hours);
        }

        return String.format("%d일 %d시간 %d분", days, hours, minutes);
    }

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2025, 4, 2);
        LocalDate endDate = LocalDate.of(2025, 4, 3);
        int diffDayStartDateAndEndDate = getDiffDayStartDateAndEndDate(startDate, endDate);
        System.out.println(diffDayStartDateAndEndDate);

        LocalTime startTime = LocalTime.of(9, 0, 0);
        LocalTime endTime = LocalTime.of(18, 0, 0);

        int diffTimeStartTimeAndEndTime = getDiffTimeStartTimeAndEndTime(startTime, endTime);
        System.out.println(diffTimeStartTimeAndEndTime);

        int sumMinute = (diffDayStartDateAndEndDate + 1) * diffTimeStartTimeAndEndTime;
        System.out.println(formatMinutesToDaysHoursMinutes(sumMinute));
    }

}
