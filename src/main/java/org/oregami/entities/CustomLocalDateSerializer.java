package org.oregami.entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class CustomLocalDateSerializer extends JsonSerializer<LocalDate>{

	@Override
	public void serialize(LocalDate value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		jgen.writeString(fmt.format(value.toDate()));

	}

}
