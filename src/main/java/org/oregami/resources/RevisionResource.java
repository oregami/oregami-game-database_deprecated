package org.oregami.resources;

import org.oregami.data.RevisionEntityDao;
import org.oregami.entities.CustomRevisionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;



@Controller
public class RevisionResource {

	@Autowired
	private RevisionEntityDao dao = null;

	public RevisionResource() {
	}

    @RequestMapping(value = "/revisions", method = RequestMethod.GET)
	public String list(Model model) {
		List<CustomRevisionEntity> ret = dao.findAll();
        model.addAttribute("revisions", ret);
		return "revisions";
	}

}
