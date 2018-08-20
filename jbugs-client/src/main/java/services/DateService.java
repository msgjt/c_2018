package services;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateService implements JsonSerializer<Date> {
    private final SimpleDateFormat formatter;

    public DateService() {
        formatter = new SimpleDateFormat("M/d/yy hh:mm a");
    }

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        String dateFormatAsString =  formatter.format(date);
        return new JsonPrimitive(dateFormatAsString);
    }
}
