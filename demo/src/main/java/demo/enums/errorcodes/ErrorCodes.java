package demo.enums.errorcodes;

public enum ErrorCodes {

    //returns if the user searches by a timezone not available in the list of available world timezones
    NOTIMEZONE(1, "no timezone available for search input, you can find a full list at https://en.wikipedia.org/wiki/List_of_tz_database_time_zones"),
    //returns if file does not exist that user inputs
    FILEDOESNOTEXIST(2, "no filename exists with the filename specified, please update and retry");
    
    private final Integer code;
    private final String message;
    
    private ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
