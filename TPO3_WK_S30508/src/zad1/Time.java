/**
 *
 *  @author Wierzba Kacper S30508
 *
 */

package zad1;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Time {
    public static String passed(String s, String s1) {

        StringBuilder resultString = new StringBuilder();

        ZonedDateTime ss = null;
        ZonedDateTime ss1 = null;

        boolean timeused = false;

        try{
            if(s.contains("T")) {
                ss = LocalDateTime.parse(s).atZone(ZoneId.of("Europe/Warsaw"));
                ss1 = LocalDateTime.parse(s1).atZone(ZoneId.of("Europe/Warsaw"));
                timeused = true;
            }
            else {
                LocalDate date = LocalDate.parse(s);
                LocalDate date1 = LocalDate.parse(s1);

                ss = date.atStartOfDay().atZone(ZoneId.of("Europe/Warsaw"));
                ss1 = date1.atStartOfDay().atZone(ZoneId.of("Europe/Warsaw"));
            }

            long daysBetween = ChronoUnit.DAYS.between(ss, ss1);
            double weeksBetween = daysBetween / 7.0;
            long hoursBetween = ChronoUnit.HOURS.between(ss, ss1);
            long minutesBetween = ChronoUnit.MINUTES.between(ss, ss1);
            Period period = Period.between(ss.toLocalDate(), ss1.toLocalDate());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("pl", "PL"));
            String startDate = ss.format(dtf);
            String endDate = ss1.format(dtf);



            // Tworzenie odpowiedzi
            resultString.append("Od ").append(startDate).append(" (")
                    .append(ss.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pl", "PL")))
                    .append(")");

            if(timeused)
                resultString.append(" godz. "+s.substring(s.indexOf("T")+1,s.length()));

            resultString.append(" do ")
                    .append(endDate).append(" (").append(ss1.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pl", "PL")))
                    .append(")");

            if(timeused)
                resultString.append(" godz. "+s1.substring(s.indexOf("T")+1,s.length()));


            resultString.append("\n - mija: ").append(daysBetween).append(" ").append(daysBetween==1?"dzień":"dni").append(", tygodni ").append(String.format("%.2f", weeksBetween));

            if (timeused && (hoursBetween > 0 || minutesBetween > 0)) {
                resultString.append("\n - godzin: ").append(hoursBetween).append(", minut: ").append(minutesBetween);
            }

            if (daysBetween >= 1) {
                resultString.append("\n - kalendarzowo: ");

                if(period.getYears()>=1) {
                    if(period.getYears()==1) resultString.append(period.getYears()).append(" rok, ");
                    else if(period.getYears()>=2 && period.getYears()<=4) resultString.append(period.getYears()).append(" lata, ");
                    else resultString.append(period.getYears()).append(" lat, ");
                }
                if(period.getMonths()>=1) {
                    if (period.getMonths() == 1) resultString.append(period.getMonths()).append(" miesiąc, ");
                    else if (period.getMonths() >= 2 && period.getMonths() <= 4)
                        resultString.append(period.getMonths()).append(" miesiące, ");
                    else resultString.append(period.getMonths()).append(" miesięcy, ");
                }
                if(period.getDays()>=1) {
                    if (period.getDays() == 1) resultString.append(period.getDays()).append(" dzień, ");
                    else resultString.append(period.getDays()).append(" dni, ");
                }

                resultString.deleteCharAt(resultString.length()-1);
                resultString.deleteCharAt(resultString.length()-1);

            }
        } catch (DateTimeParseException a)
        {
            resultString.append("*** ").append(a);
        }



        return resultString.toString();
    }
}
