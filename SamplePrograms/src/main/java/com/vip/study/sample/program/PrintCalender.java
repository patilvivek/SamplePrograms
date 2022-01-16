package com.vip.study.sample.program;

/**
 * @author : vivek
 */
public class PrintCalender {

    public static void main(String[] args) {
        String day = "Sat";
        int year = 2022;
        // printCalender(day, year);
        printCalender1(day, year);
    }

    private static void printCalender1(String day, int year) {
        String[] dayNames = getDayNames();
        String[] monthNames = getMonthNames();
        int[] noOfDayInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            noOfDayInMonth[1] = 29;
        }
        for (int i = 0; i < monthNames.length; i++) {
            int noOfDaysSkip = getNoOfDaysSkipInStartOfWeek(day, dayNames);
            int noOfDayToSkipCounter=0;
            printMonthName(monthNames[i]);
            printDaysName(dayNames);
            System.out.println();
            System.out.println("-------------------------------------------------");
            int counter=1;
            while (counter <=noOfDayInMonth[i]){
                noOfDayToSkipCounter++;
                if(noOfDayToSkipCounter<=noOfDaysSkip){
                    printMsg(" %4s |", "");
                    continue;
                }
                printMsg(counter);
                int modVal = noOfDayToSkipCounter % 7;
                if (modVal == 0) {
                    System.out.println();
                }
                day = dayNames[modVal];
                counter++;
            }
            System.out.println();
            System.out.println();
        }
    }

    private static void printDaysName(String[] dayNames) {
        for (String dayName : dayNames) {
            printMsg(" %4s |", dayName);
        }
    }

    private static int getNoOfDaysSkipInStartOfWeek(String day, String[] dayNames) {
        for (int i = 0; i < dayNames.length; i++) {
            if (dayNames[i].equalsIgnoreCase(day)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    private static void printCalender(String day, int year) {
        String[] days = getDayNames();
        String[] months = getMonthNames();
        for (int i = 0; i < 12; i++) {

            printMonthName(months[i]);
            printDaysName(days);
            System.out.println();
            System.out.println("-------------------------------------------------");

            int noOfDays = getNoOfDays(months[i], year);
            boolean dayMatch = false;
            for (int dayIndex = 1; dayIndex <= noOfDays; ) {
                int counter = 0;
                for (int j = 0; j < 7 && dayIndex <= noOfDays; j++) {
                    counter = j == 6 ? 0 : j + 1;
                    if (!dayMatch) {
                        if (day.equalsIgnoreCase(days[j])) {
                            dayMatch = true;
                            day = days[counter];
                            printMsg(dayIndex++);
                        } else {
                            printMsg(" %4s |", "");
                        }
                    } else {
                        day = days[counter];
                        printMsg(dayIndex++);
                    }
                }
                System.out.println();
                if (dayIndex == noOfDays) {
                    counter = counter == 6 ? 0 : counter + 1;
                    day = days[counter];
                    printMsg(dayIndex++);
                    System.out.println();
                }
            }
            System.out.println("-------------------------------------------------");
            System.out.println();
        }
    }

    private static void printMonthName(String month) {
        printMsg("----------------------%s------------------------%n", month);
    }

    private static void printMsg(String s, String val) {
        System.out.printf(s, val);
    }

    private static void printMsg(int val) {
        printMsg(" %4s |", String.valueOf(val));
    }

    private static String[] getMonthNames() {
        return new String[]{"Jan", "Feb", "Mar", "Apr", "May", "June", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    }

    private static String[] getDayNames() {
        return new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    }

    private static int getNoOfDays(String month, int year) {
        switch (month) {
            case "Jan":
            case "Mar":
            case "May":
            case "Jul":
            case "Aug":
            case "Oct":
            case "Dec":
                return 31;
            case "Feb":
                return isLeapYear(year) ? 29 : 28;
            default:
                return 30;
        }
    }
}
