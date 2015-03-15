package org.oregami.resources;

import com.google.inject.Inject;
import org.oregami.data.RegionDao;
import org.oregami.entities.Region;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


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
//		if (ret.size()==0) {
//			DatabaseFiller.getInstance().initData();
//			ret = regionDao.findAll();
//		}
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
