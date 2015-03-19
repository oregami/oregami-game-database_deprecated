package org.oregami.resources;

import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import org.apache.log4j.Logger;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.data.RevisionInfo;
import org.oregami.entities.Language;
import org.oregami.entities.PublicationFranchise;
import org.oregami.entities.user.User;
import org.oregami.service.PublicationFranchiseService;
import org.oregami.service.ServiceCallContext;
import org.oregami.service.ServiceResult;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/publicationFranchise")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublicationFranchiseResource {

	@Inject
	private PublicationFranchiseDao dao = null;

    @Inject
    private PublicationFranchiseService service = null;

	public PublicationFranchiseResource() {
	}
	
	  
	@GET
	public List<PublicationFranchise> list() {
		return dao.findAll();
	}

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        return ResourceHelper.get(id, dao);
    }

    @GET
    @Path("/{id}/revisions")
    public Response getRevisions(@PathParam("id") String id) {
        return ResourceHelper.getRevisions(id, dao);
    }

    @GET
    @Path("/{id}/revisions/{revision}")
    public Response getRevision(@PathParam("id") String id, @PathParam("revision") String revision) {
        return ResourceHelper.getRevision(id, revision, dao);
    }

    @POST
    public Response create(@Auth User user, PublicationFranchise entity) {
        return ResourceHelper.create(user, entity, service);
    }

    @PUT
    @Path("{id}")
    public Response update(@Auth User user, @PathParam("id") String id, PublicationFranchise entity) {
        return ResourceHelper.update(user, id, entity, service);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@Auth User user, @PathParam("id") String id) {
        return ResourceHelper.delete(user, id, service);
    }

}
