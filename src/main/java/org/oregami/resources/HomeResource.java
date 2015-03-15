package org.oregami.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {

	@GET
	public Map<String, Object> home() {
		HashMap<String, Object> values = new HashMap<String, Object>();
		values.put("hello", "oregami");
		return values;
	}
}
