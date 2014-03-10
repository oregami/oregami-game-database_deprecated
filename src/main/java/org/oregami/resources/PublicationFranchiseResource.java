package org.oregami.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.oregami.data.PublicationFranchiseDao;
import org.oregami.entities.PublicationFranchise;

import com.google.inject.Inject;


@Path("/publicationFranchise")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublicationFranchiseResource {

	@Inject
	private PublicationFranchiseDao publicationFranchiseDao;
	
	public PublicationFranchiseResource() {
	}
	
	  
	@GET
	public List<PublicationFranchise> list() {
		List<PublicationFranchise> ret = null;
		ret = publicationFranchiseDao.findAll();
//		if (ret.size()==0) {
//			DatabaseFiller.getInstance().initData();
//			ret = publicationFranchiseDao.findAll();
//		}
		return ret;
	}
	
}
