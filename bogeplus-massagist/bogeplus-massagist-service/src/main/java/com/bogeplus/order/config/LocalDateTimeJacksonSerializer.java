package com.bogeplus.order.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.seata.rm.datasource.undo.parser.spi.JacksonSerializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class LocalDateTimeJacksonSerializer implements JacksonSerializer {
    private final static String DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
    private final static DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    @Override
    public Class<LocalDateTime> type() {
        return LocalDateTime.class;
    }

    @Override
    public JsonSerializer<LocalDateTime> ser() {
        return new LocalDateTimeSerializer(DATETIME_FORMAT);
    }

    @Override
    public JsonDeserializer<? extends LocalDateTime> deser() {
        return new LocalDateTimeDeserializer(DATETIME_FORMAT);
    }
}
