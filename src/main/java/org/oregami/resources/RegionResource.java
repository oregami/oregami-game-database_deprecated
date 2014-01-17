package org.oregami.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.oregami.data.DatabaseFiller;
import org.oregami.data.RegionDao;
import org.oregami.entities.Region;

import com.google.inject.Inject;


@Path("/region")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegionResource {

	@Inject
	private RegionDao regionDao;
	
	public RegionResource() {
	}
	
	  
	@GET
	public List<Region> list() {
		List<Region> ret = null;
		ret = regionDao.findAll();
		if (ret.size()==0) {
			DatabaseFiller.getInstance().initData();
			ret = regionDao.findAll();
		}
		return ret;
	}
	
    @GET
    @Path("/{name}")
	public Response getRegion(@PathParam("name") String name) {
    	Region region = regionDao.findByExactName(name);
    	if (region!=null) {
    		return Response.ok(region).build();
    	} else {
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
	}
	
}
