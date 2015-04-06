package org.oregami.resources;

import org.oregami.data.DatabaseFiller;
import org.oregami.util.StartHelper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {

    @GET
    @Path("/refreshdata")
	public Response refreshGameData() {
    	getDatabaseFiller().deleteGameData();
    	getDatabaseFiller().deleteBaseListData();
    	getDatabaseFiller().initData();
    	return Response.ok().build();
	}


	private DatabaseFiller getDatabaseFiller() {
		return StartHelper.getInjector().getInstance(DatabaseFiller.class);
	}

}
