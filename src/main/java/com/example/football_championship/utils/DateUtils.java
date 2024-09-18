package com.example.football_championship.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate validateDate(String date) {
        if (date == null) {
            throw new IllegalArgumentException("Date is empty or null");
        }
        try {
            // Parse the date string using the defined format
            int currYear = LocalDate.now().getYear();
            LocalDate updatedDate = LocalDate.parse(date + "/" + currYear, DATE_FORMAT);
            return updatedDate;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'dd/MM'. Provided: " + date);
        }
    }

}
