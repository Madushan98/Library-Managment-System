package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToDatetime {

    public static LocalDate getDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}
