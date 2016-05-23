package org.oregami.resources;

import org.oregami.data.PublicationFranchiseDao;
import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.PublicationFranchise;
import org.oregami.service.PublicationFranchiseService;
import org.oregami.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PublicationFranchiseResource {

	@Autowired
	private PublicationFranchiseDao dao = null;

    @Autowired
    private PublicationFranchiseService service = null;

	public PublicationFranchiseResource() {
	}


    @RequestMapping(value = "/publicationFranchise", method = RequestMethod.GET)
	public String list(Model model) {
        model.addAttribute("list", dao.findAll());
        return "publicationFranchise/list";
	}

    @RequestMapping(value = "/publicationFranchise/new", method = RequestMethod.GET)
    public String newForm(Model model) {
        model.addAttribute("publicationFranchise", new PublicationFranchise());
        return "publicationFranchise/new";
    }

    @RequestMapping(value = "/publicationFranchise/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") String id, Model model) {
        model.addAttribute("publicationFranchise",ResourceHelper.get(id, dao));
        return "publicationFranchise/one";
    }

//    @GET
//    @Path("/{id}/revisions")
//    public Response getRevisions(@PathParam("id") String id) {
//        return ResourceHelper.getRevisions(id, dao);
//    }
//
//    @GET
//    @Path("/{id}/revisions/{revision}")
//    public Response getRevision(@PathParam("id") String id, @PathParam("revision") String revision) {
//        return ResourceHelper.getRevision(id, revision, dao);
//    }
//
    @RequestMapping(value = "/publicationFranchise", method = RequestMethod.POST)
    public String create(
            //User user,
            @ModelAttribute PublicationFranchise entity, Model model) {
        ServiceResult<BaseEntityUUID> serviceResult = ResourceHelper.create(null, entity, service, this.getClass());
        if (serviceResult.hasErrors()) {
            model.addAttribute("errors", serviceResult.getErrors());
            model.addAttribute("publicationFranchise", serviceResult.getResult());
            return "publicationFranchise/new";
        } else {
            return get(serviceResult.getResult().getId(), model);
        }

    }
//
//    @PUT
//    @Path("{id}")
//    public Response update(@Auth User user, @PathParam("id") String id, PublicationFranchise entity) {
//        return ResourceHelper.update(user, id, entity, service);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public Response delete(@Auth User user, @PathParam("id") String id) {
//        return ResourceHelper.delete(user, id, service);
//    }

}
