package com.github.schm1tz1.kafka.connect.smt;

import org.apache.kafka.connect.header.ConnectHeaders;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ExampleTransformationTest {

    private final ExampleTransformation<SourceRecord> xformKey = new ExampleTransformation.Key<>();
    private final ExampleTransformation<SourceRecord> xformValue = new ExampleTransformation.Value<>();

    @Test
    public void normalTransformLeavesRecordUnchanged() {
        final Map<String, Object> props = new HashMap<>();
        props.put("config.string", "Test!");

        xformValue.configure(props);

        ConnectHeaders headers = new ConnectHeaders();
        headers.addString("existing", "existing-value");

        final SourceRecord record = new SourceRecord(null, null, "test", 0,
                null, null, null, "Test-Message!", Instant.now().getEpochSecond(), headers);

        final SourceRecord transformedRecord = xformValue.apply(record);

        assertEquals(record, transformedRecord);
    }

}