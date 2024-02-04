package com.example.monumentbook.utilities.helper;


import java.math.BigDecimal;
import java.time.LocalDate;

public class DateUtils {

//    public static LocalDate[] parseDates(Contracts users, Integer year) {
//
//        // Change date parsing according to data in table
//        String formattedStartDate = (year + "-" + users.getStartentdate()).replaceAll("/", "-");
//        String formattedEndDate = (year + "-" + users.getEndentdate()).replaceAll("/", "-");
//
//        LocalDate minDate = LocalDate.parse(formattedStartDate);
//        LocalDate maxDate = LocalDate.parse(formattedEndDate);
//
//        return new LocalDate[]{minDate, maxDate};
//    }

    public static BigDecimal calculateDuration(LocalDate startDate, String startDateType,
                                               LocalDate endDate, String endDateType) {
        BigDecimal duration = BigDecimal.ZERO;
        if (startDate != null && endDate != null && startDateType != null && endDateType != null) {
            if (startDate.equals(endDate) && startDateType.equals(endDateType)) {
                duration = BigDecimal.valueOf(0.5);
            } else if (startDate.equals(endDate) && !startDateType.equals(endDateType)) {
                duration = BigDecimal.valueOf(1);
            } else {
                LocalDate currentStartDate = startDate;

                while (!currentStartDate.isAfter(endDate)) {
                    duration = duration.add(BigDecimal.valueOf(1.0));
                    currentStartDate = currentStartDate.plusDays(1);
                }

//                if (startDateType.equals(DateType.AFTERNOON.getValue())) {
//                    duration = duration.subtract(BigDecimal.valueOf(0.5));
//                }
//
//                if (endDateType.equals(DateType.MORNING.getValue())) {
//                    duration = duration.subtract(BigDecimal.valueOf(0.5));
//                }

            }
        }
        return duration;
    }

}
