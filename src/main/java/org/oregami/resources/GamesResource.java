package org.oregami.resources;

import org.oregami.data.GameDao;
import org.oregami.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping(value = "/games")
@Controller
public class GamesResource {

	@Autowired
	private GameDao dao = null;

	public GamesResource() {
	}

	@Autowired
	GameService service = null;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list",dao.findAll());
        return "games/list";
	}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getOne(@PathVariable("id") String id, Model model) {
        model.addAttribute("one", ResourceHelper.get(id, dao));
        return "games/one";
	}
//
//	@GET
//	@Path("/{id}/revisions")
//	public Response getRevisions(@PathParam("id") String id) {
//		return ResourceHelper.getRevisions(id, dao);
//	}
//
//	@GET
//	@Path("/{id}/revisions/{revision}")
//	public Response getRevision(@PathParam("id") String id, @PathParam("revision") String revision) {
//		return ResourceHelper.getRevision(id, revision, dao);
//	}
//
//	@POST
//	public Response create(@Auth User user, GamingEnvironment entity) {
//		return ResourceHelper.create(user, entity, service, this.getClass());
//	}
//
//	@PUT
//	@Path("{id}")
//	public Response update(@Auth User user, @PathParam("id") String id, GamingEnvironment entity) {
//		return ResourceHelper.update(user, id, entity, service);
//	}
//
//	@DELETE
//	@Path("{id}")
//	public Response delete(@Auth User user, @PathParam("id") String id) {
//		return ResourceHelper.delete(user, id, service);
//	}

}
