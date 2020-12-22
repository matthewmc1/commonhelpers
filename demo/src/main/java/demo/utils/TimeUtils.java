package demo.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import demo.enums.errorcodes.ErrorCodes;
import demo.exceptions.UtilitiesExceptionHandler;

public class TimeUtils {

    // filter list of zones based on specific String
    // (https://en.wikipedia.org/wiki/List_of_tz_database_time_zones)
    public static ZoneId filterZoneByCity(String city) throws UtilitiesExceptionHandler {

        Set<String> zones = ZoneId.getAvailableZoneIds();
        // return zone based on String - if there is one that exists that contains the City
        if (zones.stream().anyMatch(zone -> zone.contains(city))) {
            // can never be a null optional due to the initial if check therefore no need to check ifpresent here
            return ZoneId.of(zones.stream().filter(zone -> zone.contains(city)).findFirst().get());
        }
        throw new UtilitiesExceptionHandler(ErrorCodes.NOTIMEZONE.getCode(), ErrorCodes.NOTIMEZONE.getMessage(), city);    
    }

    public static long compareDates(String timezoneToCompare) throws UtilitiesExceptionHandler {
        // ChronoUnit was introduced in JDK 8 along with the Date/Time changes that is also used below.
        // The idea here is we can determine the time between two timezones based on the system default and a zone passed in by the user.
        return ChronoUnit.HOURS.between(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                Instant.now().atZone(filterZoneByCity(timezoneToCompare)).toLocalDateTime());
    }

    public static void main(String[] args) {
        try {
            System.out.println("Timezone difference between local machine " + ZoneId.systemDefault()
            + " and searched location is " + compareDates("New_ork") + " hour(s)");
        } catch (UtilitiesExceptionHandler e) {
            System.out.println(String.format("Error message thrown with the error code %d, error message '%s' and with user input %s", e.getErrorCode(), e.getMessage(), e.getInput()));
        }
    }

}
