package org.oregami.resources;

import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import org.oregami.data.WebsiteDao;
import org.oregami.entities.Website;
import org.oregami.entities.user.User;
import org.oregami.util.WebsiteHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/website")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WebsiteResource {

	@Inject
	private WebsiteDao websiteDao;

	@POST
	public Response createScreenshot(Website postwebsite) {
		WebsiteHelper websiteHelper = WebsiteHelper.instance();
		try {
			String url = postwebsite.getUrl();
			String size = postwebsite.getCreateSize();
			Map<String, String> result = websiteHelper.createWebsite(url, size);
			System.out.println("result: " + result);
			Website website = new Website();
			byte[] image = WebsiteHelper.instance().readFile(result.get("filename"));
			website.setImage(image);
			website.setThumbnail(WebsiteHelper.instance().resize(image, 200, null));
			website.setUrl(url);
			String id = websiteDao.save(website);
			result.put("id", id.toString());
			return Response.ok(result).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).type("text/plain").entity(e.getMessage()).build();
		}

	}

	@GET
	@Path("/raw/{id}")
	@Produces({ "image/png" })
	public Response getRawWebsite(@Auth User user, @PathParam("id") String id) {
		Website w = websiteDao.findOne(id);
		if (w==null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

//		if (!w.isApproved()) {
//			return Response.status(Response.Status.PRECONDITION_FAILED).build();
//		}

		return Response.ok(w.getImage()).build();
	}

	@GET
	@Path("/rawthumb/{id}")
	@Produces({ "image/png" })
	public Response getRawWebsiteThumbnail(@PathParam("id") String id) {
		Website w = websiteDao.findOne(id);
		if (w==null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

//		if (!w.isApproved()) {
//			return Response.status(Response.Status.PRECONDITION_FAILED).build();
//		}

		return Response.ok(w.getThumbnail()).build();
	}

	@GET
	public Response getList() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		List<Website> list = websiteDao.findAll();
		for (Website website : list) {
			java.util.HashMap<String, Object> map = new java.util.HashMap<String, Object>();
			map.put("id", website.getId());
			map.put("url", website.getUrl());
			map.put("createTime", website.getCreateTime());
			map.put("href_big", "/website/raw/" + website.getId());
			map.put("href_thumb", "/website/rawthumb/" + website.getId());
			result.add(map);
		}
		return Response.ok(result).build();
	}


}
