package org.oregami.resources;

import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import org.oregami.data.HardwarePlatformDao;
import org.oregami.entities.HardwarePlatform;
import org.oregami.entities.user.User;
import org.oregami.service.HardwarePlatformService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/hardwarePlatform")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HardwarePlatformResource {

    @Inject
    private HardwarePlatformDao dao = null;

    @Inject
    private HardwarePlatformService service = null;

    public HardwarePlatformResource() {
    }


    @GET
    public List<HardwarePlatform> list() {
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
    public Response create(@Auth User user, HardwarePlatform entity) {
        return ResourceHelper.create(user, entity, service, this.getClass());
    }

    @PUT
    @Path("{id}")
    public Response update(@Auth User user, @PathParam("id") String id, HardwarePlatform entity) {
        return ResourceHelper.update(user, id, entity, service);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@Auth User user, @PathParam("id") String id) {
        return ResourceHelper.delete(user, id, service);
    }
}
