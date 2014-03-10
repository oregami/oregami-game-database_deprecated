package org.oregami.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.oregami.data.LanguageDao;
import org.oregami.entities.Language;

import com.google.inject.Inject;


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
//		if (ret.size()==0) {
//			DatabaseFiller.getInstance().initData();
//			ret = languageDao.findAll();
//		}
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
