package org.oregami.resources;

import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import org.oregami.data.GamingEnvironmentDao;
import org.oregami.entities.GamingEnvironment;
import org.oregami.entities.user.User;
import org.oregami.service.GamingEnvironmentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/gamingEnvironments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GamingEnvironmentResource {

	@Inject
	private GamingEnvironmentDao dao = null;

	public GamingEnvironmentResource() {
	}

	@Inject
	GamingEnvironmentService service = null;

	@GET
	public List<GamingEnvironment> list() {
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
	public Response create(@Auth User user, GamingEnvironment entity) {
		return ResourceHelper.create(user, entity, service, this.getClass());
	}

	@PUT
	@Path("{id}")
	public Response update(@Auth User user, @PathParam("id") String id, GamingEnvironment entity) {
		return ResourceHelper.update(user, id, entity, service);
	}

	@DELETE
	@Path("{id}")
	public Response delete(@Auth User user, @PathParam("id") String id) {
		return ResourceHelper.delete(user, id, service);
	}

}
