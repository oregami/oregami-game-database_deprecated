package org.oregami.resources;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import org.apache.log4j.Logger;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.entities.PublicationFranchise;

import com.google.inject.Inject;
import org.oregami.entities.user.User;
import org.oregami.service.PublicationFranchiseService;
import org.oregami.service.ServiceResult;


@Path("/publicationFranchise")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublicationFranchiseResource {

	@Inject
	private PublicationFranchiseDao publicationFranchiseDao;

    @Inject
    private PublicationFranchiseService publicationFranchiseervice;

	public PublicationFranchiseResource() {
	}
	
	  
	@GET
	public List<PublicationFranchise> list() {
		List<PublicationFranchise> ret = null;
		ret = publicationFranchiseDao.findAll();
		return ret;
	}

    @GET
    @Path("/{id}")
    public Response getPublicationFranchise(@PathParam("id") String id) {
        PublicationFranchise p = publicationFranchiseDao.findOne(id);
        if (p!=null) {
            return Response.ok(p).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/revisions")
    public Response getPublicationFranchiseRevisions(@PathParam("id") String id) {
        List<Number> revisionList = publicationFranchiseDao.findRevisions(id);
        if (revisionList!=null) {
            return Response.ok(revisionList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/revisions/{revision}")
    public Response getPublicationFranchiseRevision(@PathParam("id") String id, @PathParam("revision") String revision) {
        PublicationFranchise t = publicationFranchiseDao.findRevision(id, Integer.parseInt(revision));
        if (t!=null) {
            return Response.ok(t).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response update(@Auth User user, @PathParam("id") String id, PublicationFranchise t) {
        if (t.getId()==null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            ServiceResult<PublicationFranchise> serviceResult = publicationFranchiseervice.updatePublicationFranchise(t);
            if (serviceResult.hasErrors()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .type("text/json")
                        .entity(serviceResult.getErrors()).build();
            }
        } catch (OptimisticLockException e) {
            Logger.getLogger(this.getClass()).warn("OptimisticLockException", e);
            return Response.status(Response.Status.BAD_REQUEST).tag("OptimisticLockException").build();
        }
        return Response.status(Response.Status.ACCEPTED).entity(t).build();
    }

}
