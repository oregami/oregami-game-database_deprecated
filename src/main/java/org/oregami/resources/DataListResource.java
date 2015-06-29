package org.oregami.resources;

import org.oregami.data.*;
import org.oregami.entities.datalist.*;
import org.oregami.util.StartHelper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/datalist")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataListResource {


	public DataListResource() {}

	@GET
	public List<String> list() {
		List<String> l = new ArrayList<>();
		l.add("/censorshipTypes");
		l.add("/demoContentTypes");
		l.add("/gameEntryTypes");
		l.add("/hardwarePlatformTypes");
		l.add("/releaseGroupReasons");
		l.add("/releaseStates");
		l.add("/remakeEnhancementTypes");
		l.add("/softwarePlatformTypes");
		l.add("/titleTypes");
		l.add("/unReleasedStates");
		return l;
	}


	@GET
	@Path("/censorshipTypes")
	public List<CensorshipType> listCensorshipTypes() {
		return StartHelper.getInjector().getInstance(CensorshipTypeDao.class).findAll();
	}

	@GET
	@Path("/demoContentTypes")
	public List<DemoContentType> listDemoContentTypes() {
		return StartHelper.getInjector().getInstance(DemoContentTypeDao.class).findAll();
	}

	@GET
	@Path("/gameEntryTypes")
	public List<GameEntryType> listGameEntryTypes() {
		return StartHelper.getInjector().getInstance(GameEntryTypeDao.class).findAll();
	}

	@GET
	@Path("/hardwarePlatformTypes")
	public List<HardwarePlatformType> listHardwarePlatformTypes() {
		return StartHelper.getInjector().getInstance(HardwarePlatformTypeDao.class).findAll();
	}

	@GET
	@Path("/releaseGroupReasons")
	public List<ReleaseGroupReason> listReleaseGroupReasons() {
		return StartHelper.getInjector().getInstance(ReleaseGroupReasonDao.class).findAll();
	}

	@GET
	@Path("/releaseStates")
	public List<ReleaseState> listReleaseStates() {
		return StartHelper.getInjector().getInstance(ReleaseStateDao.class).findAll();
	}

	@GET
	@Path("/remakeEnhancementTypes")
	public List<RemakeEnhancementType> listRemakeEnhancementTypes() {
		return StartHelper.getInjector().getInstance(RemakeEnhancementTypeDao.class).findAll();
	}


	@GET
	@Path("/softwarePlatformTypes")
	public List<SoftwarePlatformType> listSoftwarePlatformTypes() {
		return StartHelper.getInjector().getInstance(SoftwarePlatformTypeDao.class).findAll();
	}

	@GET
	@Path("/titleTypes")
	public List<TitleType> listTitleTypes() {
		return StartHelper.getInjector().getInstance(TitleTypeDao.class).findAll();
	}

	@GET
    @Path("/unReleasedStates")
	public List<UnReleasedState> listUnReleasedStates() {
		return StartHelper.getInjector().getInstance(UnReleasedStateDao.class).findAll();
	}








}
