package org.oregami.resources;

import com.google.inject.Inject;
import org.oregami.data.LanguageDao;
import org.oregami.entities.Language;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/language")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanguageResource {

	@Inject
	private LanguageDao languageDao;

	public LanguageResource() {
	}

	@GET
	public List<Language> list() {
		List<Language> ret = null;
		ret = languageDao.findAll();
		return ret;
	}

    @GET
    @Path("/{name}")
	public Response getLanguage(@PathParam("name") String name) {
    	Language language = languageDao.findByExactName(name);
    	if (language!=null) {
    		return Response.ok(language).build();
    	} else {
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
	}

}
